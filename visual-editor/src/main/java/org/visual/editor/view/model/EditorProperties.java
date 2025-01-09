package org.visual.editor.view.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import lombok.Getter;
import org.visual.editor.constant.EditorElement;

import java.util.EnumMap;
import java.util.Map;

@Getter
public class EditorProperties {

  private final Double maxWidth = Double.MAX_VALUE;
  private final Double maxHeight = Double.MAX_VALUE;

  private final DoubleProperty northBoundValue = new SimpleDoubleProperty(15);

  public double getNorthBoundValue() {
    return northBoundValue.get();
  }

  public void setNorthBoundValue(Double newVal) {
    northBoundValue.set(newVal);
  }

  private final DoubleProperty southBoundValue = new SimpleDoubleProperty(15);

  public double getSouthBoundValue() {
    return southBoundValue.get();
  }

  public void setSouthBoundValue(Double newVal) {
    southBoundValue.set(newVal);
  }

  private final DoubleProperty eastBoundValue = new SimpleDoubleProperty(12);

  private final DoubleProperty westBoundValue = new SimpleDoubleProperty(12);

  private final BooleanProperty gridVisibleProperty = new SimpleBooleanProperty(this, "gridVisible");

  public Boolean getGridVisible() {
    return gridVisibleProperty.get();
  }

  public void setGridVisible(Boolean newVal) {
    gridVisibleProperty.set(newVal);
  }

  private final BooleanProperty snapToGrid = new SimpleBooleanProperty(this, "snapToGrid");
  private final DoubleProperty gridSpacingProperty = new SimpleDoubleProperty(this, "gridSpacing", 12);

  private final Map<EditorElement, BooleanProperty> readOnly = new EnumMap<>(EditorElement.class);
  private final ObservableMap<String, String> customProperties = FXCollections.observableHashMap();

}
