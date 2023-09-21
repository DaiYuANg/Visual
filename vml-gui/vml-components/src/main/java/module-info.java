module org.visual.model.gui.components {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
    requires static lombok;
	requires org.slf4j;
    requires org.jetbrains.annotations;

    opens org.visual.model.components;

	exports org.visual.model.components;
}
