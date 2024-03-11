package org.visual.context;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import lombok.extern.slf4j.Slf4j;
import org.visual.constant.AvailableFeature;

@Slf4j
public enum CreationContext {
  INSTANCE;

  private final SimpleObjectProperty<AvailableFeature> simpleStringProperty =
      new SimpleObjectProperty<>(AvailableFeature.ER);

  public void addListener(ChangeListener<AvailableFeature> changeListener) {
    simpleStringProperty.addListener(changeListener);
  }

  public void set(AvailableFeature feature) {
    simpleStringProperty.setValue(feature);
  }
}
