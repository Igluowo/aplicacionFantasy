package com.example.libreria.pantallas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.libreria.navegacion.Navegacion

@Composable
fun PantallaPrincipal(navController: NavController) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Bienvenido a Libreria Paquito",
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(10.dp)
        )
        Button(onClick = { navController.navigate(route = Navegacion.PantallaFantasy.ruta) }) {
            Text(text = "Presione aquí para continuar")
        }
    }
}