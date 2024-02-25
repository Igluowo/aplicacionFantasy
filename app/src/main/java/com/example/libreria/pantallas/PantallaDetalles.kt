package com.example.libreria.pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController

@Composable
fun PantallaDetalles(navController: NavController, equipo: String, equipoVS: String, pais: String,
                     mapas: Int, mapasMedia: Float, imagen: Int, partidos: Int, historia: Int) {
    Column {
        Text(text = "$equipo equipo de $pais")
        Image(painter = painterResource(id = imagen), contentDescription = null)
        Text(text = "Estadisticas de un total de $partidos partidas jugadas: media de mapas ganados: $mapas" +
                ", pero en el partido vs $equipoVS ganaron $mapas" + stringResource(id = historia))
    }
}