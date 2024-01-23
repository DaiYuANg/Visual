package org.visual.model.component.wrapper;

import javafx.scene.control.Label;
import org.visual.model.component.font.FontManager;
import org.visual.model.component.theme.Theme;

public class ThemeLabel extends Label {
    public ThemeLabel() {
        setTextFill(Theme.current().normalTextColor());
    }

    public ThemeLabel(String text) {
        super(text);
        setTextFill(Theme.current().normalTextColor());
        FontManager.get().setFont(this);
    }
}
