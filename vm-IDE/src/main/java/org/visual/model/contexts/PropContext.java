package org.visual.model.contexts;

import lombok.SneakyThrows;
import lombok.val;

import java.util.Properties;

public enum PropContext {
    PROP_CONTEXT;

    @SneakyThrows
    PropContext(){
        val p = new Properties();
        p.load(this.getClass().getClassLoader().getResourceAsStream("application.properties"));
        p.forEach((k, v) -> System.setProperty(k.toString(), v.toString()));
    }
}
