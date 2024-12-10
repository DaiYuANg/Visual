package org.visual.app.util;

import jakarta.inject.Singleton;
import javafx.scene.control.Dialog;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.util.concurrent.atomic.AtomicInteger;

@Singleton
@RequiredArgsConstructor
@Slf4j
public class DialogManager {

  private final DialogPool dialogPool;
  private final AtomicInteger idGenerator = new AtomicInteger(0);

  @SneakyThrows
  public Dialog<?> getDialog() {
    // 获取一个 Dialog 实例
    val dialog = dialogPool.borrowObject();
    dialog.setTitle("Dialog #" + idGenerator.getAndIncrement()); // 为 Dialog 添加编号
    return dialog;
  }

  public void closeDialog() {

  }

}
