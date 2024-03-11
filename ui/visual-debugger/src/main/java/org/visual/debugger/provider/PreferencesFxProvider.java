package org.visual.debugger.provider;

import com.dlsc.preferencesfx.PreferencesFx;
import com.dlsc.preferencesfx.model.Category;
import com.dlsc.preferencesfx.model.Group;
import com.dlsc.preferencesfx.model.Setting;
import jakarta.inject.Provider;
import javafx.beans.property.SimpleStringProperty;
import lombok.extern.slf4j.Slf4j;
import org.visual.debugger.dto.SaveClass;

@Slf4j
public class PreferencesFxProvider implements Provider<PreferencesFx> {

  @Override
  public PreferencesFx get() {
    return PreferencesFx.of(
        SaveClass.class,
        Category.of(
            "Category Title",
            Group.of("Group Title", Setting.of("Setting Title", new SimpleStringProperty()))));
  }
}
