package org.visual.model.ui.control;

import javafx.scene.control.Button;
import org.kordamp.ikonli.Ikon;
import org.kordamp.ikonli.javafx.FontIcon;

public class IconButton extends Button {

    public IconButton(Ikon icon) {
        FontIcon fontIcon = new FontIcon(icon);
        fontIcon.setIconSize(20);
        setGraphic(fontIcon);
    }
}
