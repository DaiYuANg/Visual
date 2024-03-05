package org.visual.context

import io.avaje.inject.BeanScope
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap
import java.nio.charset.StandardCharsets
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.util.Callback
import lombok.SneakyThrows
import org.visual.view.VisualUI

data object ApplicationContext {
  private val scope by lazy { BeanScope.builder().shutdownHook(true).build() }

  private val shareData by lazy { Object2ObjectOpenHashMap<Any, Any>() }

  fun <T> get(clazz: Class<T>): T {
    return scope.get(clazz)
  }

  @SneakyThrows
  fun load(prefix: String): Parent {
    val loader = FXMLLoader(VisualUI::class.java.getResource("$prefix.fxml"))
    loader.controllerFactory = Callback { aClass: Class<*> -> scope.get(aClass) }
    loader.charset = StandardCharsets.UTF_8
    return loader.load()
  }
}
