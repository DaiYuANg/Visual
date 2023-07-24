package org.visual.model.initializing;

import io.github.palexdev.materialfx.css.themes.MFXThemeManager;
import io.github.palexdev.materialfx.css.themes.Themes;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.visual.model.views.MainScene;
import org.visual.model.contexts.I18nContext;
import org.visual.model.i18n.I18nKeys;

@Slf4j
public class StageSetup {
    private final Stage stage;

    private final MainScene mainScene;
    MenuBar menuBar = new MenuBar();

    public StageSetup(Stage stage, MainScene mainScene) {
        this.stage = stage;
        this.mainScene = mainScene;
        setOnClose();
        setView();
        //        ApplicationContext.CONTEXT.getEventBus().consumer("test", System.err::println);
    }

    private void setOnClose() {
        stage.setOnCloseRequest(event -> {
            val alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle(I18nContext.I18N.getI18nResource(I18nKeys.CONFIRM.getValue()));
            alert.setHeaderText(I18nContext.I18N.getI18nResource(I18nKeys.CONFIRM_CLOSE_HEADER.getValue()));
            if (alert.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {
                stage.close();
                return;
            }
            event.consume();
        });
    }

    private void setView() {
        stage.centerOnScreen();
        stage.setTitle(System.getProperty("application.name"));
        stage.setScene(mainScene.getMainScene());
        MFXThemeManager.addOn(mainScene.getMainScene(), Themes.DEFAULT, Themes.LEGACY);
        stage.show();
    }
}
