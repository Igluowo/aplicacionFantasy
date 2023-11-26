package com.example.aplicacionfantasy.pantallas

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.aplicacionfantasy.Listas
import com.example.aplicacionfantasy.Navegacion
import com.example.aplicacionfantasy.R
import com.example.aplicacionfantasy.Tarjeta

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaFantasy(navController: NavController, context: Context) {
    var query by remember { mutableStateOf("") }
    var activo by remember { mutableStateOf(false) }
    var borrarPulsado by remember { mutableStateOf(false) }
    var paises = Listas().listaPaises
    var tarjetas = ArrayList<Tarjeta>()
    for (i in 1 .. 5) {
        tarjetas.add(Tarjeta(R.drawable.cloud9, "Cloud9", "VS G2", "10/12/2023", 5))
    }
    Column(modifier = Modifier.fillMaxSize()) {
        SearchBar(
            query = query,
            onQueryChange = { query = it },
            onSearch = { Toast.makeText(context, "Buscando...", Toast.LENGTH_SHORT).show(); activo = false },
            active = activo,
            onActiveChange = { activo = !activo},
            trailingIcon = {
                IconButton(onClick = { activo = false }) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = null)
                }},
                leadingIcon = { Icon(painterResource(id = R.drawable.valorant), contentDescription = null,
                    modifier = Modifier.width(25.dp))},
                placeholder = { Text(text = "Buscar") }
            ) {
            val filtradoPaises = paises.filter { it.contains(query, true) }
            LazyColumn()
            {
                items(filtradoPaises){
                        nombre ->
                    ListItem(modifier = Modifier.clickable { query = nombre; activo = false },
                        headlineContent = { Text(nombre) },
                        trailingContent = { Icon(imageVector = Icons.Default.AccountBox, contentDescription = null)},
                        leadingContent = { Icon(imageVector = Icons.Default.Star, contentDescription = null)})
                }
            }
        }
        LazyColumn {
            items(tarjetas) { tarjeta ->
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(modifier = Modifier.padding(30.dp, 10.dp)) {
                            Image(painter = painterResource(id = tarjeta.imagen) , contentDescription = null,
                                modifier = Modifier.width(80.dp))
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                            Text(text = tarjeta.equipo)
                            Text(text = "${tarjeta.equipoVS}, ${tarjeta.fecha}")
                            Text(text = "${tarjeta.puntos}")
                        }
                        if (borrarPulsado) {
                            var checkeado by remember { mutableStateOf(false) }
                            Checkbox(checked = checkeado, onCheckedChange = { checkeado = !checkeado})
                        }
                    }
                }
            }
        }
        Row(verticalAlignment = Alignment.Bottom, modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween) {
            ExtendedFloatingActionButton(onClick =  {navController.navigate(route = Navegacion.PantallaAniadir.ruta)},
                elevation = FloatingActionButtonDefaults.elevation(20.dp)){
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
                Text(text = "Añadir")
            }
            ExtendedFloatingActionButton(onClick =  { borrarPulsado = true }) {
                Text(text = "Eliminar")
                Icon(imageVector = Icons.Default.Delete, contentDescription = null)
            }
        }
    }
}
