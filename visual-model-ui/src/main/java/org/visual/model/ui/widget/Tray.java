package org.visual.model.ui.widget;

import javafx.application.Platform;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import java.util.Collection;

public class Tray {
    private final SystemTray tray = SystemTray.getSystemTray();
    private final Image image;
    private final PopupMenu popup;

    @SneakyThrows
    public Tray(@NotNull Path iconPath, Stage primaryStage, @NotNull Collection<MenuItem> menuItems) {
        this.image = Toolkit.getDefaultToolkit().getImage(iconPath.toAbsolutePath().toString());
        this.popup = new PopupMenu();
        menuItems.forEach(popup::add);

        ActionListener trayListener = e -> {
            if (primaryStage.isShowing()) {
                Platform.runLater(primaryStage::hide);
            } else {
                Platform.runLater(primaryStage::show);
            }
        };

        TrayIcon trayIcon = new TrayIcon(image, "Tray Demo", popup);
        trayIcon.addActionListener(trayListener);

        Platform.setImplicitExit(false);
        tray.add(trayIcon);
    }
}
