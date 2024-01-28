package org.visual.debugger.factory;

import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import io.github.classgraph.ClassGraph;
import java.lang.management.*;
import lombok.extern.slf4j.Slf4j;

@Factory
@Slf4j
public class JvmFactory {

  @Bean
  MemoryMXBean memoryMXBean() {
    return ManagementFactory.getMemoryMXBean();
  }

  @Bean
  ClassLoadingMXBean classLoadingMXBean() {
    return ManagementFactory.getClassLoadingMXBean();
  }

  @Bean
  CompilationMXBean compilationMXBean() {
    return ManagementFactory.getCompilationMXBean();
  }

  @Bean
  RuntimeMXBean runtimeMXBean() {
    return ManagementFactory.getRuntimeMXBean();
  }

  @Bean
  OperatingSystemMXBean operatingSystemMXBean() {
    return ManagementFactory.getOperatingSystemMXBean();
  }

  @Bean
  ThreadMXBean threadMXBean() {
    return ManagementFactory.getThreadMXBean();
  }

  @Bean
  PlatformLoggingMXBean platformLoggingMXBean() {
    return ManagementFactory.getPlatformMXBean(PlatformLoggingMXBean.class);
  }

  @Bean
  ClassGraph classGraph() {
    return new ClassGraph().enableAllInfo();
  }
}
