package org.visual.model.language.gui.ide.contexts;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.util.concurrent.*;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

@Slf4j
public enum AsyncContext {
	INSTANCE;

	private final int cpuCount = Runtime.getRuntime().availableProcessors();

	private final ThreadPoolExecutor executor = new ThreadPoolExecutor(cpuCount, cpuCount * 2, 1, TimeUnit.MINUTES,
			new LinkedBlockingDeque<>(cpuCount * 5),
			new ThreadFactoryBuilder().setNameFormat("VisualModel-%d").build());

	private final ScheduledThreadPoolExecutor scheduledExecutor = new ScheduledThreadPoolExecutor(cpuCount / 2,
			new ThreadFactoryBuilder().setNameFormat("VisualModel-Scheduler-%d").build(),
			new ThreadPoolExecutor.CallerRunsPolicy());

	AsyncContext() {
		executor.prestartAllCoreThreads();
		scheduledExecutor.prestartAllCoreThreads();
	}

	@Contract("_ -> new")
	public @NotNull CompletableFuture<Void> run(Runnable runnable) {
		return CompletableFuture.runAsync(runnable, executor);
	}

	public @NotNull ScheduledFuture<?> schedule(Runnable runnable, long delay, TimeUnit timeUnit) {
		return scheduledExecutor.schedule(runnable, delay, timeUnit);
	}

	public void shutdown() {
		executor.shutdown();
		scheduledExecutor.shutdown();
	}
}
