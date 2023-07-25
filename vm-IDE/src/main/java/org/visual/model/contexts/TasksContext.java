package org.visual.model.contexts;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.*;

@Slf4j
public enum TasksContext {

    ASYNC;

    private final int cpuCount = Runtime.getRuntime().availableProcessors();

    private final ThreadPoolExecutor executor = new ThreadPoolExecutor(
            cpuCount,
            cpuCount * 2,
            1,
            TimeUnit.MINUTES,
            new LinkedBlockingDeque<>(cpuCount * 5),
            new ThreadFactoryBuilder()
                    .setNameFormat("VisualModel-%d")
                    .build()
    );

    private final ScheduledThreadPoolExecutor scheduledExecutor =
            new ScheduledThreadPoolExecutor(cpuCount / 2,
                    new ThreadFactoryBuilder()
                            .setNameFormat("VisualModel-Scheduler-%d")
                            .build(),
                    new ThreadPoolExecutor.CallerRunsPolicy());

    @Contract("_ -> new")
    public @NotNull CompletableFuture<Void> run(Runnable runnable) {
        log.info("test");
        return CompletableFuture.runAsync(runnable, executor);
    }

    public void schedule(Runnable runnable,long delay,TimeUnit timeUnit){
//        scheduledExecutor.schedule();
    }
}
