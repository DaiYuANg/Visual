package org.visual.model.views;

import javafx.beans.property.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum Settings {
	SETTINGS;

	// @Getter
	// private final PreferencesFx settings;
	//
	// private final StringProperty stringProperty = new
	// SimpleStringProperty("String");
	// private final BooleanProperty booleanProperty = new
	// SimpleBooleanProperty(true);
	// private final IntegerProperty integerProperty = new
	// SimpleIntegerProperty(12);
	// private final DoubleProperty doubleProperty = new SimpleDoubleProperty(6.5);

	Settings() {
		// settings = PreferencesFx.of(TestStarter.class, // Save class (will be used to
		// reference saved values of
		// Settings
		// // to)
		// Category.of("Category title 1", Setting.of("Setting title 1",
		// stringProperty), // creates a group
		// // automatically
		// Setting.of("Setting title 2", booleanProperty) // which contains both
		// settings
		// ), Category.of("Category title 2").expand() // Expand the parent category in
		// the tree-view
		// .subCategories( // adds a subcategory to "Category title 2"
		// Category.of("Category title 3",
		// Group.of("Group title 1", Setting.of("Setting title 3", integerProperty)),
		// Group.of( // group without title
		// Setting.of("Setting title 3", doubleProperty)))));
	}

	public void openSettings() {
		// settings.addEventHandler(PreferencesFxEvent.EVENT_PREFERENCES_SAVED, event ->
		// {
		// });
		// settings.show(true);
	}
}
