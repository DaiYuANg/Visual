package org.visual.model.ui.control;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import lombok.*;
import lombok.experimental.Accessors;
import org.kordamp.ikonli.fluentui.FluentUiRegularAL;
import org.kordamp.ikonli.javafx.FontIcon;

import java.beans.BeanProperty;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class IconButton extends Button {

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
