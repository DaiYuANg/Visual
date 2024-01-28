package org.visual.debugger.constant;

import java.lang.management.*;
import javax.management.MBeanServerDelegate;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum MBean {
    JMImplementation("JMImplementation:type=MBeanServerDelegate", MBeanServerDelegate.class),
    Runtime("java.lang:type=Runtime", Runtime.class),
    Threading("java.lang:type=Threading", ThreadMXBean.class),
    OperatingSystem("java.lang:type=OperatingSystem", OperatingSystemMXBean.class),
    G1ConcurrentGC("java.lang:name=G1 Concurrent GC,type=GarbageCollector", GarbageCollectorMXBean.class),
    DirectBufferPool("java.nio:name=direct,type=BufferPool", BufferPoolMXBean.class),
    Compilation("java.lang:type=Compilation", CompilationMXBean.class),
    G1YoungGeneration("java.lang:name=G1 Young Generation,type=GarbageCollector", GarbageCollectorMXBean.class),
    CodeCacheManager("java.lang:name=CodeCacheManager,type=MemoryManager", MemoryManagerMXBean.class),
    G1OldGen("java.lang:name=G1 Old Gen,type=MemoryPool", MemoryPoolMXBean.class),
    Logging("java.util.logging:type=Logging", PlatformLoggingMXBean.class),
    G1OldGeneration("java.lang:name=G1 Old Generation,type=GarbageCollector", GarbageCollectorMXBean.class),
    ClassLoading("java.lang:type=ClassLoading", ClassLoadingMXBean.class),
    MetaspaceManager("java.lang:name=Metaspace Manager,type=MemoryManager", MemoryManagerMXBean.class),
    G1SurvivorSpace("java.lang:name=G1 Survivor Space,type=MemoryPool", MemoryPoolMXBean.class),
    Metaspace("java.lang:name=Metaspace,type=MemoryPool", MemoryPoolMXBean.class),
    CodeHeapProfiledNMethods("java.lang:name=CodeHeap 'profiled nmethods',type=MemoryPool", MemoryPoolMXBean.class),
    MappedNonVolatileMemoryBufferPool("java.nio:name=mapped - 'non-volatile memory',type=BufferPool", BufferPoolMXBean.class),
    CodeHeapNonNMethods("java.lang:name=CodeHeap 'non-nmethods',type=MemoryPool", MemoryPoolMXBean.class),
    CompressedClassSpace("java.lang:name=Compressed Class Space,type=MemoryPool", MemoryPoolMXBean.class),
    Memory("java.lang:type=Memory", MemoryMXBean.class),
    G1EdenSpace("java.lang:name=G1 Eden Space,type=MemoryPool", MemoryPoolMXBean.class),
    MappedBufferPool("java.nio:name=mapped,type=BufferPool", BufferPoolMXBean.class),
    CodeHeapNonProfiledNMethods("java.lang:name=CodeHeap 'non-profiled nmethods',type=MemoryPool", MemoryPoolMXBean.class);

    private final String mbeanName;
    private final Class<?> associatedClass;
}
