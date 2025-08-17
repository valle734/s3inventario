package com.libreria.model

data class Producto(
    val id: Int? = null, // Puede ser null al crear un nuevo producto
    var nombre: String,
    var precio: Double,
    var cantidad: Int
)
