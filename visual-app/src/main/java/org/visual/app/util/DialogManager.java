package org.visual.app.util;

import jakarta.inject.Singleton;
import javafx.scene.control.Dialog;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Singleton
@RequiredArgsConstructor
@Slf4j
public class DialogManager {

  private final DialogPool dialogPool;

  @SneakyThrows
  public Dialog<?> getDialog() {
    // 获取一个 Dialog 实例
    return dialogPool.borrowObject();
  }

  public void closeDialog(Dialog<?> dialog) {
    dialogPool.returnObject(dialog);
  }
}
