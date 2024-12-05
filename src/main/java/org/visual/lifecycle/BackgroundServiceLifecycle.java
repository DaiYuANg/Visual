package org.visual.lifecycle;

import io.avaje.spi.ServiceProvider;
import lombok.extern.slf4j.Slf4j;
import org.visual.api.Lifecycle;

@ServiceProvider
@Slf4j
public class BackgroundServiceLifecycle implements Lifecycle {
  @Override
  public void onStart() {
    log.atInfo().log("Background Service Start");
  }
}
