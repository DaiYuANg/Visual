package org.visual.app.util;

import jakarta.inject.Singleton;
import javafx.application.Platform;
import javafx.scene.control.Dialog;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.jetbrains.annotations.NotNull;

import static com.google.common.base.Preconditions.checkArgument;

@Singleton
@Slf4j
public class DialogFactory extends BasePooledObjectFactory<Dialog<?>> {
  @Override
  public Dialog<?> create() throws Exception {
    checkArgument(Platform.isFxApplicationThread());
    return new Dialog<>();
  }

  @Override
  public PooledObject<Dialog<?>> wrap(Dialog<?> dialog) {
    return new DialogPooledObject(dialog);
  }

  @Override
  public void passivateObject(@NotNull PooledObject<Dialog<?>> p) {
    val dialog = p.getObject();
    // 清除 Dialog 的属性
    dialog.setTitle(null);
    dialog.setHeaderText(null);
    dialog.setResultConverter(null);
    dialog.setDialogPane(null);
  }
}
