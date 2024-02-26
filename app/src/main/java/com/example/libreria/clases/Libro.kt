package com.example.libreria.clases

import com.google.gson.annotations.SerializedName

data class Libro(@SerializedName("idLibro")
                 val idLibro: Int? = null,
                 val titulo: String,
                 val añoPublicacion: String,
                 val idAutor: Autor? = null,
                 val imagenUrl: String) {
}