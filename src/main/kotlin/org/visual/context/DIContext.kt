package org.visual.context

import com.google.inject.Guice
import com.google.inject.Injector
import org.visual.module.RootModule

object DIContext {

  private val rootModule by lazy {
    RootModule()
  }

  private val injector by lazy {
    Guice.createInjector(rootModule)
  }
}