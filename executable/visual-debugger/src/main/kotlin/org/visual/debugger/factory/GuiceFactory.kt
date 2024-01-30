package org.visual.debugger.factory

import com.google.inject.ConfigurationException
import org.visual.debugger.context.DebuggerContext
import picocli.CommandLine

class GuiceFactory : CommandLine.IFactory {
    override fun <K : Any?> create(p0: Class<K>): K {
        return try {
            DebuggerContext.get(p0)
        } catch (ex: ConfigurationException) {
            CommandLine.defaultFactory().create(p0)
        }
    }
}