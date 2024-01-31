package org.visual.database.context

import io.avaje.inject.BeanScope
import java.nio.charset.StandardCharsets
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.util.Callback
import lombok.SneakyThrows
import org.visual.database.VisualDatabase
import org.visual.database.constant.FXMLKey

object VisualDatabaseContext {

  private val beanScope = BeanScope.builder().shutdownHook(true).build()

  fun <T> get(clazz: Class<T>): T {
    return beanScope.get(clazz)
  }

  @SneakyThrows
  fun load(fxml: FXMLKey): Parent {
    val loader = FXMLLoader(VisualDatabase::class.java.getResource("${fxml.key}.fxml"))
    loader.controllerFactory = Callback { aClass: Class<*> -> beanScope.get(aClass) }
    loader.charset = StandardCharsets.UTF_8
    return loader.load()
  }
}
