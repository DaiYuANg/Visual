package org.visual.model.contexts;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
public enum AsyncContext {

    ASYNC;

    private final int cpuCount = Runtime.getRuntime().availableProcessors();

    private final ThreadPoolExecutor executor = new ThreadPoolExecutor(
            cpuCount,
            cpuCount * 2,
            1,
            TimeUnit.MINUTES,
            new LinkedBlockingDeque<>(cpuCount * 5),
            new ThreadFactoryBuilder()
                    .setNameFormat("visualModel-%d")
                    .build()
    );

    AsyncContext() {
        executor.prestartAllCoreThreads();
    }

    @Contract("_ -> new")
    public @NotNull CompletableFuture<Void> run(Runnable runnable) {
        log.info("test");
        return CompletableFuture.runAsync(runnable, executor);
    }
}
