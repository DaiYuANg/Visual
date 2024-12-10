package org.visual.app.util;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import javafx.scene.control.Dialog;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

@Singleton
@Slf4j
public class DialogPool extends GenericObjectPool<Dialog<?>> {

  @Inject
  public DialogPool(DialogFactory factory) {
    super(factory);
    val config = new GenericObjectPoolConfig<Dialog<?>>();
    config.setMaxTotal(10);  // 最大池大小
    config.setMinIdle(2);    // 最小空闲大小
    config.setMaxIdle(5);    // 最大空闲大小
    setConfig(config);
  }
}
