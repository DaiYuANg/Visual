/*
 * Scenic View, 
 * Copyright (C) 2012 Jonathan Giles, Ander Ruiz, Amy Fowler, Matthieu Brouillard
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

import java.util.HashMap;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.StackPane;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.simpleicons.SimpleIcons;
import org.visual.model.debugger.controller.StageID;
import org.visual.model.debugger.event.EvCSSFXEvent;
import org.visual.model.debugger.event.FXConnectorEvent;
import org.visual.model.debugger.view.ScenicViewGui;
import org.visual.model.debugger.view.cssfx.CSSFXTabContentController;
import org.visual.model.debugger.view.cssfx.MonitoredCSS;

@Slf4j
public class CSSFXTab extends Tab {
    private static final String CSSFX_TAB_NAME = "CSSFX";
    private ScenicViewGui gui;
    private CSSFXTabContentController cssfxTabContentController;
    private final Map<StageID, ObservableList<MonitoredCSS>> cssByStage = new HashMap<>();

    public CSSFXTab(ScenicViewGui gui) {
        super(CSSFX_TAB_NAME);
        this.gui = gui;

        // Set the tab icon, uses a SVG CSS3 icon
        setGraphic(createTabGraphic());
        setContent(createTabContent());
        
        setClosable(false);
    }

    private Node createTabContent() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(CSSFXTabContentController.class.getResource("CssFxTabContent.fxml"));
            Node root = fxmlLoader.load();
            cssfxTabContentController = fxmlLoader.getController();
            cssfxTabContentController.setScenicViewGui(gui);
            return root;
        } catch (Exception ex) {
            log.error(ex.getLocalizedMessage(),ex);
            Label errorLabel = new Label("Failure loading CSSFX tab");
            errorLabel.setAlignment(Pos.CENTER);
            StackPane sp = new StackPane(errorLabel);
            return sp;
        }
    }

    @Contract(" -> new")
    private @NotNull Node createTabGraphic() {
        return new FontIcon(SimpleIcons.CSS3);
    }

    public void handleEvent(FXConnectorEvent appEvent) {
        if (appEvent instanceof EvCSSFXEvent cssEvent) {
            FXConnectorEvent.SVEventType type = cssEvent.getType();
            
            switch (type) {
            case CSS_ADDED:
                addCSS(cssEvent.getStageID(), cssEvent.getUri(), cssEvent.getSource());
                break;
            case CSS_REMOVED:
                removeCSS(cssEvent.getStageID(), cssEvent.getUri());
                break;
            case CSS_REPLACED:
                replaceCSS(cssEvent.getStageID(), cssEvent.getUri(), cssEvent.getSource());
                break;
            default:
                break;
            }
        }
    }

    public void setActiveStage(StageID stageID) {
        if (stageID == null) {
            cssfxTabContentController.setMonitoredCSS(null);
        } else {
            ObservableList<MonitoredCSS> stageCSS = cssByStage.computeIfAbsent(stageID, sid -> FXCollections.observableArrayList());
            cssfxTabContentController.setMonitoredCSS(stageCSS);
        }
    }
    
    public void registerStage(StageID stageID) {
        cssByStage.computeIfAbsent(stageID, sid -> FXCollections.observableArrayList());
    }

    public void addCSS(StageID stageID, String uri, String source) {
        replaceCSS(stageID, uri, source);
    }

    public void removeCSS(StageID stageID, String uri) {
        ObservableList<MonitoredCSS> monitoredCSS = cssByStage.get(stageID);
        if (monitoredCSS != null) {
            monitoredCSS.removeIf(css -> css.getCSS().equals(uri));
        }
    }

    public void replaceCSS(StageID stageID, String uri, String source) {
        ObservableList<MonitoredCSS> monitoredCSS = cssByStage.get(stageID);
        if (monitoredCSS != null) {
            FilteredList<MonitoredCSS> existingCSS = monitoredCSS.filtered(css -> css.getCSS().equals(uri));
            
            if (!existingCSS.isEmpty()) {
                existingCSS.forEach(css -> css.mappedBy().set(source));
            } else  {
                MonitoredCSS css = new MonitoredCSS(uri);
                css.mappedBy().set(source);
                monitoredCSS.add(css);
            }
        }
    }

}
