package org.visual.model.ui.widget;

import javafx.application.Platform;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;

public class Tray {
    private final SystemTray tray = SystemTray.getSystemTray();
    private final Image image;
    private final PopupMenu popup = new PopupMenu();

    @SneakyThrows
    public Tray(@NotNull Path iconPath, Stage primaryStage, @NotNull Collection<MenuItem> menuItems) {
        this.image = Toolkit.getDefaultToolkit().getImage(iconPath.toAbsolutePath().toString());
        menuItems.forEach(popup::add);
        ActionListener trayListener = e -> {
            if (primaryStage.isShowing()) {
                Platform.runLater(primaryStage::hide);
            } else {
                Platform.runLater(primaryStage::show);
            }
        };
        Platform.setImplicitExit(false);
        TrayIcon trayIcon = new TrayIcon(image, "Tray Demo", popup);
        trayIcon.addActionListener(trayListener);

        tray.add(trayIcon);
    }

    @SneakyThrows
    public Tray(@NotNull Path iconPath, Stage primaryStage) {
        this.image = Toolkit.getDefaultToolkit().getImage(iconPath.toAbsolutePath().toString());
        ActionListener trayListener = e -> {
            if (primaryStage.isShowing()) {
                Platform.runLater(primaryStage::hide);
            } else {
                Platform.runLater(primaryStage::show);
            }
        };
        Platform.setImplicitExit(false);
        TrayIcon trayIcon = new TrayIcon(image, "Tray Demo", popup);
        trayIcon.addActionListener(trayListener);

        tray.add(trayIcon);
    }
}
