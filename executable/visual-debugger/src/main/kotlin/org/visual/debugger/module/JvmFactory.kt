package org.visual.debugger.module

import com.google.inject.AbstractModule
import com.google.inject.Provides
import io.github.classgraph.ClassGraph
import java.lang.management.*

class JvmFactory :AbstractModule(){

    @Provides
    fun memoryMXBean(): MemoryMXBean {
        return ManagementFactory.getMemoryMXBean()
    }

    @Provides
    fun classLoadingMXBean(): ClassLoadingMXBean {
        return ManagementFactory.getClassLoadingMXBean()
    }

    @Provides
    fun compilationMXBean(): CompilationMXBean {
        return ManagementFactory.getCompilationMXBean()
    }

    @Provides
    fun runtimeMXBean(): RuntimeMXBean {
        return ManagementFactory.getRuntimeMXBean()
    }

    @Provides
    fun operatingSystemMXBean(): OperatingSystemMXBean {
        return ManagementFactory.getOperatingSystemMXBean()
    }

    @Provides
    fun threadMXBean(): ThreadMXBean {
        return ManagementFactory.getThreadMXBean()
    }

    @Provides
    fun platformLoggingMXBean(): PlatformLoggingMXBean {
        return ManagementFactory.getPlatformMXBean(PlatformLoggingMXBean::class.java)
    }

    @Provides
    fun classGraph(): ClassGraph {
        return ClassGraph().enableAllInfo()
    }
}
