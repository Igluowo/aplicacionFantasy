package com.example.libreria.navegacion

sealed class Navegacion(val ruta : String) {
    object PantallaPrincipal: Navegacion("PantallaPrincipal")
    object PantallaFantasy: Navegacion("PantallaFantasy")
    object  PantallaAniadir: Navegacion("PantallaAniadir")
    object PantallaDetalles: Navegacion("PantallaDetalles")
}

