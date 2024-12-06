package org.visual.core;

import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import io.avaje.inject.Lazy;
import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OperatingSystem;

@Factory
@Lazy
public class RuntimeFactory {
  @Bean
  SystemInfo systemInfo() {
    return new SystemInfo();
  }

  @Bean
  OperatingSystem operatingSystem(SystemInfo systemInfo) {
    return systemInfo.getOperatingSystem();
  }

  @Bean
  HardwareAbstractionLayer hardwareAbstractionLayer(SystemInfo systemInfo) {
    return systemInfo.getHardware();
  }
}
