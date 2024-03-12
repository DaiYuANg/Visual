package org.visual.context;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import lombok.extern.slf4j.Slf4j;
import org.visual.constant.AvailableFeature;

@Slf4j
public enum CreationContext {
  INSTANCE;

  private final SimpleObjectProperty<AvailableFeature> featureSimpleObjectProperty =
      new SimpleObjectProperty<>(AvailableFeature.OBJECT_ORIENTED);

  public void addListener(ChangeListener<AvailableFeature> changeListener) {
    featureSimpleObjectProperty.addListener(changeListener);
  }

  public void set(AvailableFeature feature) {
    featureSimpleObjectProperty.setValue(feature);
  }

  public AvailableFeature get() {
    return featureSimpleObjectProperty.get();
  }
}
