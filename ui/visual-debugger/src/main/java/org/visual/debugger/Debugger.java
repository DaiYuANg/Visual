package org.visual.debugger;

import javafx.scene.Scene;
import javafx.stage.WindowEvent;
import org.visual.component.display.VisualStage;
import org.visual.component.util.FxUtil;
import org.visual.debugger.constant.FXMLKey;
import org.visual.debugger.context.AttachSceneContext;
import org.visual.debugger.context.DebuggerContext;

public class Debugger {
  private final Scene rootScene = new Scene(DebuggerContext.load(FXMLKey.LAYOUT));
  private final VisualStage stage = new VisualStage();

  public Debugger() {
    AttachSceneContext.getStage()
        .addListener(
            (a, b, newValue) ->
                newValue.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, c -> stage.close()));
  }

  public void showDebugger() {
    FxUtil.runOnFx(
        () -> {
          stage.setScene(rootScene);
          stage.showAndFocus();
        });
  }
}
