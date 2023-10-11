package org.visual.model.di.providers;

import com.dustinredmond.fxtrayicon.FXTrayIcon;
import com.google.inject.Inject;
import com.google.inject.Provider;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.util.Arrays;

@Slf4j
public class FxTrayIconProvider implements Provider<FXTrayIcon> {

    @Inject
    private Stage stage;

    @Override
    public FXTrayIcon get() {
        return new FXTrayIcon(stage);
    }
}
