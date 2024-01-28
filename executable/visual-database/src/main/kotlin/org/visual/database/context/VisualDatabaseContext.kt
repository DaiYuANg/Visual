package org.visual.database.context

import io.avaje.inject.BeanScope
import java.nio.charset.StandardCharsets
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.util.Callback
import lombok.SneakyThrows
import org.visual.database.VisualModelDatabase

object VisualDatabaseContext {

  private val beanScope = BeanScope.builder().shutdownHook(true).build()

  fun <T> get(clazz: Class<T>): T {
    return beanScope.get(clazz)
  }

  @SneakyThrows
  fun load(prefix: String): Parent {
    val loader = FXMLLoader(VisualModelDatabase::class.java.getResource("$prefix.fxml"))
    loader.controllerFactory = Callback { aClass: Class<*> -> beanScope.get(aClass) }
    loader.charset = StandardCharsets.UTF_8
    return loader.load()
  }
}
