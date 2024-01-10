package org.visual.model.ui.control;

import javafx.scene.control.Button;
import org.kordamp.ikonli.Ikon;
import org.kordamp.ikonli.javafx.FontIcon;

public class IconButton extends Button {

    private final FontIcon fontIcon;

    public IconButton(Ikon icon) {
        this.fontIcon = new FontIcon(icon);
        this.initialize();
    }

    private void initialize() {
        this.setGraphic(fontIcon);
    }
}
