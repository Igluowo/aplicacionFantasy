package com.example.aplicacionfantasy.pantallas

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.aplicacionfantasy.Listas
import com.example.aplicacionfantasy.Navegacion
import com.example.aplicacionfantasy.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaFantasy(navController: NavController, context: Context) {
    var query by remember { mutableStateOf("") }
    var activo by remember { mutableStateOf(false) }
    var paises = Listas().listaPaises
    Column {
        SearchBar(
            query = query,
            onQueryChange = { query = it },
            onSearch = { Toast.makeText(context, "Buscando...", Toast.LENGTH_SHORT).show(); activo = false },
            active = activo,
            onActiveChange = { activo = !activo},
            trailingIcon = {
                IconButton(onClick = { activo = false }) {
                Icon(imageVector = Icons.Default.Search, contentDescription = null)
            }
            },
            leadingIcon = { Icon(painterResource(id = R.drawable.valorant), contentDescription = null, modifier = Modifier.width(25.dp))},
            placeholder = { Text(text = "Buscar") }
        ) {
            val filtradoPaises = paises.filter { it.contains(query, true) }
            LazyColumn()
            {
                items(filtradoPaises){
                        nombre ->
                    ListItem(modifier = Modifier.clickable { query = nombre; activo = false },headlineContent = { Text(nombre) },
                        trailingContent = { Icon(imageVector = Icons.Default.AccountBox, contentDescription = null)},
                        leadingContent = { Icon(imageVector = Icons.Default.Star, contentDescription = null)})
                }
            }
        }
        Row {
            Button(onClick = { navController.navigate(route = Navegacion.PantallaAniadir.ruta) }) {
                Text(text = "Añadir")
            }
            Button(onClick = {  }) {
                Text(text = "Eliminar")
            }
        }
    }
}
