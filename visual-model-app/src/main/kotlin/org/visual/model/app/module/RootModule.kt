package org.visual.model.app.module

import com.google.inject.AbstractModule
import org.github.gestalt.config.Gestalt
import org.visual.model.app.workspace.ClassModel
import java.util.concurrent.Executor
import java.util.concurrent.Executors

//
class RootModule : AbstractModule() {
    private val executor = Executors.newThreadPerTaskExecutor(Thread.ofVirtual().name("visual-async", 0).factory())
    override fun configure() {
        bind(Executor::class.java).toInstance(executor)
        bind(ClassModel::class.java).toInstance(ClassModel())
    }
}
