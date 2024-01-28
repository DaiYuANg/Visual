package org.visual.component.shapes;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import org.visual.component.algebradata.ColorData;
import org.visual.component.animation.AnimationGraph;
import org.visual.component.animation.AnimationGraphBuilder;
import org.visual.component.animation.AnimationNode;
import org.visual.component.control.click.ClickEventHandler;

public class ClickableCircle extends Circle {
  private EventHandler<?> handler;

  public ClickableCircle(Color normalColor, Color hoverColor, Color downColor) {
    AnimationNode<ColorData> normal = new AnimationNode<>("normal", new ColorData(normalColor));
    AnimationNode<ColorData> hover = new AnimationNode<>("hover", new ColorData(hoverColor));
    AnimationNode<ColorData> down = new AnimationNode<>("down", new ColorData(downColor));
    AnimationGraph<ColorData> animation =
        new AnimationGraphBuilder<ColorData>()
            .addNode(normal)
            .addNode(hover)
            .addNode(down)
            .addTwoWayEdge(normal, hover, 300)
            .setApply((from, to, d) -> setFill(d.getColor()))
            .build(normal);

    setStrokeWidth(0.5);

    setCursor(Cursor.HAND);

    var clickHandler =
        new ClickEventHandler() {
          @Override
          protected void onMouseEntered() {
            animation.play(hover);
          }

          @Override
          protected void onMouseExited() {
            animation.play(normal);
          }

          @Override
          protected void onMousePressed() {
            animation.stopAndSetNode(down);
          }

          @Override
          protected void onMouseReleased() {
            animation.stopAndSetNode(hover);
          }

          @Override
          protected void onMouseClicked() {
            clicked();
          }
        };
    setOnMouseEntered(clickHandler);
    setOnMouseExited(clickHandler);
    setOnMousePressed(clickHandler);
    setOnMouseReleased(clickHandler);
  }

  public void setOnAction(EventHandler<?> handler) {
    this.handler = handler;
  }

  private void clicked() {
    var handler = this.handler;
    if (handler != null) {
      handler.handle(null);
    }
  }
}
