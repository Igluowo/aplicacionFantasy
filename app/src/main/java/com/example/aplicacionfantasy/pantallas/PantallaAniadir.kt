package com.example.aplicacionfantasy.pantallas

import android.content.Context
import android.os.Build
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ScrollState
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
import com.example.aplicacionfantasy.Listas
import com.example.aplicacionfantasy.R
import com.example.aplicacionfantasy.Tarjeta
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
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
        var fieldEquipo = AgregarDropDown(lista = Listas().listaEquiposNombre)
        AgregarTexo(texto = "Introduce país del equipo:")
        var fieldPais =AgregarDropDown(lista = Listas().listaPaises)
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
        var fieldVS = AgregarDropDown(lista = Listas().listaEquiposNombre)
        Spacer(modifier = Modifier.padding(15.dp))
        var fecha = AgregarDate()
        var fechaString = "${fecha?.dayOfMonth}/${fecha?.monthValue}/${fecha?.year}"
        BackHandler {
            var imagen = EscogerImagen(fieldEquipo)
            var historia = escogerHistoria(fieldEquipo)
            listaTarjetas.add(Tarjeta(id, imagen, fieldEquipo, fieldVS, fechaString, posicionSlider, fieldPais, historia))
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
        "Loud" -> imagen = R.drawable.loud
        "Evil Geniuses" -> imagen = R.drawable.evil
        "Paper Rex" -> imagen = R.drawable.paperrex
        "Fnatic" -> imagen = R.drawable.fnatic
        "Optic Gaming" -> imagen = R.drawable.optic
        "DRX" -> imagen = R.drawable.drx
        "FunPlus Fenix" -> imagen = R.drawable.funplus
        "ZETA DIVISION" -> imagen = R.drawable.zeta
        "G2 Esports" -> imagen = R.drawable.g2
        "Team Liquid" -> imagen = R.drawable.teamliquid
        "KRU Esports" -> imagen = R.drawable.krus
        "EDward Gaming" -> imagen = R.drawable.edward
        "Xerxia" -> imagen = R.drawable.xerxia
        "T1" -> imagen = R.drawable.t1
        "XSET" -> imagen = R.drawable.xset
        "NRG" -> imagen = R.drawable.nrg
        "NAVI" -> imagen = R.drawable.navi
        "The Guard" -> imagen = R.drawable.guard
        "Leviatan Esports" -> imagen = R.drawable.leviatan
        "100 Thieves" -> imagen = R.drawable.hundredthieves
        "Cloud9" -> imagen = R.drawable.cloud9
        "Giant Gaming" -> imagen = R.drawable.giant
        "Bleed Esport" -> imagen = R.drawable.bleed
        "FURIA Esports" -> imagen = R.drawable.furia
        "Bilibili Gaming" -> imagen = R.drawable.bilibili
        "BOOM Esports" -> imagen = R.drawable.boom
    }
    return imagen
}

fun escogerHistoria(equipo: String): Int {
    var historia = 0
    when (equipo) {
        "Loud" -> historia = R.string.loud
        "Evil Geniuses" -> historia = R.string.loud
        "Paper Rex" -> historia = R.string.loud
        "Fnatic" -> historia = R.string.loud
        "Optic Gaming" -> historia = R.string.loud
        "DRX" -> historia = R.string.drx
        "FunPlus Fenix" -> historia = R.string.loud
        "ZETA DIVISION" -> historia = R.string.loud
        "G2 Esports" -> historia = R.string.loud
        "Team Liquid" -> historia = R.string.loud
        "KRU Esports" -> historia = R.string.bilibili
        "EDward Gaming" -> historia = R.string.bilibili
        "Xerxia" -> historia = R.string.bilibili
        "T1" -> historia = R.string.bilibili
        "XSET" -> historia = R.string.bilibili
        "NRG" -> historia = R.string.bilibili
        "NAVI" -> historia = R.string.bilibili
        "The Guard" -> historia = R.string.bilibili
        "Leviatan Esports" -> historia = R.string.bilibili
        "100 Thieves" -> historia = R.string.bilibili
        "Cloud9" -> historia = R.string.cloud9
        "Giant Gaming" -> historia = R.string.bilibili
        "Bleed Esport" -> historia = R.string.bleed
        "FURIA Esports" -> historia = R.string.bilibili
        "Bilibili Gaming" -> historia = R.string.bilibili
        "BOOM Esports" -> historia = R.string.boom
    }
    return historia
}