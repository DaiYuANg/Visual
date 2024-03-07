package org.visual.component.util;

import javafx.application.Platform;

public class FxUtil {
  public static void runOnFx(Runnable r) {
    if (Platform.isFxApplicationThread()) {
      r.run();
      return;
    }
    Platform.runLater(r);
  }
}
