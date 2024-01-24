package org.visual.model.component.container;

import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.SplitPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class HolyGrailLayout extends VBox {
    private final String styleFile = "/split.css";

    private final SplitPane hSplitPane = new SplitPane();
    private final SplitPane vSplitPane = new SplitPane();
    private final HBox footer = new HBox();
    private final HBox header = new HBox();

    public HolyGrailLayout() {
        setVgrow(this, Priority.ALWAYS);

        hSplitPane.setOrientation(Orientation.HORIZONTAL);
        setFocused(true);
        setVgrow(this, Priority.ALWAYS);
        hSplitPane.setDividerPositions(0.2, 0.9, 0.1);

        vSplitPane.setOrientation(Orientation.VERTICAL);
        vSplitPane.setDividerPositions(0.9, 0.05);

        footer.getChildren().addAll();
        header.getChildren().add(new ToolBar());

        setVgrow(vSplitPane, Priority.ALWAYS);
        getChildren().addAll(header, vSplitPane);

        vSplitPane.lookupAll(".split-pane-divider").forEach(divider -> {
            divider.setStyle("-fx-background-color: red;");
        });

        getStylesheets().add(styleFile);
    }

    public void addItem(Node... nodes) {
        hSplitPane.getItems().addAll(nodes);
    }
}
