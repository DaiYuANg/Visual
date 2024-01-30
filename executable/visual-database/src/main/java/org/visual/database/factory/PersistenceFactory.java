package org.visual.database.factory;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import java.util.prefs.Preferences;
import org.visual.database.VisualModelDatabase;
import org.visual.shared.PreferencesWrapper;

@Factory
public class PersistenceFactory {
  @Bean
  ObjectMapper objectMapper() {
    return new ObjectMapper();
  }

  @Bean
  PreferencesWrapper preferencesWrapper() {
    return new PreferencesWrapper(Preferences.userNodeForPackage(VisualModelDatabase.class));
  }
}
