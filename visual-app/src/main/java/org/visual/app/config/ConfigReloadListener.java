package org.visual.app.config;

import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.github.gestalt.config.reload.CoreReloadListener;

@Singleton
@Slf4j
@RequiredArgsConstructor
public class ConfigReloadListener implements CoreReloadListener {
  @Override
  public void reload() {
    log.atInfo().log("Reloading config...");
  }
}
