package org.visual.app.lifecycle;

import io.avaje.spi.ServiceProvider;
import lombok.extern.slf4j.Slf4j;

@ServiceProvider
@Slf4j
public class BackgroundServiceLifecycle implements Lifecycle {
  @Override
  public void onStart() {
    log.atInfo().log("Background Service Start");
  }
}
