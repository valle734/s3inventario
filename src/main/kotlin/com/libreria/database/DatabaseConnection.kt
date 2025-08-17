package com.libreria.database

import java.sql.Connection
import java.sql.DriverManager

object DatabaseConnection {
    private const val url = "jdbc:postgresql://localhost:5432/inventario_db"
    private const val user = "postgres"
    private const val password = "matias734"

    init {
        // Registrar el driver de PostgreSQL
        Class.forName("org.postgresql.Driver")
    }

    fun getConnection(): Connection {
        return DriverManager.getConnection(url, user, password)
    }
}
