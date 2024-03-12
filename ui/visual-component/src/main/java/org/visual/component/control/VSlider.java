package org.visual.component.control;

import java.util.function.DoubleFunction;
import javafx.beans.property.DoubleProperty;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import lombok.Setter;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.visual.component.algebradata.DoubleData;
import org.visual.component.animation.AnimationGraph;
import org.visual.component.animation.AnimationGraphBuilder;
import org.visual.component.animation.AnimationNode;
import org.visual.component.constant.DisplayFormat;
import org.visual.component.handler.DragHandler;
import org.visual.component.loading.VProgressBar;
import org.visual.component.shapes.ClickableCircle;
import org.visual.component.util.EventUtil;

public class VSlider extends Pane {
  public static final int BUTTON_RADIUS = 15;

  private final SliderDirection sliderDirection;
  private final Group positionGroup = new Group();
  private final Pane rotatePane = new Pane();
  private final Rotate rotate = new Rotate();
  private final VProgressBar bar = new VProgressBar();
  private final ClickableCircle button =
      new ClickableCircle(Color.ALICEBLUE, Color.ALICEBLUE, Color.ALICEBLUE) {
        {
          setStroke(Color.ALICEBLUE);
        }
      };
  @Setter private EventHandler<Event> onAction = null;
  private final Label buttonLabel = new Label();
  private DoubleFunction<String> valueTransform = DisplayFormat.DOUBLE_FORMAT.getFormat()::format;
  private final AnimationNode<DoubleData> labelInvisible =
      new AnimationNode<>("invisible", new DoubleData(0));
  private final AnimationNode<DoubleData> labelVisible =
      new AnimationNode<>("visible", new DoubleData(1));
  private final AnimationGraph<DoubleData> labelAnimation =
      AnimationGraphBuilder.simpleTwoNodeGraph(labelInvisible, labelVisible, 300)
          .setApply((from, to, d) -> buttonLabel.setOpacity(d.value))
          .build(labelInvisible);

  public VSlider() {
    this(SliderDirection.LEFT_TO_RIGHT);
  }

  public VSlider(SliderDirection sliderDirection) {
    this.sliderDirection = sliderDirection;

    rotate.setAngle(sliderDirection.rotation);
    rotatePane.getTransforms().add(rotate);
    positionGroup.getChildren().add(rotatePane);
    getChildren().add(positionGroup);
    getChildren().add(buttonLabel);

    if (sliderDirection.isHorizontal) {
      setMinHeight(BUTTON_RADIUS * 2);
      setPrefHeight(BUTTON_RADIUS * 2);
      setMaxHeight(BUTTON_RADIUS * 2);
    } else {
      setMinWidth(BUTTON_RADIUS * 2);
      setPrefWidth(BUTTON_RADIUS * 2);
      setMaxWidth(BUTTON_RADIUS * 2);
    }
    rotatePane.setMinHeight(BUTTON_RADIUS * 2);
    rotatePane.setPrefHeight(BUTTON_RADIUS * 2);
    rotatePane.setMaxHeight(BUTTON_RADIUS * 2);

    button.setRadius(BUTTON_RADIUS);
    button.setLayoutX(BUTTON_RADIUS);

    EventUtil.setOnMouseEntered(button, e -> labelAnimation.play(labelVisible));
    EventUtil.setOnMouseExited(button, e -> labelAnimation.play(labelInvisible));
    button.layoutXProperty().addListener(ob -> updateLabel());

    bar.progressProperty()
        .addListener(
            (ob, old, now) -> {
              if (now == null) return;
              var p = now.doubleValue();
              button.setLayoutX(bar.getLength() * p + BUTTON_RADIUS);
            });
    bar.setLayoutX(BUTTON_RADIUS);

    var dragHandler =
        new DragHandler() {
          @Override
          protected void set(double x, double y) {
            setPercentage((x - BUTTON_RADIUS) / bar.getLength());
          }

          @Contract(" -> new")
          @Override
          protected double @NotNull [] get() {
            return new double[] {button.getLayoutX(), 0};
          }

          @Override
          protected double calculateDeltaX(double deltaX, double deltaY) {
            return switch (sliderDirection) {
              case LEFT_TO_RIGHT -> deltaX;
              case RIGHT_TO_LEFT -> -deltaX;
              case TOP_TO_BOTTOM -> deltaY;
              case BOTTOM_TO_TOP -> -deltaY;
            };
          }

          @Override
          protected void consume(@NotNull MouseEvent e) {
            e.consume();
          }
        };
    double[] tmp = new double[] {0};
    EventUtil.setOnMousePressed(
        button,
        e -> {
          tmp[0] = button.getLayoutX();
          dragHandler.handle(e);
        });
    button.setOnAction(
        e -> {
          if (tmp[0] != button.getLayoutX()) {
            return;
          }
          if (onAction != null) {
            onAction.handle(e);
          }
        });
    button.setOnMouseDragged(dragHandler);

    rotatePane.getChildren().addAll(bar, button);
  }

  public double getLength() {
    return bar.getLength() + BUTTON_RADIUS * 2;
  }

  public void setLength(double length) {
    bar.setLength(length - BUTTON_RADIUS * 2);
    if (sliderDirection.isHorizontal) {
      setMinWidth(length);
      setPrefWidth(length);
      setMaxWidth(length);
    } else {
      setMinHeight(length);
      setPrefHeight(length);
      setMaxHeight(length);
    }
    rotatePane.setMinWidth(length);
    rotatePane.setPrefWidth(length);
    rotatePane.setMaxWidth(length);
    rotate.setPivotX(length / 2);
    if (sliderDirection.isVertical) {
      positionGroup.setLayoutX(-length / 2 + BUTTON_RADIUS);
      positionGroup.setLayoutY(length / 2);
    } else {
      positionGroup.setLayoutY(BUTTON_RADIUS);
    }
    updateLabel();
  }

  public void setValueTransform(DoubleFunction<String> valueTransform) {
    if (valueTransform == null) {
      valueTransform = DisplayFormat.DOUBLE_FORMAT.getFormat()::format;
    }
    this.valueTransform = valueTransform;
    updateLabel();
  }

  private void updateLabel() {
    var str = this.valueTransform.apply(getPercentage());
    buttonLabel.setText(str);
    if (sliderDirection.isHorizontal) {
      if (sliderDirection == SliderDirection.LEFT_TO_RIGHT) {
        buttonLabel.setLayoutX(button.getLayoutX() + BUTTON_RADIUS + 5);
      } else {
        buttonLabel.setLayoutX(getLength() - button.getLayoutX() + BUTTON_RADIUS + 5);
      }
      buttonLabel.setLayoutY(BUTTON_RADIUS + 5);
    } else {
      buttonLabel.setLayoutX(BUTTON_RADIUS + 5);
      if (sliderDirection == SliderDirection.TOP_TO_BOTTOM) {
        buttonLabel.setLayoutY(button.getLayoutX() + BUTTON_RADIUS + 5);
      } else {
        buttonLabel.setLayoutY(getLength() - button.getLayoutX() + BUTTON_RADIUS + 5);
      }
    }
  }

  public DoubleProperty percentageProperty() {
    return bar.progressProperty();
  }

  public double getPercentage() {
    return bar.getProgress();
  }

  public void setPercentage(double p) {
    bar.setProgress(p);
  }
}
