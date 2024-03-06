package org.visual.module

import com.google.common.util.concurrent.ThreadFactoryBuilder
import com.google.inject.AbstractModule
import com.google.inject.Provides
import org.visual.shared.OS
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy
import java.util.concurrent.TimeUnit

class RootModule:AbstractModule() {

  override fun configure() {

  }

  @Provides
  fun executor(): ThreadPoolExecutor {
    return ThreadPoolExecutor(
      OS.cpuCore,
      OS.cpuCore + 1,
      1,
      TimeUnit.MINUTES,
      ArrayBlockingQueue(200),
      ThreadFactoryBuilder().setDaemon(true).setNameFormat("visual-model-%s").build(),
      CallerRunsPolicy()
    )
  }
}