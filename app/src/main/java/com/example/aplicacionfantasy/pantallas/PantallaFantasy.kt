package com.example.aplicacionfantasy.pantallas

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults.TrailingIcon
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaFantasy(navController: NavController, context: Context) {
    var query by remember { mutableStateOf("") }
    var activo by remember { mutableStateOf(false) }
    Column {
        SearchBar(
            query = query,
            onQueryChange = { query = it },
            onSearch = { Toast.makeText(context, "Buscando...", Toast.LENGTH_SHORT).show(); activo = false },
            active = activo,
            onActiveChange = { activo = it},
            trailingIcon = {
                IconButton(onClick = { activo = false }) {
                Icon(imageVector = Icons.Default.Search, contentDescription = null)
            }

            },
            placeholder = { Text(text = "Buscar") }
        ) {

        }
    }
}