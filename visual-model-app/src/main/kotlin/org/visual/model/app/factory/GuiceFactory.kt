package org.visual.model.app.factory

import com.google.inject.ConfigurationException
import org.visual.model.app.DIContainer
import picocli.CommandLine

class GuiceFactory : CommandLine.IFactory {
    override fun <K : Any?> create(p0: Class<K>?): K {
        return try {
            DIContainer.injector.getInstance(p0)
        } catch (ex: ConfigurationException) {
            CommandLine.defaultFactory().create(p0)
        }
    }
}