package org.visual.debugger.context;

import io.avaje.inject.BeanScope;
import java.nio.charset.StandardCharsets;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.util.Callback;
import lombok.SneakyThrows;
import org.github.gestalt.config.exceptions.GestaltException;
import org.github.gestalt.config.source.ClassPathConfigSourceBuilder;
import org.github.gestalt.config.source.ConfigSourcePackage;
import org.github.gestalt.config.source.EnvironmentConfigSourceBuilder;
import org.github.gestalt.config.source.SystemPropertiesConfigSourceBuilder;
import org.visual.debugger.Debugger;
import org.visual.debugger.constant.FXMLKey;

public enum DebuggerContext {
  INSTANCE;

  //  private static final PreferencesModule preferencesModule = new PreferencesModule();
  //  private static final List<ConfigSourcePackage> configLoaders = List.of(
  //    new EnvironmentVarsLoader(),
  //    new PropertyLoader(),
  //    new MapConfigLoader()
  //  );

  private static final ConfigSourcePackage environmentSource;

  static {
    try {
      environmentSource =
          EnvironmentConfigSourceBuilder.builder()
              .setPrefix("VISUAL_MODEL")
              .setFailOnErrors(false)
              .build();
    } catch (GestaltException e) {
      throw new RuntimeException(e);
    }
  }

  private static final ConfigSourcePackage classPathSource;

  static {
    try {
      classPathSource =
          ClassPathConfigSourceBuilder.builder()
              .setResource("visual.model.debugger.properties")
              .build();
    } catch (GestaltException e) {
      throw new RuntimeException(e);
    }
  }

  private static final ConfigSourcePackage javafxClassPathSource;

  static {
    try {
      javafxClassPathSource =
          ClassPathConfigSourceBuilder.builder().setResource("javafx.properties").build();
    } catch (GestaltException e) {
      throw new RuntimeException(e);
    }
  }

  private static final ConfigSourcePackage systemSource;

  static {
    try {
      systemSource = SystemPropertiesConfigSourceBuilder.builder().setFailOnErrors(false).build();
    } catch (GestaltException e) {
      throw new RuntimeException(e);
    }
  }

  //  private static final GestaltModule gestaltModule = createGestaltModule();

  //  private static final JvmFactory jvmFactory = new JvmFactory();
  //  private static final UIFactory uiFactory = new UIFactory();
  //
  private static final BeanScope injector = BeanScope.builder().build();

  //  private static GestaltModule createGestaltModule() {
  //    GestaltBuilder builder = new
  // GestaltBuilder().useCacheDecorator(true).addConfigLoaders(configLoaders);
  //    builder.addSources(List.of(classPathSource, environmentSource, systemSource,
  // javafxClassPathSource));
  //    return new GestaltModule(builder.build());
  //  }

  //  private static Injector createInjector() {
  //    return Guice.createInjector(Stage.PRODUCTION, preferencesModule, gestaltModule, jvmFactory,
  // uiFactory);
  //  }

  public static <T> T get(Class<T> clazz) {
    return injector.get(clazz);
  }

  @SneakyThrows
  public static Parent load(FXMLKey fxmlKey) {
    FXMLLoader loader = new FXMLLoader(Debugger.class.getResource(fxmlKey.getKey() + ".fxml"));
    loader.setControllerFactory((Callback<Class<?>, Object>) DebuggerContext::get);
    loader.setCharset(StandardCharsets.UTF_8);
    return loader.load();
  }
}
