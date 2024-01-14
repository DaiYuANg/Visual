package org.visual.model.sql

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.visual.model.sql.exception.TableNotExistsException
import java.io.Serializable
import java.util.concurrent.StructuredTaskScope
import kotlin.jvm.Throws

data class Database
    (
    private val jdbcUrl: String,
    private val username: String,
    private val password: String
) : Serializable {
    private val datasource by lazy {
        val config = HikariConfig().apply {
            jdbcUrl = this@Database.jdbcUrl
            username = this@Database.username
            password = this@Database.password
        }
        HikariDataSource(config)
    }
    val allDatabase by lazy {
        listDatabase(datasource.connection)
    }

    private val allTables by lazy {
        listTables(datasource.connection)
    }

    @Throws(TableNotExistsException::class)
    fun listTableColumn(table: String): Set<DatabaseTableColumn> {
        if (allTables.contains(table)) {
            return listColumns(datasource.connection, table)
        }
        throw TableNotExistsException()
    }

    fun listTableInfo(table: String): Triple<Set<String>, Set<DatabaseTableColumn>, Set<String>> {
        StructuredTaskScope<Any>().use {
            val a = it.fork { listPrimaryKeys(datasource.connection, table) }
            val b = it.fork { listTableColumn(table) }
            val c = it.fork { listPrimaryKeys(datasource.connection, table) }
            return Triple(a.get(), b.get(), c.get())
        }
    }
}