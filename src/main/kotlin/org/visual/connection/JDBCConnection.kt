package org.visual.connection

data class JDBCConnection(
    val driver: String,
    val jdbcPassword: String,
    val database: String,
    val save: Boolean
) {}
