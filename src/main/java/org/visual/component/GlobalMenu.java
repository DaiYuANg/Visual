package org.visual.component;

import io.avaje.inject.Lazy;
import jakarta.inject.Singleton;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import lombok.extern.slf4j.Slf4j;
import org.kordamp.ikonli.fluentui.FluentUiFilledAL;
import org.kordamp.ikonli.javafx.FontIcon;
import org.visual.dsl.MenuDSL;
import org.visual.i18n.I18n;
import org.visual.i18n.constant.Display;
import org.visual.view.SettingView;

@Singleton
@Slf4j
@Lazy
public class GlobalMenu extends MenuBar {

  private final Menu menu =
      MenuDSL.create(
          "File",
          menuBuilder -> {
            menuBuilder
                .addItem(I18n.INSTANCE.t(Display.FILE))
                .addItem(
                    I18n.INSTANCE.t(Display.SETTING),
                    new FontIcon(FluentUiFilledAL.LAUNCHER_SETTINGS_24),
                    event -> {
                      log.atInfo().log("Setting clicked");
                      new SettingView().showAndWait();
                    });
          });

  {
    setUseSystemMenuBar(true);
    getMenus().add(menu);
  }
}
