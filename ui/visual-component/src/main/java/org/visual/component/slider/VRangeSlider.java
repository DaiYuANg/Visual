package org.visual.component.slider;

import java.util.function.DoubleFunction;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.DoublePropertyBase;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import lombok.Getter;
import lombok.Setter;
import org.visual.component.algebradata.DoubleData;
import org.visual.component.animation.AnimationGraph;
import org.visual.component.animation.AnimationGraphBuilder;
import org.visual.component.animation.AnimationNode;
import org.visual.component.constant.DisplayFormat;
import org.visual.component.handler.DragHandler;
import org.visual.component.shapes.ClickableCircle;
import org.visual.component.shapes.VLine;
import org.visual.component.util.FXUtils;

public class VRangeSlider extends Pane {
  private static final double lineRadius = 1;
  private static final double lineWidth = lineRadius * 2;
  public static final int BUTTON_RADIUS = 15;

  private final SliderDirection sliderDirection;
  private final Group positionGroup = new Group();
  private final Pane rotatePane = new Pane();
  private final Rotate rotate = new Rotate();

  @Getter private double length;
  private double rangeMin;
  private double rangeMax;
  private final VLine backgroundLine = new VLine(lineWidth);
  private final VLine rangeLine = new VLine(lineWidth);

  private final ClickableCircle buttonMin =
      new ClickableCircle(Color.ALICEBLUE, Color.ALICEBLUE, Color.ALICEBLUE) {
        {
          setStroke(Color.ALICEBLUE);
        }
      };
  private final ClickableCircle buttonMax =
      new ClickableCircle(Color.ALICEBLUE, Color.ALICEBLUE, Color.ALICEBLUE) {
        {
          setStroke(Color.ALICEBLUE);
        }
      };

  @Setter private EventHandler<Event> minOnAction = null;
  @Setter private EventHandler<Event> maxOnAction = null;

  private final Label minButtonLabel = new Label();
  private final Label maxButtonLabel = new Label();
  private DoubleFunction<String> minValueTransform =
      DisplayFormat.DOUBLE_FORMAT.getFormat()::format;
  private DoubleFunction<String> maxValueTransform =
      DisplayFormat.DOUBLE_FORMAT.getFormat()::format;
  private final AnimationNode<DoubleData> labelInvisible =
      new AnimationNode<>("invisible", new DoubleData(0));
  private final AnimationNode<DoubleData> labelVisible =
      new AnimationNode<>("visible", new DoubleData(1));
  private final AnimationGraph<DoubleData> labelAnimation =
      AnimationGraphBuilder.simpleTwoNodeGraph(labelInvisible, labelVisible, 300)
          .setApply(
              (from, to, d) -> {
                minButtonLabel.setOpacity(d.value);
                maxButtonLabel.setOpacity(d.value);
              })
          .build(labelInvisible);

  public VRangeSlider() {
    this(SliderDirection.LEFT_TO_RIGHT);
  }

  public VRangeSlider(SliderDirection sliderDirection) {
    this.sliderDirection = sliderDirection;

    rotate.setAngle(sliderDirection.rotation);
    rotatePane.getTransforms().add(rotate);
    positionGroup.getChildren().add(rotatePane);
    getChildren().add(positionGroup);
    getChildren().addAll(minButtonLabel, maxButtonLabel);

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

    rotatePane.getChildren().addAll(backgroundLine, rangeLine);
    backgroundLine.setStartX(BUTTON_RADIUS + lineRadius);
    backgroundLine.setStroke(Color.ALICEBLUE);

    rangeLine.setStroke(Color.RED);

    buttonMin.setRadius(BUTTON_RADIUS);
    buttonMax.setRadius(BUTTON_RADIUS);
    rotatePane.getChildren().addAll(buttonMin, buttonMax);
    FXUtils.setOnMouseEntered(buttonMin, e -> labelAnimation.play(labelVisible));
    FXUtils.setOnMouseExited(buttonMin, e -> labelAnimation.play(labelInvisible));
    FXUtils.setOnMouseEntered(buttonMax, e -> labelAnimation.play(labelVisible));
    FXUtils.setOnMouseExited(buttonMax, e -> labelAnimation.play(labelInvisible));
    buttonMin.layoutXProperty().addListener(ob -> updateLabels());
    buttonMax.layoutXProperty().addListener(ob -> updateLabels());
    updateLabels();

    var dragMin =
        new DragHandler() {
          @Override
          protected void set(double x, double y) {
            setMinPercentage((x - BUTTON_RADIUS) / (length - BUTTON_RADIUS * 2));
          }

          @Override
          protected double[] get() {
            return new double[] {buttonMin.getLayoutX(), 0};
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
          protected void consume(MouseEvent e) {
            e.consume();
          }
        };
    {
      double[] tmp = new double[] {0};
      FXUtils.setOnMousePressed(
          buttonMin,
          e -> {
            tmp[0] = buttonMin.getLayoutX();
            dragMin.handle(e);
          });
      buttonMin.setOnAction(
          e -> {
            if (tmp[0] != buttonMin.getLayoutX()) {
              return;
            }
            if (minOnAction != null) {
              minOnAction.handle(e);
            }
          });
      buttonMin.setOnMouseDragged(dragMin);
    }

    var dragMax =
        new DragHandler() {
          @Override
          protected void set(double x, double y) {
            setMaxPercentage((x - BUTTON_RADIUS) / (length - BUTTON_RADIUS * 2));
          }

          @Override
          protected double[] get() {
            return new double[] {buttonMax.getLayoutX(), 0};
          }

          @SuppressWarnings("SuspiciousNameCombination")
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
          protected void consume(MouseEvent e) {
            e.consume();
          }
        };
    {
      double[] tmp = new double[] {0};
      FXUtils.setOnMousePressed(
          buttonMax,
          e -> {
            tmp[0] = buttonMax.getLayoutX();
            dragMax.handle(e);
          });
      buttonMax.setOnAction(
          e -> {
            if (tmp[0] != buttonMax.getLayoutX()) {
              return;
            }
            if (maxOnAction != null) {
              maxOnAction.handle(e);
            }
          });
      buttonMax.setOnMouseDragged(dragMax);
    }
  }

  private final DoublePropertyBase minPercentageProperty =
      new DoublePropertyBase() {
        @Override
        protected void invalidated() {
          setMinPercentage(minPercentageProperty.get());
        }

        @Override
        public Object getBean() {
          return VRangeSlider.this;
        }

        @Override
        public String getName() {
          return "minPercentageProperty";
        }
      };

  public DoubleProperty minPercentageProperty() {
    return minPercentageProperty;
  }

  public double getMinPercentage() {
    return rangeMin;
  }

  public void setMinPercentage(double minPercentage) {
    if (minPercentage < 0) {
      minPercentage = 0;
    }
    if (minPercentage > 1) {
      minPercentage = 1;
    }
    this.rangeMin = minPercentage;
    if (minPercentage > rangeMax) {
      this.rangeMax = minPercentage;
      maxPercentageProperty.set(minPercentage);
    }
    minPercentageProperty.set(minPercentage);
    updateRange();
  }

  private final DoublePropertyBase maxPercentageProperty =
      new DoublePropertyBase() {
        @Override
        protected void invalidated() {
          setMaxPercentage(maxPercentageProperty.get());
        }

        @Override
        public Object getBean() {
          return VRangeSlider.this;
        }

        @Override
        public String getName() {
          return "maxPercentageProperty";
        }
      };

  public DoubleProperty maxPercentageProperty() {
    return maxPercentageProperty;
  }

  public double getMaxPercentage() {
    return rangeMax;
  }

  public void setMaxPercentage(double maxPercentage) {
    if (maxPercentage > 1) {
      maxPercentage = 1;
    }
    if (maxPercentage < 0) {
      maxPercentage = 0;
    }
    this.rangeMax = maxPercentage;
    if (maxPercentage < rangeMin) {
      this.rangeMin = maxPercentage;
      minPercentageProperty.set(maxPercentage);
    }
    maxPercentageProperty.set(maxPercentage);
    updateRange();
    updateLabels();
  }

  public void setLength(double length) {
    this.length = length;
    backgroundLine.setEndX(length - BUTTON_RADIUS - lineRadius);
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
    updateRange();
    updateLabels();
  }

  private void updateRange() {
    var barLen = length - BUTTON_RADIUS * 2;

    buttonMin.setLayoutX(BUTTON_RADIUS + barLen * rangeMin);
    buttonMax.setLayoutX(BUTTON_RADIUS + barLen * rangeMax);

    rangeLine.setStartX(lineRadius + buttonMin.getLayoutX());
    rangeLine.setEndX(lineRadius + buttonMax.getLayoutX());
  }

  public void setMinValueTransform(DoubleFunction<String> minValueTransform) {
    if (minValueTransform == null) {
      minValueTransform = DisplayFormat.DOUBLE_FORMAT.getFormat()::format;
    }
    this.minValueTransform = minValueTransform;
    updateLabels();
  }

  public void setMaxValueTransform(DoubleFunction<String> maxValueTransform) {
    if (maxValueTransform == null) {
      maxValueTransform = DisplayFormat.DOUBLE_FORMAT.getFormat()::format;
    }
    this.maxValueTransform = maxValueTransform;
    updateLabels();
  }

  private void updateLabels() {
    var minStr = this.minValueTransform.apply(getMinPercentage());
    var maxStr = this.maxValueTransform.apply(getMaxPercentage());
    minButtonLabel.setText(minStr);
    maxButtonLabel.setText(maxStr);
    if (sliderDirection.isHorizontal) {
      if (sliderDirection == SliderDirection.LEFT_TO_RIGHT) {
        minButtonLabel.setLayoutX(buttonMin.getLayoutX() + BUTTON_RADIUS + 5);
        maxButtonLabel.setLayoutX(buttonMax.getLayoutX() + BUTTON_RADIUS + 5);
      } else {
        minButtonLabel.setLayoutX(getLength() - buttonMin.getLayoutX() + BUTTON_RADIUS + 5);
        maxButtonLabel.setLayoutX(getLength() - buttonMax.getLayoutX() + BUTTON_RADIUS + 5);
      }
      minButtonLabel.setLayoutY(BUTTON_RADIUS + 5);
      maxButtonLabel.setLayoutY(BUTTON_RADIUS + 5);
    } else {
      minButtonLabel.setLayoutX(BUTTON_RADIUS + 5);
      maxButtonLabel.setLayoutX(BUTTON_RADIUS + 5);
      if (sliderDirection == SliderDirection.TOP_TO_BOTTOM) {
        minButtonLabel.setLayoutY(buttonMin.getLayoutX() + BUTTON_RADIUS + 5);
        maxButtonLabel.setLayoutY(buttonMax.getLayoutX() + BUTTON_RADIUS + 5);
      } else {
        minButtonLabel.setLayoutY(getLength() - buttonMin.getLayoutX() + BUTTON_RADIUS + 5);
        maxButtonLabel.setLayoutY(getLength() - buttonMax.getLayoutX() + BUTTON_RADIUS + 5);
      }
    }
  }
}
