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
package org.visual.debugger.view.tabs;

import javafx.scene.control.Menu;
import javafx.scene.control.Tab;
import lombok.extern.slf4j.Slf4j;
import org.kordamp.ikonli.devicons.Devicons;
import org.kordamp.ikonli.javafx.FontIcon;
import org.visual.debugger.api.ContextMenuContainer;
import org.visual.debugger.context.DebuggerContext;
import org.visual.debugger.node.SVNode;
import org.visual.debugger.view.DisplayUtils;
import org.visual.debugger.view.ScenicViewGui;
import org.visual.debugger.view.control.ProgressWebView;
import org.visual.shared.pojo.JavaFxProperty;

@Slf4j
public class JavaDocTab extends Tab implements ContextMenuContainer {

  private final ScenicViewGui scenicView;

  private final ProgressWebView webView;

  public static final String TAB_NAME = "JavaDoc";

  private final JavaFxProperty javaFxProperty = DebuggerContext.INSTANCE.get(JavaFxProperty.class);

  private final String javaDoc = "https://openjfx.io/javadoc/" + javaFxProperty.getVersion();

  public JavaDocTab(final ScenicViewGui view) {
    super(TAB_NAME);
    this.scenicView = view;
    this.webView = new ProgressWebView();
    webView.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
    setContent(webView);
    setGraphic(new FontIcon(Devicons.JAVA));
    setClosable(false);
    selectedProperty()
        .addListener(
            (arg0, arg1, newValue) -> {
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
    if (selectedNode == null
        || selectedNode.getNodeClassName() == null
        || !selectedNode.getNodeClassName().startsWith("javafx.")) {
      webView.doLoad(javaDoc);
    } else {
      String baseClass = selectedNode.getNodeClassName();
      String baseModule;
      try {
        baseModule = Class.forName(baseClass).getModule().getName();
      } catch (ClassNotFoundException e) {
        log.error(e.getMessage(), e);
        return;
      }
      if (property != null) {
        baseClass = scenicView.findProperty(baseClass, property);
      }
      final String page =
          javaDoc
              + baseModule
              + "/"
              + baseClass.replace('.', '/')
              + ".html"
              + (property != null ? ("#" + property + "Property") : "");
      webView.doLoad(page);
    }
  }
}
