package org.visual.connection

data class ConnectionOptions(
    val readonly: Boolean,
    val transaction: Boolean,
    val sync: Boolean,
) {}
