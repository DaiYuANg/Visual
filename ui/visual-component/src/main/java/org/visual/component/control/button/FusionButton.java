package org.visual.component.control.button;

import javafx.animation.AnimationTimer;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Window;
import lombok.Getter;
import lombok.Setter;
import org.visual.component.util.FXUtils;

public class FusionButton extends AbstractFusionButton {
  private final Label text = new Label() {};
  private EventHandler<?> actionHandler = null;

  private Window watchingWindow = null;
  private final ChangeListener<? super Boolean> windowFocusPlayAnimationListener =
      (ob, old, now) -> {
        if (now == null) {
          return;
        }
        setInternalDisableAnimation(!now);
      };

  public FusionButton() {
    this(null);
  }

  public FusionButton(String text) {
    this.text.textProperty().addListener((ob, old, now) -> updateTextPosition());
    widthProperty()
        .addListener(
            (ob, old, now) -> {
              if (now == null) return;
              updateTextPosition();
              var w = now.doubleValue();
              borderLightPane.setPrefWidth(w);
            });
    heightProperty()
        .addListener(
            (ob, old, now) -> {
              if (now == null) return;
              updateTextPosition();
              var h = now.doubleValue();
              borderLightPane.setPrefHeight(h);
            });
    if (text != null) {
      this.text.setText(text);
    }
    setCursor(Cursor.HAND);
    getChildren().add(this.text);
    sceneProperty()
        .addListener(
            (ob, old, now) -> {
              var oldWindow = watchingWindow;
              watchingWindow = null;
              if (oldWindow != null) {
                oldWindow.focusedProperty().removeListener(windowFocusPlayAnimationListener);
              }
              if (now != null) {
                var newWindow = now.getWindow();
                watchingWindow = newWindow;
                newWindow.focusedProperty().addListener(windowFocusPlayAnimationListener);
                setInternalDisableAnimation(!newWindow.isFocused());
              } else {
                setInternalDisableAnimation(true);
              }
            });
    setInternalDisableAnimation(true);
    disabledProperty()
        .addListener(
            (ob, old, now) -> {
              if (now == null) return;
              handleDisable(now);
            });

    borderLightPane.setBorder(
        new Border(
            new BorderStroke(
                Color.WHEAT, BorderStrokeStyle.SOLID, getCornerRadii(), new BorderWidths(1.5))));
    borderLightPane.setBackground(Background.EMPTY);
    borderLightPane.setOpacity(0);
    getChildren().add(borderLightPane);

    setPrefWidth(64);
    setPrefHeight(24);
  }

  private void handleDisable(boolean v) {
    if (v) {
      setCursor(Cursor.DEFAULT);
      setMouseTransparent(true);
    } else {
      setCursor(Cursor.HAND);
      setMouseTransparent(false);
      startAnimating();
    }
  }

  private void updateTextPosition() {
    var bounds = FXUtils.calculateTextBounds(text);
    text.setLayoutX((getWidth() - bounds.getWidth()) / 2);
    text.setLayoutY((getHeight() - bounds.getHeight()) / 2);
  }

  public void setText(String text) {
    this.text.setText(text);
  }

  public void setOnAction(EventHandler<?> handler) {
    this.actionHandler = handler;
  }

  public EventHandler<?> getOnAction() {
    return actionHandler;
  }

  @Override
  protected void onMouseClicked() {
    alreadyClicked = true;
    var actionHandler = this.actionHandler;
    if (actionHandler == null) {
      return;
    }
    actionHandler.handle(null);
  }

  @Override
  protected CornerRadii getCornerRadii() {
    return new CornerRadii(4);
  }

  public Label getTextNode() {
    return text;
  }

  private Animation timer = null;
  private final Pane borderLightPane = new Pane();
  @Getter private boolean disableAnimation = false;
  private boolean internalDisableAnimation = false;
  private boolean alreadyClicked = false;
  @Setter @Getter private boolean onlyAnimateWhenNotClicked = false;

  // return true if it's animating after calling this method
  public boolean startAnimating() {
    var timer = this.timer;
    if (timer != null) {
      return true; // is already animating
    }
    if (isDisableAnimation0()) {
      return false;
    }
    timer = new Animation();
    timer.start();
    this.timer = timer;
    return true;
  }

  private class Animation extends AnimationTimer {
    private long beginTs = 0;

    @Override
    public void handle(long now) {
      if (beginTs == 0) {
        beginTs = now;
        return;
      }
      var delta = (now - beginTs) / 1_000_000;
      final long noAnimate = 2_000;
      final long showTime = 3_500;
      final long glowTime = 4_000;
      final long hideTime = 5_500;
      final long fullPeriod = 10_000;
      if (delta > fullPeriod) {
        while (delta > fullPeriod) {
          delta -= fullPeriod;
        }
        beginTs = now - delta * 1_000_000;
      }
      if (delta < noAnimate) {
        borderLightPane.setOpacity(0);
      }
      if (delta < showTime) {
        var p = (delta - noAnimate) / (double) (showTime - noAnimate);
        borderLightPane.setOpacity(p);
      } else if (delta < glowTime) {
        borderLightPane.setOpacity(1);
      } else if (delta < hideTime) {
        var p = (delta - glowTime) / (double) (hideTime - glowTime);
        borderLightPane.setOpacity(1 - p);
      } else {
        borderLightPane.setOpacity(0);
        if (isDisableAnimation0()) {
          timer = null;
          stop();
        }
      }
    }
  }

  public void stopAnimating() {
    var timer = this.timer;
    this.timer = null;
    if (timer != null) {
      timer.stop();
    }
    borderLightPane.setOpacity(0);
  }

  public boolean isDisableAnimation0() {
    return disableAnimation
        || internalDisableAnimation
        || isDisabled()
        || (alreadyClicked && onlyAnimateWhenNotClicked);
  }

  public void setDisableAnimation(boolean disableAnimation) {
    this.disableAnimation = disableAnimation;
    if (disableAnimation) {
      stopAnimating();
    } else {
      startAnimating();
    }
  }

  private void setInternalDisableAnimation(boolean internalDisableAnimation) {
    this.internalDisableAnimation = internalDisableAnimation;
    if (internalDisableAnimation) {
      stopAnimating();
    } else {
      startAnimating();
    }
  }
}
