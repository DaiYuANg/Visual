package org.visual.model;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.reflections.util.ConfigurationBuilder;

@Slf4j
@ToString
public class EventSubscriber {
    private final Reflections scanner;

    EventSubscriber() {
        scanner = new Reflections(
                new ConfigurationBuilder().forPackages(this.getClass().getPackageName()));
        registerSubscriber();
    }

    private void registerSubscriber() {}
}
