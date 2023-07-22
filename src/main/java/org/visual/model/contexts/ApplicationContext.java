package org.visual.model.contexts;

import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.visual.model.EventSubscriber;

public enum ApplicationContext {
    CONTEXT;

    private final ConcurrentMap<Object, Object> global = new ConcurrentHashMap<>();

    @Setter
    private ResourceBundle i18nResource;

    @Setter
    private EventSubscriber eventSubscriber;

    //    @Setter
    //    @Getter
    //    private Vertx vertx;
    //
    //    @Setter
    //    @Getter
    //    private EventBus eventBus;

    ApplicationContext() {}

    public @NotNull String getI18nResource(String key) {
        return i18nResource.getString(key);
    }

    public Object get(Object key) {
        return global.get(key);
    }

    public void put(Object key, Object value) {
        global.put(key, value);
    }
}
