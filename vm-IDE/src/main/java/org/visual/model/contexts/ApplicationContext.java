package org.visual.model.contexts;

import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.visual.model.EventSubscriber;

public enum ApplicationContext {
    APPLICATION;

    private final ConcurrentMap<Object, Object> global = new ConcurrentHashMap<>();

    ApplicationContext() {}


    public Object get(Object key) {
        return global.get(key);
    }

    public void put(Object key, Object value) {
        global.put(key, value);
    }
}
