package org.visual.debugger.component;

import static org.visual.component.util.GeometryUtil.makeSameInsets;
import static org.visual.component.util.GeometryUtil.posToXy;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.visual.debugger.Debugger;
import org.visual.debugger.context.AttachSceneContext;
import org.visual.debugger.view.VisualDebuggerView;
import org.visual.shared.util.AutoIncrement;

@Slf4j
public class VisualDebugger extends Pane {
  private final KeyCombination keyCombination = new KeyCodeCombination(KeyCode.F12);

  private final SimpleBooleanProperty _show = new SimpleBooleanProperty(false);

  public Boolean getShow() {
    return _show.get();
  }

  public void setShow(Boolean value) {
    if (value != null) {
      _show.set(value);
    }
  }

  private final SimpleObjectProperty<Pos> _pos = new SimpleObjectProperty<>(Pos.TOP_LEFT);

  public Pos getPos() {
    return _pos.get();
  }

  public void setPos(Pos value) {
    _pos.set(value);
  }

  private double xOffset = 0.0;
  private double yOffset = 0.0;

  public VisualDebugger() {
    setManaged(false);
    setVisible(true);
    setPadding(makeSameInsets(0.5));
    getChildren().add(new DebugWidget());
    initialize();
  }

  private void initialize() {
    sceneProperty()
        .addListener(
            (observable, oldValue, newScene) -> {
              newScene
                  .windowProperty()
                  .addListener(
                      (windowObservable, oldWindow, newWindow) -> {
                        Stage stage = (Stage) newWindow;
                        Debugger debugger = setupAttachScene(stage);
                        newScene
                            .getAccelerators()
                            .put(keyCombination, () -> debugger.showDebugger());
                        if (_show.get()) {
                          VisualDebuggerView.show(newScene);
                          debugger.showDebugger();
                        }
                        toFront();
                      });
            });

    _pos.addListener(
        (observable, oldValue, newValue) -> {
          val xy = posToXy(newValue);
          setTranslateX(xy.getKey() - getWidth());
          setTranslateY(xy.getValue());
          toFront();
        });

    setOnMouseEntered(event -> setCursor(Cursor.HAND));
    setOnMousePressed(
        event -> {
          xOffset = event.getX();
          yOffset = event.getY();
        });

    setOnMouseDragged(
        event -> {
          setTranslateX(event.getSceneX() - xOffset);
          setTranslateY(event.getSceneY() - yOffset);
        });
  }

  @Contract("_ -> new")
  private @NotNull Debugger setupAttachScene(Stage stage) {
    long sceneId = AutoIncrement.next();
    AttachSceneContext.getStage().set(stage);
    return new Debugger();
  }
}
