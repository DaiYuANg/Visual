package org.visual.model.components.callbacks;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;

public class ProjectListCell extends ListCell<String> {
    private final HBox hbox = new HBox();

    {
        // 在HBox中添加内容，这里可以根据需要自定义
        Label label = new Label();
        label.textProperty().bind(itemProperty()); // 设置Label的文本

        Button button = new Button("删除");
        button.setOnAction(event -> {
            getListView().getItems().remove(getItem());
        });

        hbox.getChildren().addAll(label, button);
    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (item == null || empty) {
            setGraphic(null);
        } else {
            setGraphic(hbox);
        }
    }
}
