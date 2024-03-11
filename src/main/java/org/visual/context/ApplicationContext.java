package org.visual.context;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.grapher.graphviz.GraphvizGrapher;
import com.google.inject.grapher.graphviz.GraphvizModule;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.val;
import org.github.gestalt.config.guice.GestaltModule;
import org.jetbrains.annotations.NotNull;
import org.visual.constant.FXMLView;
import org.visual.module.RootModule;
import org.visual.provider.ConfigProvider;
import org.visual.view.VisualUI;

@Getter
public enum ApplicationContext {
  INSTANCE;

  private final Injector injector;

  public <T> @NotNull T get(Class<T> clazz) {
    return injector.getInstance(clazz);
  }

  @SneakyThrows
  ApplicationContext() {
    injector =
        Guice.createInjector(
            new RootModule(), new GraphvizModule(), new GestaltModule(new ConfigProvider().get()));
    val grapher = get(GraphvizGrapher.class);
    val out = new PrintWriter("./visual.dot", StandardCharsets.UTF_8);
    grapher.setOut(out);
    grapher.setRankdir("TB");
    grapher.graph(this.injector);
  }

  @SneakyThrows
  public Parent load(@NotNull FXMLView fxmlKey) {
    FXMLLoader loader = new FXMLLoader(VisualUI.class.getResource(STR."\{fxmlKey.getView()}.fxml"));
    loader.setControllerFactory(this::get);
    loader.setCharset(StandardCharsets.UTF_8);
    return loader.load();
  }
}
