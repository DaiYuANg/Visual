/*
 * Scenic View, 
 * Copyright (C) 2012 Jonathan Giles, Ander Ruiz, Amy Fowler 
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.visual.model.debugger.view.tabs;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.control.Menu;
import javafx.scene.control.Tab;
import javafx.scene.image.ImageView;
import lombok.extern.slf4j.Slf4j;
import org.visual.model.debugger.node.SVNode;
import org.visual.model.debugger.api.ContextMenuContainer;
import org.visual.model.debugger.view.DisplayUtils;
import org.visual.model.debugger.view.control.ProgressWebView;
import org.visual.model.debugger.view.ScenicViewGui;


@Slf4j
public class JavaDocTab extends Tab implements ContextMenuContainer {
    
    private final ScenicViewGui scenicView;
    
    private final ProgressWebView webView;
    
    public static final String TAB_NAME = "JavaDoc";

    public JavaDocTab(final ScenicViewGui view) {
        super(TAB_NAME);
        
        this.scenicView = view;
        this.webView = new ProgressWebView();
        webView.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        setContent(webView);
        setGraphic(new ImageView(DisplayUtils.getUIImage("javadoc.png")));
        setClosable(false);
        selectedProperty().addListener((arg0, arg1, newValue) -> {
            if (newValue) {
                DisplayUtils.showWebView(true);
                loadAPI(null);
            } else {
                DisplayUtils.showWebView(false);
            }
        });
    }

    @Override
    public Menu getMenu() {
        return null;
    }
    
    public void loadAPI(final String property) {
        SVNode selectedNode = scenicView.getSelectedNode();
        if (selectedNode == null || selectedNode.getNodeClassName() == null || !selectedNode.getNodeClassName().startsWith("javafx.")) {
            webView.doLoad("https://openjfx.io/javadoc/11/index.html");
        } else {
            String baseClass = selectedNode.getNodeClassName();
            String baseModule;
            try {
                baseModule = Class.forName(baseClass).getModule().getName();
            } catch (ClassNotFoundException e) {
                log.error(e.getMessage(),e);
                return;
            }
            if (property != null) {
                baseClass = scenicView.findProperty(baseClass, property);
            }
            final String page = "https://openjfx.io/javadoc/11/" + baseModule + "/" + baseClass.replace('.', '/') + ".html"
                    + (property != null ? ("#" + property + "Property") : "");
            webView.doLoad(page);
        }
    }
}
