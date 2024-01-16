package org.visual.model.debugger.factory;

import io.avaje.inject.Bean;
import io.avaje.inject.Factory;

import java.lang.management.*;

@Factory
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
}
