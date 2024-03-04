package org.visual.designer.connection

data class JDBCConnection(
    val driver: String,
    val jdbcPassword: String,
    val database: String,
    val save: Boolean
) {}
