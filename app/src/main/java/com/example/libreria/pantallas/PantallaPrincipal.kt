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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.libreria.navegacion.Navegacion
import com.example.libreria.viewmodels.ViewModelGeneral

@Composable
fun PantallaPrincipal(navController: NavController) {
    val viewModelGeneral: ViewModelGeneral = viewModel()
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Bienvenido a Libreria Paquito",
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(10.dp)
        )
        Button(onClick = { navController.navigate(route = Navegacion.PantallaMostrar.ruta);
            viewModelGeneral.cambiarDatoMostrado("autor") }) {
            Text(text = "Acceder a autores")
        }
        Button(onClick = { navController.navigate(route = Navegacion.PantallaLibros.ruta);
            viewModelGeneral.cambiarDatoMostrado("libro") }) {
            Text(text = "Acceder a libros")
        }
    }
}