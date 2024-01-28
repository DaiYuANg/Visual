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

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import javafx.scene.Node;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.VBox;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.kordamp.ikonli.fontawesome5.FontAwesomeRegular;
import org.kordamp.ikonli.javafx.FontIcon;
import org.visual.debugger.api.ContextMenuContainer;
import org.visual.debugger.details.Detail;
import org.visual.debugger.details.DetailPaneType;
import org.visual.debugger.view.ScenicViewGui;
import org.visual.debugger.view.tabs.details.GDetailPane;


/**
 *
 */
@Slf4j
public class DetailsTab extends Tab implements ContextMenuContainer {

    public static final String TAB_NAME = "Details";

    List<GDetailPane> gDetailPanes = new ObjectArrayList<>();

    public static boolean showDefaultProperties = true;

    private final Consumer<String> loader;
    private final ScenicViewGui scenicView;

    VBox vbox;

    Menu menu;

    MenuItem dumpDetails;

    public DetailsTab(final ScenicViewGui view, final Consumer<String> loader) {
        super(TAB_NAME);
        this.scenicView = view;
        this.loader = loader;

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setFitToWidth(true);
        vbox = new VBox();
        vbox.setFillWidth(true);
        scrollPane.setContent(vbox);
        getStyleClass().add("all-details-pane");
        setGraphic(new FontIcon(FontAwesomeRegular.BOOKMARK));
        setContent(scrollPane);
        setClosable(false);
    }

    public void setShowDefaultProperties(final boolean show) {
        showDefaultProperties = show;
    }

    public void filterProperties(final String text) {
        gDetailPanes.forEach(type -> {
            type.filterProperties(text);
            updatedDetailPane(type);
        });
    }

    public void updateDetails(final DetailPaneType type, final String paneName, final List<Detail> details, final GDetailPane.RemotePropertySetter setter) {
        final GDetailPane pane = getPane(type, paneName);
        pane.updateDetails(details, setter);
        updatedDetailPane(pane);
    }

    private void updatedDetailPane(final @NotNull GDetailPane pane) {
        boolean detailVisible = false;
        for (final Node gridChild : pane.gridpane.getChildren()) {
            detailVisible = gridChild.isVisible();
            if (detailVisible)
                break;
        }
        pane.setExpanded(detailVisible);
        pane.setManaged(detailVisible);
        pane.setVisible(detailVisible);
        updateDump();
    }

    public void updateDetail(final DetailPaneType type, final String paneName, final Detail detail) {
        getPane(type, paneName).updateDetail(detail);
    }

    private GDetailPane getPane(final DetailPaneType type, final String paneName) {
        GDetailPane pane = null;
        boolean found = false;
        for (GDetailPane gDetailPane : gDetailPanes) {
            if (gDetailPane.type == type) {
                found = true;
                pane = gDetailPane;
                break;
            }
        }
        if (!found) {
            pane = new GDetailPane(scenicView, type, paneName, loader);
            gDetailPanes.add(pane);
            vbox.getChildren().add(pane);
        }
        return pane;
    }

    @Override
    public Menu getMenu() {
        if (menu == null) {
            menu = new Menu("Details");

            // --- copy to clipboard
            dumpDetails = new MenuItem("Copy Details to Clipboard");
            dumpDetails.setOnAction(event -> {
                val sb = gDetailPanes.stream().map(String::valueOf).collect(Collectors.joining());
                val clipboard = Clipboard.getSystemClipboard();
                val content = new ClipboardContent();
                content.putString(sb);
                clipboard.setContent(content);
            });
            updateDump();
            menu.getItems().addAll(dumpDetails);

            // --- show default properties
            final CheckMenuItem showDefaultProperties = scenicView.buildCheckMenuItem("Show Default Properties", "Show default properties",
                    "Hide default properties", "showDefaultProperties", Boolean.TRUE);
            showDefaultProperties.selectedProperty().addListener(arg0 -> {
                setShowDefaultProperties(showDefaultProperties.isSelected());
                val selected = scenicView.getSelectedNode();
                scenicView.configurationUpdated();
                scenicView.setSelectedNode(scenicView.activeStage, selected);
            });
            setShowDefaultProperties(showDefaultProperties.isSelected());
            menu.getItems().addAll(showDefaultProperties);

            // --- show css properties
            final CheckMenuItem showCSSProperties = scenicView.buildCheckMenuItem("Show CSS Properties", "Show CSS properties", "Hide CSS properties",
                    "showCSSProperties", Boolean.FALSE);
            showCSSProperties.selectedProperty().addListener(arg0 -> {
                scenicView.configuration.setCSSPropertiesDetail(showCSSProperties.isSelected());
                val selected = scenicView.getSelectedNode();
                scenicView.configurationUpdated();
                scenicView.setSelectedNode(scenicView.activeStage, selected);
            });
            scenicView.configuration.setCSSPropertiesDetail(showCSSProperties.isSelected());
            menu.getItems().addAll(showCSSProperties);
        }
        return menu;
    }

    private void updateDump() {
        boolean anyVisible = gDetailPanes.stream().anyMatch(Node::isVisible);
        dumpDetails.setDisable(!anyVisible);
    }
}
