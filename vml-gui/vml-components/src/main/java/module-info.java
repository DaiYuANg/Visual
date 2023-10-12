module org.visual.model.gui.components {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires static lombok;
	requires org.slf4j;
	requires org.jetbrains.annotations;

	exports org.visual.model.components.controls;
	exports org.visual.model.components.container;
	exports org.visual.model.components.frame;
	exports org.visual.model.components.callbacks;
}
