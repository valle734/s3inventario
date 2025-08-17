package com.libreria.main

import com.libreria.dao.ProductoDAO
import com.libreria.model.Producto
import java.util.Scanner

fun main() {
    val dao = ProductoDAO()
    val scanner = Scanner(System.`in`)

    while (true) {
        println("\n--- Sistema de Inventario Librería UJMD ---")
        println("1. Agregar producto")
        println("2. Eliminar producto")
        println("3. Ver lista de productos")
        println("4. Buscar producto por nombre")
        println("5. Actualizar stock")
        println("6. Salir")
        print("Selecciona una opción: ")

        when (scanner.nextInt()) {
            1 -> {
                print("Nombre: ")
                val nombre = readln()
                print("Precio: ")
                val precio = readln().toDouble()
                print("Cantidad: ")
                val cantidad = readln().toInt()
                val producto = Producto(nombre = nombre, precio = precio, cantidad = cantidad)
                dao.agregarProducto(producto)
            }
            2 -> {
                print("ID del producto a eliminar: ")
                val id = readln().toInt()
                dao.eliminarProducto(id)
            }
            3 -> dao.listarProductos()
            4 -> {
                print("Nombre a buscar: ")
                val nombre = readln()
                dao.buscarPorNombre(nombre)
            }
            5 -> {
                print("ID del producto a actualizar: ")
                val id = readln().toInt()
                print("Nueva cantidad: ")
                val cantidad = readln().toInt()
                dao.actualizarStock(id, cantidad)
            }
            6 -> {
                println("Saliendo...")
                return
            }
            else -> println("Opción inválida")
        }
    }
}
