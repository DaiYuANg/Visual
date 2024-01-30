package org.visual.component.container;

import static org.visual.component.util.FXUtils.getScreenOf;

import java.util.regex.Pattern;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.Getter;
import lombok.val;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.visual.component.container.scene.VScene;
import org.visual.component.container.scene.VSceneGroup;
import org.visual.component.container.scene.VSceneGroupInitParams;
import org.visual.component.container.scene.VSceneRole;
import org.visual.component.control.CloseButton;
import org.visual.component.control.IconifyButton;
import org.visual.component.control.MaxResetButton;
import org.visual.component.handler.DragHandler;
import org.visual.component.util.FXUtils;
import org.visual.shared.OS;

public class VStage {
  public static final int TITLE_BAR_HEIGHT = 28;

  @Getter private final Stage stage;
  private final Pane rootContainer = new Pane();
  private final VScene rootContent =
      new VScene(VSceneRole.MAIN) {
        {
          enableAutoContentWidthHeight();
        }
      };

  @Getter
  private final VSceneGroup rootSceneGroup =
      new VSceneGroup(rootContent, new VSceneGroupInitParams().setUseClip(false));

  private final VScene content;
  @Getter private final VSceneGroup sceneGroup;
  private final Label title =
      new Label() {
        {
          setMouseTransparent(true);
        }
      };
  private final Circle resizeButton =
      new Circle() {
        {
          setStrokeWidth(0);
          setFill(Color.TRANSPARENT);
          setRadius(8);
          setCursor(Cursor.NW_RESIZE);
        }
      };

  @Getter private boolean maximized = false;
  private final MaxResetButton maxResetButton;

  public VStage() {
    this(new VStageInitParams());
  }

  public VStage(VStageInitParams initParams) {
    this(new Stage(), initParams);
  }

  public VStage(Stage stage) {
    this(stage, new VStageInitParams());
  }

  public VStage(@NotNull Stage stage, VStageInitParams initParams) {
    this.stage = stage;
    stage.initStyle(StageStyle.TRANSPARENT);

    //noinspection ReplaceNullCheck
    if (initParams.initialScene == null) {
      content = new VScene(VSceneRole.MAIN);
    } else {
      content = initParams.initialScene;
    }
    sceneGroup =
        new VSceneGroup(
            content, new VSceneGroupInitParams().setGradientCover(true).setUseClip(false));

    var scene = new Scene(rootContainer);
    scene.setFill(Color.TRANSPARENT);
    stage.setScene(scene);
    rootSceneGroup.getNode().setLayoutX(0.5);
    rootSceneGroup.getNode().setLayoutY(0.5);

    FXUtils.makeClipFor(rootSceneGroup.getNode(), 8);

    stage
        .widthProperty()
        .addListener(
            (ob, old, now) -> {
              if (now == null) return;
              var w = now.doubleValue();
              rootContainer.setPrefWidth(w);
              rootSceneGroup.getNode().setPrefWidth(w - 1);
            });
    stage
        .heightProperty()
        .addListener(
            (ob, old, now) -> {
              if (now == null) return;
              var h = now.doubleValue();
              rootContainer.setPrefHeight(h);
              rootSceneGroup.getNode().setPrefHeight(h - 1);
            });
    rootContainer.setBackground(Background.EMPTY);
    useDefaultBorder();

    var movingPane = new Pane();
    movingPane.setPrefHeight(TITLE_BAR_HEIGHT);
    var moveWindowHandler =
        new org.visual.component.handler.DragHandler() {
          @Override
          protected void set(double x, double y) {
            if (maximized) {
              return;
            }
            stage.setX(x);
            stage.setY(y);
          }

          @Override
          protected double[] get() {
            return new double[] {stage.getX(), stage.getY()};
          }
        };
    movingPane.setOnMousePressed(moveWindowHandler);
    movingPane.setOnMouseDragged(moveWindowHandler);
    rootContent.getContentPane().getChildren().add(movingPane);

    var closeBtn = new CloseButton(this, initParams);
    if (initParams.closeButton) rootContent.getContentPane().getChildren().add(closeBtn);

    maxResetButton = new MaxResetButton(this, initParams);
    if (initParams.maximizeAndResetButton)
      rootContent.getContentPane().getChildren().add(maxResetButton);

    var iconifyBtn = new IconifyButton(this, initParams);
    if (initParams.iconifyButton) rootContent.getContentPane().getChildren().add(iconifyBtn);

    sceneGroup.getNode().setLayoutX(0.5);
    sceneGroup.getNode().setLayoutY(TITLE_BAR_HEIGHT);
    rootContent.getContentPane().getChildren().add(sceneGroup.getNode());

    rootContent.getContentPane().getChildren().add(title);

    var resizeHandler =
        new DragHandler() {
          @Override
          protected void set(double x, double y) {
            if (x < 256) {
              x = 256;
            }
            if (y < 128) {
              y = 128;
            }
            stage.setWidth(x);
            stage.setHeight(y);
          }

          @Contract(" -> new")
          @Override
          protected double @NotNull [] get() {
            return new double[] {
              stage.getWidth(), stage.getHeight(),
            };
          }
        };
    resizeButton.setOnMousePressed(resizeHandler);
    resizeButton.setOnMouseDragged(resizeHandler);

    title.textProperty().addListener((ob, old, now) -> updateTitlePosition());
    stage
        .widthProperty()
        .addListener(
            (ob, old, now) -> {
              if (now == null) return;
              var w = now.doubleValue();
              final double padRight = 0;
              var controlButtonsOffset = w - padRight;
              if (initParams.closeButton) {
                controlButtonsOffset -= CloseButton.WIDTH;
                closeBtn.setLayoutX(controlButtonsOffset);
              }
              if (initParams.maximizeAndResetButton) {
                controlButtonsOffset -= MaxResetButton.WIDTH;
                maxResetButton.setLayoutX(controlButtonsOffset);
              }
              if (initParams.iconifyButton) {
                controlButtonsOffset -= IconifyButton.WIDTH;
                iconifyBtn.setLayoutX(controlButtonsOffset);
              }
              movingPane.setPrefWidth(w);
              rootSceneGroup.getNode().setPrefWidth(w - 1);
              resizeButton.setLayoutX(w - 8);
              updateTitlePosition();
            });
    stage
        .heightProperty()
        .addListener(
            (ob, old, now) -> {
              if (now == null) return;
              var h = now.doubleValue();
              rootSceneGroup.getNode().setPrefHeight(h - 1);
              resizeButton.setLayoutY(h - 8);
            });
    FXUtils.observeWidthHeight(
        rootContent.getContentPane(), sceneGroup.getNode(), 0, -TITLE_BAR_HEIGHT);

    rootContainer.getChildren().add(rootSceneGroup.getNode());
    if (initParams.resizable) {
      rootContainer.getChildren().add(resizeButton);
    }

    stage.setOnCloseRequest(e -> close());
  }

  public void useDefaultBorder() {
    useLightBorder();
  }

  public void useLightBorder() {
    rootContainer.setBorder(
        new Border(
            new BorderStroke(
                Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(8), new BorderWidths(0.5))));
  }

  public void useDarkBorder() {
    rootContainer.setBorder(
        new Border(
            new BorderStroke(
                Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(8), new BorderWidths(0.5))));
  }

  private void updateTitlePosition() {
    if (title.getText() == null || title.getText().isBlank()) {
      title.setVisible(false);
      return;
    }
    title.setVisible(true);
    var bounds = FXUtils.calculateTextBounds(title);
    var tWidth = bounds.getWidth();
    var tHeight = bounds.getHeight();
    var sWidth = stage.getWidth();
    title.setLayoutX((sWidth - tWidth) / 2);
    title.setLayoutY((TITLE_BAR_HEIGHT - tHeight) / 2);
  }

  public void setBackground(Paint paint) {
    rootSceneGroup
        .getNode()
        .setBackground(new Background(new BackgroundFill(paint, CornerRadii.EMPTY, Insets.EMPTY)));
  }

  public VScene getRoot() {
    return rootContent;
  }

  public VScene getInitialScene() {
    return content;
  }

  public void setTitle(String title) {
    this.title.setText(title);
    stage.setTitle(title);
  }

  private boolean supportsIconify() {
    if (OS.OS == OS.MAC) {
      return true;
    }
    var pattern = Pattern.compile("^(\\d+)(.*)$");
    var version = System.getProperty("javafx.runtime.version", "");
    var matcher = pattern.matcher(version);
    if (!matcher.find() || matcher.groupCount() < 1) {
      return false;
    }
    int v;
    try {
      v = Integer.parseInt(matcher.group(1));
    } catch (NumberFormatException e) {
      return false;
    }
    return v >= 17;
  }

  public void setIconified(boolean iconified) {
    if (supportsIconify()) {
      stage.setIconified(iconified);
    } else {
      if (iconified) {
        stage.toBack();
      }
    }
  }

  private double stageOriginalX;
  private double stageOriginalY;
  private double stageOriginalW;
  private double stageOriginalH;

  public void setMaximized(boolean maximized) {
    if (this.maximized == maximized) {
      return;
    }
    this.maximized = maximized;
    maxResetButton.updateImage();

    if (OS.OS == OS.MAC) {
      if (maximized) {
        stageOriginalX = stage.getX();
        stageOriginalY = stage.getY();
        stageOriginalW = stage.getWidth();
        stageOriginalH = stage.getHeight();
        val screen = getScreenOf(stage);
        if (screen == null) {
          return;
        }
        val bounds = screen.getBounds();
        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY() + 24);
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight() - 24);
      } else {
        stage.setX(stageOriginalX);
        stage.setY(stageOriginalY);
        stage.setWidth(stageOriginalW);
        stage.setHeight(stageOriginalH);
      }
    } else {
      stage.setMaximized(maximized);
    }
  }

  public void close() {
    stage.close();
  }

  public void show() {
    stage.show();
  }

  public void showAndWait() {
    stage.showAndWait();
  }

  public void temporaryOnTop() {
    if (stage.isAlwaysOnTop()) {
      return;
    }
    stage.setAlwaysOnTop(true);
    FXUtils.runDelay(500, () -> stage.setAlwaysOnTop(false));
  }
}
