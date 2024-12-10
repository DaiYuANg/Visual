package org.visual.app.listener;

import io.avaje.inject.PreDestroy;
import jakarta.inject.Singleton;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.visual.app.constant.PreferencesKey;

import java.util.prefs.Preferences;

@Singleton
@Slf4j
@RequiredArgsConstructor
public class StageWidthListener implements ChangeListener<Number> {

  private final Preferences preferences;

  @Override
  public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
    log.atTrace().log("Number1:{}", number);
    log.atTrace().log("Number2:{}", t1);
    preferences.putInt(PreferencesKey.STAGE_WIDTH, t1.intValue());
  }

  @SneakyThrows
  @PreDestroy
  void onShutdown() {
    preferences.flush();
  }
}
