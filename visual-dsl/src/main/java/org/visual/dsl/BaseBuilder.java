package org.visual.dsl;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.effect.Effect;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BaseBuilder<N extends Node> {
  protected final N node;

  public BaseBuilder<N> setDisable(boolean disabled) {
    node.setDisable(disabled);
    return this;
  }

  public BaseBuilder<N> setOnMouseClicked(
      javafx.event.EventHandler<javafx.scene.input.MouseEvent> handler) {
    node.setOnMouseClicked(handler);
    return this;
  }

  public BaseBuilder<N> setAlignment(javafx.geometry.Pos alignment) {
    if (node instanceof HBox hBox) {
      hBox.setAlignment(alignment);
      return this;
    }
    if (node instanceof BorderPane) {
      BorderPane.setAlignment(node, alignment);
      return this;
    }
    return this;
  }

  public BaseBuilder<N> setPadding(Insets insets) {
    if (node instanceof Region region) {
      region.setPadding(insets);
    }
    return this;
  }

  public BaseBuilder<N> setEffect(Effect effect) {
    node.setEffect(effect);
    return this;
  }

  public BaseBuilder<N> setBackground(Background background) {
    if (node instanceof Region region) {
      region.setBackground(background);
    }
    return this;
  }

  public BaseBuilder<N> setOpacity(double opacity) {
    node.setOpacity(opacity);
    return this;
  }

  public BaseBuilder<N> setVisibility(boolean visible) {
    node.setVisible(visible);
    return this;
  }

  public BaseBuilder<N> setStyle(String style) {
    node.setStyle(style);
    return this;
  }

  public BaseBuilder<N> setPosition(double x, double y) {
    node.setLayoutX(x);
    node.setLayoutY(y);
    return this;
  }

  public BaseBuilder<N> setSize(double width, double height) {
    node.resize(width, height);
    return this;
  }

  public N build() {
    return node;
  }
}
