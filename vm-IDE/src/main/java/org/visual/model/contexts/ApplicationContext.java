package org.visual.model.contexts;

import lombok.SneakyThrows;
import lombok.val;

import java.nio.file.Path;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public enum ApplicationContext {
    INSTANCE;

    private final ConcurrentMap<Object, Object> global = new ConcurrentHashMap<>();

    private final Path cwd = Path.of("").toAbsolutePath();

    @SneakyThrows
    ApplicationContext() {

    }


    public Object get(Object key) {
        return global.get(key);
    }

    public void put(Object key, Object value) {
        global.put(key, value);
    }
}
