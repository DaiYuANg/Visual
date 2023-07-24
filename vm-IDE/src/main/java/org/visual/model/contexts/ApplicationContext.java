package org.visual.model.contexts;

import lombok.SneakyThrows;
import lombok.val;

import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public enum ApplicationContext {
    APPLICATION;

    private final ConcurrentMap<Object, Object> global = new ConcurrentHashMap<>();

    @SneakyThrows
    ApplicationContext() {
        val p = new Properties();
        p.load(this.getClass().getClassLoader().getResourceAsStream("application.properties"));
        p.forEach((k, v) -> System.setProperty(k.toString(), v.toString()));
    }


    public Object get(Object key) {
        return global.get(key);
    }

    public void put(Object key, Object value) {
        global.put(key, value);
    }
}
