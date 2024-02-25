package com.example.libreria.pantallas

import android.content.Context
import android.os.Build
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

import com.example.libreria.clases.Tarjeta
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaAniadir(navController: NavController, context: Context, listaTarjetas: ArrayList<Tarjeta>, id: Int) {
    var posicionSlider by remember { mutableIntStateOf(0) }
    val listaMapas = listOf<String>("Bind", "Icebox", "Fracture", "Haven", "Split", "Lotus", "Ascent")
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        AgregarTexo(texto = "Introduce un equipo:")
        //var fieldEquipo = AgregarDropDown(lista = Listas().listaEquiposNombre)
        AgregarTexo(texto = "Introduce país del equipo:")
        //var fieldPais =AgregarDropDown(lista = Listas().listaPaises)
        AgregarTexo(texto = "Mapas jugados:")
        for (elemento in listaMapas) {
            Row {
                mapasRadios(texto = elemento)
            }
        }
        AgregarTexo(texto = "Mapas ganandos:")
        Row(horizontalArrangement = Arrangement.Center) {
            posicionSlider = AgregarSlider().toInt()
            Text(text = "$posicionSlider")
        }
        AgregarTexo(texto = "El equipo contra quien se jugo:")
        //var fieldVS = AgregarDropDown(lista = Listas().listaEquiposNombre)
        Spacer(modifier = Modifier.padding(15.dp))
        var fecha = AgregarDate()
        var fechaString = "${fecha?.dayOfMonth}/${fecha?.monthValue}/${fecha?.year}"
        BackHandler {
            //var imagen = EscogerImagen(fieldEquipo)
            //var historia = escogerHistoria(fieldEquipo)
            //listaTarjetas.add(Tarjeta(id, imagen, fieldEquipo, fieldVS, fechaString, posicionSlider, fieldPais, historia))
            navController.popBackStack()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgregarDropDown(lista: List<String>): String {
    var desplegado by remember { mutableStateOf(false) }
    var opcion by remember { mutableStateOf(lista.get(0)) }
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()) {
        ExposedDropdownMenuBox(expanded = desplegado, onExpandedChange = { desplegado = true }
        ) {
            TextField(
                value = opcion,
                onValueChange = {},
                label = { Text(text = "Resultado") },
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = desplegado) },
                modifier = Modifier.menuAnchor()
            )
            DropdownMenu(
                expanded = desplegado,
                onDismissRequest = { desplegado = false },
                modifier = Modifier.width(TextFieldDefaults.MinWidth)
            ) {
                lista.forEach { elemento ->
                    DropdownMenuItem(
                        text = { Text(text = elemento) },
                        onClick = { opcion = elemento; desplegado = false })
                }
            }
        }
    }
    return opcion
}

@Composable
fun AgregarTexo(texto: String) {
    Text(text = texto, modifier = Modifier.padding(20.dp))
}

@Composable
fun mapasRadios(texto: String) {
    var seleccionado by remember { mutableStateOf(false) }
    RadioButton(selected = seleccionado,
        onClick = { seleccionado = !seleccionado }
    )
    Text(text = texto)
}

@Composable
fun AgregarSlider(): Float {
    var posicionSlider by remember { mutableFloatStateOf(0f) }
    Slider(value = posicionSlider,
        onValueChange = { posicionSlider = it},
        steps = 4,
        valueRange = 0f..5f,
        modifier = Modifier.width(335.dp)
    )
    return posicionSlider
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgregarDate(): LocalDate? {
    val state = rememberDatePickerState(initialDisplayMode = DisplayMode.Input)
    DatePicker(state = state,
        title = { Row {
            Text(text = "Introduce la fecha del partido")
            }
        }
    )
    var localDate = state?.selectedDateMillis?.let {
        Instant.ofEpochMilli(it).atZone(ZoneId.of("UTC")).toLocalDate()
    }
    return localDate
}

fun EscogerImagen(equipo: String): Int {
    var imagen = 0
    when (equipo) {

    }
    return imagen
}

fun escogerHistoria(equipo: String): Int {
    var historia = 0
    when (equipo) {

    }
    return historia
}