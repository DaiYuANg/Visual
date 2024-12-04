package org.visual.pool;

import com.google.common.base.Preconditions;
import jakarta.inject.Singleton;
import javafx.application.Platform;
import javafx.scene.control.Dialog;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

import static com.google.common.base.Preconditions.checkArgument;

@Singleton
@Slf4j
public class DialogPool extends BasePooledObjectFactory<Dialog<?>> {
  @Override
  public Dialog<?> create() throws Exception {
    checkArgument(Platform.isFxApplicationThread());
    return new Dialog<>();
  }

  @Override
  public PooledObject<Dialog<?>> wrap(Dialog<?> dialog) {
    return new DefaultPooledObject<>(dialog);
  }
}
