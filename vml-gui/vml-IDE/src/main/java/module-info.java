module org.visual.model {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.web;
	requires javafx.swing;
	requires static lombok;
	requires com.google.common;
	requires io.vertx.core;
	requires org.slf4j;
	requires jakarta.inject;
	requires org.jetbrains.annotations;
	requires com.google.guice;
	requires java.prefs;
	requires atlantafx.base;
	requires com.dlsc.preferencesfx;
	requires com.google.gson;
	requires java.compiler;
	requires com.github.oshi;
	requires org.apache.commons.io;
	requires dev.dirs;
	requires com.dustinredmond.fxtrayicon;

	opens org.visual.model to javafx.fxml;

	exports org.visual.model;
	exports org.visual.model.controllers;

	opens org.visual.model.controllers to javafx.fxml, com.google.guice;

	exports org.visual.model.views;

	opens org.visual.model.views to javafx.fxml;

	exports org.visual.model.i18n;

	opens org.visual.model.i18n to javafx.fxml;

	exports org.visual.model.initializing;

	opens org.visual.model.initializing to javafx.fxml;

	exports org.visual.model.contexts;

	opens org.visual.model.contexts to javafx.fxml;
	opens org.visual.model.di.modules to com.google.guice;
	opens org.visual.model.services to com.google.guice;
	opens org.visual.model.services.impl to com.google.guice;
	opens org.visual.model.di.providers to com.google.guice;

	exports org.visual.model.di;

	opens org.visual.model.di to javafx.fxml;
    exports org.visual.model.views.scene;
    opens org.visual.model.views.scene to javafx.fxml;
}
