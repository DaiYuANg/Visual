module org.visual.model.ui {
    requires javafx.graphics;
    requires org.slf4j;
    requires kotlin.stdlib;
    requires javafx.fxml;

    opens org.visual.model.ui to javafx.fxml;
    exports org.visual.model.ui;
}