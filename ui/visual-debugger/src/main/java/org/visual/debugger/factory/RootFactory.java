package org.visual.debugger.factory;

import com.dlsc.preferencesfx.PreferencesFx;
import com.dlsc.preferencesfx.model.Category;
import com.dlsc.preferencesfx.model.Group;
import com.dlsc.preferencesfx.model.Setting;
import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import java.util.prefs.Preferences;
import javafx.beans.property.SimpleStringProperty;
import org.visual.debugger.dto.SaveClass;

@Factory
public class RootFactory {

  @Bean
  PreferencesFx preferencesFx() {
    return PreferencesFx.of(
        SaveClass.class,
        Category.of(
            "Category Title",
            Group.of("Group Title", Setting.of("Setting Title", new SimpleStringProperty()))));
  }

  @Bean
  Preferences preferences() {
    return Preferences.systemRoot();
  }
}
