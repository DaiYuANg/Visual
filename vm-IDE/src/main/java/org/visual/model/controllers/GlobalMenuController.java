package org.visual.model.controllers;

import com.dlsc.preferencesfx.PreferencesFx;
import com.dlsc.preferencesfx.model.Category;
import com.dlsc.preferencesfx.model.Group;
import com.dlsc.preferencesfx.model.Setting;
import javafx.beans.property.*;
import javafx.event.ActionEvent;
import javafx.scene.control.MenuItem;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.visual.model.base.Controller;

@Slf4j
public class GlobalMenuController implements Controller {
    public MenuItem newProject;

    public void newProject(ActionEvent actionEvent) {
        val stage = new Stage();
        stage.centerOnScreen();
        DirectoryChooser directoryChooser = new DirectoryChooser();
    }

    public void openSettings(ActionEvent actionEvent) {
        StringProperty stringProperty = new SimpleStringProperty("String");
        BooleanProperty booleanProperty = new SimpleBooleanProperty(true);
        IntegerProperty integerProperty = new SimpleIntegerProperty(12);
        DoubleProperty doubleProperty = new SimpleDoubleProperty(6.5);

        PreferencesFx preferencesFx =
                PreferencesFx.of(TestStarter.class, // Save class (will be used to reference saved values of Settings to)
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
        preferencesFx.show(true);
//        Settings.SETTINGS.openSettings();
    }

    @Override
    public void initialize() {

    }
}
