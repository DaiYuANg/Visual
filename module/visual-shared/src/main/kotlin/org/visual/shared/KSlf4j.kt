package org.visual.shared

import io.github.oshai.kotlinlogging.KLogger
import io.github.oshai.kotlinlogging.KotlinLogging

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class KSlf4j{
    companion object{
        val <reified T> T.log: KLogger
            inline get() = KotlinLogging.logger{T::class.java.name}
    }
}
