package org.visual.model.views;

import com.dlsc.preferencesfx.PreferencesFx;
import com.dlsc.preferencesfx.model.Category;
import com.dlsc.preferencesfx.model.Group;
import com.dlsc.preferencesfx.model.Setting;
import javafx.beans.property.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SettingsView {

	StringProperty stringProperty = new SimpleStringProperty("String");
	BooleanProperty booleanProperty = new SimpleBooleanProperty(true);
	IntegerProperty integerProperty = new SimpleIntegerProperty(12);
	DoubleProperty doubleProperty = new SimpleDoubleProperty(6.5);

	private final PreferencesFx settings;

	SettingsView() {
		settings =
				PreferencesFx.of(SettingsView.class, // Save class (will be used to reference saved values of Settings to)
						Category.of("Category title 1",
								Setting.of("Setting title 1", stringProperty), // creates a group automatically
								Setting.of("Setting title 2", booleanProperty) // which contains both settings
						),
						Category.of("Category title 2")
								.expand()                                       // Expand the parent category in the tree-view
								.subCategories( // adds a subcategory to "Category title 2"
										Category.of("Category title 3",
												Group.of("Group title 1",
														Setting.of("Setting title 3", integerProperty)
												),
												Group.of( // group without title
														Setting.of("Setting title 3", doubleProperty)
												)
										)
								)
				);

	}

	public void openSettings() {
		 settings.show(true);
	}
}
