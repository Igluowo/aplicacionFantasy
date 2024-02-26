package com.example.libreria.navegacion

sealed class Navegacion(val ruta : String) {
    object PantallaPrincipal: Navegacion("PantallaPrincipal")
    object PantallaMostrar: Navegacion("PantallaMostrar")
    object PantallaLibros: Navegacion("PantallaLibros")
}

