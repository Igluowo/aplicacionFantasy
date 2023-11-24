package com.example.aplicacionfantasy.pantallas

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.aplicacionfantasy.Listas

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaAniadir(navController: NavController, context: Context) {
    var valorEquipo by remember { mutableStateOf("") }
    var desplegadoEquipo by remember { mutableStateOf(false) }
    Column(Modifier.fillMaxSize()) {
        AgregarTexo(texto = "Introduce un equipo:")
        ExposedDropdownMenuBox(expanded = desplegadoEquipo, onExpandedChange = { desplegadoEquipo = true}) {
            TextField(value = valorEquipo, onValueChange = { valorEquipo = it }, label = { Text(text = "Seleccione un valor")},
                readOnly = true, trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = desplegadoEquipo)})
        }
        AgregarTexo(texto = "Introduce país del equipo:")
    }
}

@Composable
fun AgregarTexo(texto: String) {
    Text(text = texto, modifier = Modifier.padding(20.dp))
}

@Composable
fun AgregarDropDown(desplegado: Boolean, lista: List<Listas>) {

}