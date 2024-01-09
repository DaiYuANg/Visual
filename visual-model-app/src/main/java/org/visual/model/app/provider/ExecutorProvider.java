package org.visual.model.app.provider;

import jakarta.inject.Provider;
import lombok.val;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ExecutorProvider implements Provider<Executor> {

    @Override
    public Executor get() {
        val factory = Thread.ofVirtual().name("visual-model", 0).factory();
        return Executors.newThreadPerTaskExecutor(factory);
    }
}
