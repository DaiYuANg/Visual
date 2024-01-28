package org.visual.database.connection

data class ConnectionOptions(
    val readonly: Boolean,
    val transaction: Boolean,
    val sync: Boolean,
) {
}