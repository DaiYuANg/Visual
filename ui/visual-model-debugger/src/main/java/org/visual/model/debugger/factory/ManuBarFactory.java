package org.visual.model.debugger.factory;

import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import org.visual.model.debugger.component.GlobalMenuBar;

@Factory
public class ManuBarFactory {

    @Bean
    GlobalMenuBar globalMenuBar() {
        return new GlobalMenuBar();
    }
}
