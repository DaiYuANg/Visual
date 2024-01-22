package org.visual.model.component.control;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Button;
import lombok.*;
import org.kordamp.ikonli.fluentui.FluentUiRegularAL;
import org.kordamp.ikonli.javafx.FontIcon;
import org.visual.model.annotation.FxProperty;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class IconButton extends Button {

    @FxProperty
    private final ObjectProperty<FluentUiRegularAL> icon = new SimpleObjectProperty<>();

    {
        icon.addListener((observableValue, fluentUiRegularAL, t1) -> setGraphic(new FontIcon(t1)));
    }

    public final FluentUiRegularAL getIcon() {
        return icon.get();
    }

    public IconButton setIcon(FluentUiRegularAL icon) {
        this.icon.set(icon);
        return this;
    }
}
