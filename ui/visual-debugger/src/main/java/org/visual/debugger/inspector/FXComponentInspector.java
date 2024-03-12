package org.visual.debugger.inspector;

import java.util.List;
import java.util.Optional;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import lombok.val;
import org.jetbrains.annotations.NotNull;

class FXComponentInspector extends AbstractComponentInspector<Node> {

  @Override
  protected Node getParent(Node component) {
    if (component == null) {
      return null;
    }
    return component.getParent();
  }

  @Override
  protected Optional<Node> createFieldNameComponent(Node component) {
    return new ObjectMetadataExtractor<>(component, this)
        .getDeclaredFieldNameInParent()
        .map(
            fieldName -> {
              Label result = new Label(fieldName);
              result.getStyleClass().add(CSSStyleClass.FIELD_COMPONENT.getCssClassName());
              return result;
            });
  }

  @Override
  protected Node createClassComponent(@NotNull Node component) {
    val result = new Label(component.getClass().getName());
    result.getStyleClass().add(CSSStyleClass.CLASS_COMPONENT.getCssClassName());
    return result;
  }

  @Override
  protected Node createStylesComponent(@NotNull Node component) {
    var labelText = String.join(", ", component.getStyleClass());
    if (labelText.isBlank()) {
      labelText = "(empty)";
    }
    val result = new Label(labelText);
    result.getStyleClass().add(CSSStyleClass.STYLES_COMPONENT.getCssClassName());
    return result;
  }

  @Override
  protected Node createComponentDetailsPanel(@NotNull ComponentDetails<Node> details) {
    val result = new HBox(details.getClassComponent(), details.getStylesComponent());
    result.getStyleClass().add(CSSStyleClass.COMPONENT_DETAILS.getCssClassName());
    result.relocate(details.getLocationX(), details.getLocationY());
    details
        .getFieldNameComponent()
        .ifPresent(fieldNameComponent -> result.getChildren().add(fieldNameComponent));
    return result;
  }

  @Override
  protected void buildCascade(@NotNull List<ComponentDetails<Node>> hierarchy) {
    for (int componentLevel = 0, componentIndex = hierarchy.size() - 1;
        componentIndex >= 0;
        componentIndex--, componentLevel++) {
      ComponentDetails<Node> componentChild = hierarchy.get(componentIndex);
      componentChild.setLocation(
          componentLevel * HORIZONTAL_SPACING, componentLevel * (VERTICAL_SPACING + 5));
    }
  }
}
