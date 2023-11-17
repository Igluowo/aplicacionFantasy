package com.example.aplicacionfantasy

sealed class Navegacion(val ruta : String) {
    object PantallaPrincipal: Navegacion("PantallaPrincipal")
    object PantallaFantasy: Navegacion("PantallaFantasy")
}

