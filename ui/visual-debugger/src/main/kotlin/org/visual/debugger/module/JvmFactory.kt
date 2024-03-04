package org.visual.debugger.module

import com.google.inject.AbstractModule
import com.google.inject.Provides
import com.google.inject.Singleton
import io.github.classgraph.ClassGraph
import java.lang.management.*

class JvmFactory : AbstractModule() {

  @Provides
  @Singleton
  fun memoryMXBean(): MemoryMXBean {
    return ManagementFactory.getMemoryMXBean()
  }

  @Provides
  @Singleton
  fun classLoadingMXBean(): ClassLoadingMXBean {
    return ManagementFactory.getClassLoadingMXBean()
  }

  @Provides
  @Singleton
  fun compilationMXBean(): CompilationMXBean {
    return ManagementFactory.getCompilationMXBean()
  }

  @Provides
  @Singleton
  fun runtimeMXBean(): RuntimeMXBean {
    return ManagementFactory.getRuntimeMXBean()
  }

  @Provides
  @Singleton
  fun operatingSystemMXBean(): OperatingSystemMXBean {
    return ManagementFactory.getOperatingSystemMXBean()
  }

  @Provides
  @Singleton
  fun threadMXBean(): ThreadMXBean {
    return ManagementFactory.getThreadMXBean()
  }

  @Provides
  @Singleton
  fun platformLoggingMXBean(): PlatformLoggingMXBean {
    return ManagementFactory.getPlatformMXBean(PlatformLoggingMXBean::class.java)
  }

  @Provides
  @Singleton
  fun classGraph(): ClassGraph {
    return ClassGraph().enableAllInfo()
  }
}
