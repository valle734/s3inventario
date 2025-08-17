package com.libreria.dao

import com.libreria.database.DatabaseConnection
import com.libreria.model.Producto
import java.sql.ResultSet

class ProductoDAO {

    fun agregarProducto(producto: Producto) {
        val sql = "INSERT INTO productos(nombre, precio, cantidad) VALUES (?, ?, ?)"
        DatabaseConnection.getConnection().use { conn ->
            conn.prepareStatement(sql).use { stmt ->
                stmt.setString(1, producto.nombre)
                stmt.setDouble(2, producto.precio)
                stmt.setInt(3, producto.cantidad)
                stmt.executeUpdate()
                println("Producto agregado correctamente")
            }
        }
    }

    fun eliminarProducto(id: Int) {
        val sql = "DELETE FROM productos WHERE id = ?"
        DatabaseConnection.getConnection().use { conn ->
            conn.prepareStatement(sql).use { stmt ->
                stmt.setInt(1, id)
                val filas = stmt.executeUpdate()
                if (filas > 0) println("Producto eliminado") else println("Producto no encontrado")
            }
        }
    }

    fun listarProductos() {
        val sql = "SELECT * FROM productos"
        DatabaseConnection.getConnection().use { conn ->
            conn.createStatement().use { stmt ->
                val rs: ResultSet = stmt.executeQuery(sql)
                println("Lista de productos:")
                while (rs.next()) {
                    println("ID: ${rs.getInt("id")} | Nombre: ${rs.getString("nombre")} | Precio: ${rs.getDouble("precio")} | Stock: ${rs.getInt("cantidad")}")
                }
            }
        }
    }

    fun buscarPorNombre(nombre: String) {
        val sql = "SELECT * FROM productos WHERE nombre ILIKE ?"
        DatabaseConnection.getConnection().use { conn ->
            conn.prepareStatement(sql).use { stmt ->
                stmt.setString(1, "%$nombre%")
                val rs: ResultSet = stmt.executeQuery()
                println("🔎 Resultados de búsqueda:")
                while (rs.next()) {
                    println("ID: ${rs.getInt("id")} | Nombre: ${rs.getString("nombre")} | Precio: ${rs.getDouble("precio")} | Stock: ${rs.getInt("cantidad")}")
                }
            }
        }
    }

    fun actualizarStock(id: Int, nuevaCantidad: Int) {
        val sql = "UPDATE productos SET cantidad = ? WHERE id = ?"
        DatabaseConnection.getConnection().use { conn ->
            conn.prepareStatement(sql).use { stmt ->
                stmt.setInt(1, nuevaCantidad)
                stmt.setInt(2, id)
                val filas = stmt.executeUpdate()
                if (filas > 0) println(" Stock actualizado") else println(" Producto no encontrado")
            }
        }
    }
}
