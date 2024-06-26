package org.visual.view;

import io.avaje.inject.Lazy;
import jakarta.inject.Singleton;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.SplitPane;
import lombok.val;
import org.visual.config.model.AppearanceConfig;

@Singleton
@Lazy
public class SettingView extends Dialog<AppearanceConfig> {

  private final DialogPane dialogPane = new DialogPane();

  {
    val splitView = new SplitPane();
  }
}
