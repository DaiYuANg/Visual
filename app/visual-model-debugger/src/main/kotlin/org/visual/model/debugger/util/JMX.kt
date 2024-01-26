@file:JvmName("JMXUtil")

package org.visual.model.debugger.util

import java.util.*
import org.visual.model.shared.util.NetUtil.randomPort

private const val JMX_PORT_PROPERTY = "com.sun.management.jmxremote.port"
private const val JMX_AUTHENTICATE_PROPERTY = "com.sun.management.jmxremote.authenticate"
private const val JMX_SSL_PROPERTY = "com.sun.management.jmxremote.ssl"

@JvmOverloads
fun jmxPropertiesBuilder(
    port: Int = randomPort(),
    auth: Boolean = false,
    ssl: Boolean = false
): Properties {
  return Properties().apply {
    put(JMX_PORT_PROPERTY, port)
    put(JMX_AUTHENTICATE_PROPERTY, auth)
    put(JMX_SSL_PROPERTY, ssl)
  }
}
