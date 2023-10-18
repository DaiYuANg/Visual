module org.visual.model.language.gui.ide {
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
	requires org.kordamp.ikonli.core;
	requires org.kordamp.ikonli.javafx;
	requires org.kordamp.ikonli.fontawesome;
	requires org.kordamp.ikonli.fontawesome5;
	requires org.jetbrains.annotations;
	requires com.google.guice;
	requires com.google.guice.extensions.grapher;
	requires com.google.guice.extensions.assistedinject;
	requires java.prefs;
	requires atlantafx.base;
	requires com.google.gson;
	requires java.compiler;
	requires com.github.oshi;
	requires org.apache.commons.io;
	requires dev.dirs;
	requires com.dustinredmond.fxtrayicon;
	requires io.ebean;
	requires io.ebean.jackson.mapper;
	requires io.ebean.migration;
	requires com.fasterxml.jackson.databind;
	requires org.visual.model.gui.components;
	requires io.github.eckig.grapheditor.api;
	requires io.github.eckig.grapheditor.model;
	requires io.github.eckig.grapheditor.core;

	opens org.visual.model.language.gui.ide to javafx.fxml, com.google.guice;

	exports org.visual.model.language.gui.ide;
	exports org.visual.model.language.gui.ide.controllers;

	opens org.visual.model.language.gui.ide.controllers to javafx.fxml, com.google.guice;

	exports org.visual.model.language.gui.ide.views.scene;
	exports org.visual.model.language.gui.ide.views.stages;

	opens org.visual.model.language.gui.ide.views.stages to javafx.fxml;
	opens org.visual.model.language.gui.ide.views.scene to javafx.fxml;

	exports org.visual.model.language.gui.ide.i18n;

	opens org.visual.model.language.gui.ide.i18n to javafx.fxml;

	exports org.visual.model.language.gui.ide.lifecycle;

	opens org.visual.model.language.gui.ide.lifecycle to javafx.fxml, com.google.guice;

	exports org.visual.model.language.gui.ide.contexts;

	opens org.visual.model.language.gui.ide.contexts to javafx.fxml;
	opens org.visual.model.language.gui.ide.di.modules to com.google.guice;
	opens org.visual.model.language.gui.ide.di.providers to com.google.guice;
	opens org.visual.model.language.gui.ide.verticles to com.google.guice;

	exports org.visual.model.language.gui.ide.di;

	opens org.visual.model.language.gui.ide.di to javafx.fxml;

	exports org.visual.model.language.gui.ide.lifecycle.managers;

	opens org.visual.model.language.gui.ide.lifecycle.managers to com.google.guice, javafx.fxml;
	opens org.visual.model.language.gui.ide.functional to com.google.guice;
	opens org.visual.model.language.gui.ide.service to com.google.guice;
}
