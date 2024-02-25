package com.example.libreria.pantallas

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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.libreria.R
import com.example.libreria.navegacion.Navegacion
import com.example.libreria.clases.Tarjeta
import com.example.libreria.viewmodels.ViewModelGeneral

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaFantasy(
    navController: NavController,
    context: Context,
    listaTarjetas: ArrayList<Tarjeta>,
    id: Int
) {
    val viewModelEso: ViewModelGeneral = viewModel()
    var query by remember { mutableStateOf("") }
    var activo by remember { mutableStateOf(false) }
    var borrarPulsado by remember { mutableIntStateOf(0) }
    var tarjetas: MutableState<MutableList<Tarjeta>> = remember { mutableStateOf(mutableListOf()) }
    var id2 by remember { mutableIntStateOf(0) }
    if (id2 > 0) {}else{
        for (tarjeta in listaTarjetas) {
            tarjetas.value.add(tarjeta)
        }
    }
    val lista = viewModelEso.listaLibros.collectAsState().value
    viewModelEso.getRecursos("autor")
    id2++
    var habilitado by remember { mutableStateOf(true) }
    var tarjetasBorradas: MutableState<MutableList<Int>> = rememberSaveable { mutableStateOf(mutableListOf()) }
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
            //val filtradoPaises = paises.filter { it.contains(query, true) }
            LazyColumn()
            {
                items(lista){
                        nombre ->
                    ListItem(modifier = Modifier.clickable { query = ""; activo = false },
                        headlineContent = { Text(nombre.nacionalidad) },
                        trailingContent = { Icon(imageVector = Icons.Default.AccountBox, contentDescription = null)},
                        leadingContent = { Icon(imageVector = Icons.Default.Star, contentDescription = null)})
                }
            }
        }
        LazyColumn {
            items(lista) { tarjeta ->
                    if (query == "") {
                        Card(modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(modifier = Modifier.padding(30.dp, 10.dp)) {
                                    //Image(painter = painterResource(id = tarjeta.imagenUrl) , contentDescription = null,
                                      //  modifier = Modifier.width(80.dp)
                                    //)
                                }
                                Column(horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Text(text = tarjeta.nombreAutor)
                                    Text(text = "${tarjeta.nacionalidad}")
                                }
                                if (borrarPulsado >= 1) {
                                    var checkeado by remember { mutableStateOf(false) }
                                    if (borrarPulsado == 2 && checkeado) {
                                        tarjetasBorradas.value.add(tarjeta.idAutor)
                                        borrarPulsado = 0
                                    }else if (!checkeado && borrarPulsado == 3){
                                        borrarPulsado = 0
                                    }else{
                                        Checkbox(checked = checkeado, onCheckedChange = { checkeado = !checkeado })
                                    }
                                }
                            }
                        }
                    }else{
                        if (query == tarjeta.nacionalidad) {
                            Card(modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp), onClick = { }
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Box(modifier = Modifier.padding(30.dp, 10.dp)) {
                                        //Image(painter = painterResource(id = tarjeta.imagenUrl) , contentDescription = null,
                                          //  modifier = Modifier.width(80.dp)
                                        //)
                                    }
                                    Column(horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center
                                    ) {
                                        Text(text = tarjeta.nombreAutor)
                                        Text(text = "${tarjeta.nacionalidad}")
                                    }
                                    if (borrarPulsado >= 1) {
                                        var checkeado by remember { mutableStateOf(false) }
                                        if (borrarPulsado == 2 && checkeado) {
                                            tarjetasBorradas.value.add(tarjeta.idAutor)
                                            borrarPulsado = 0
                                        }else if (!checkeado && borrarPulsado == 3){
                                            borrarPulsado = 0
                                        }else{
                                            Checkbox(checked = checkeado, onCheckedChange = { checkeado = !checkeado })
                                        }
                                    }
                                }
                            }
                        }
                    }
                        }


            }

         }
        if (borrarPulsado == 3) {
            borrarPulsado = 0
        }


        Row(verticalAlignment = Alignment.Bottom, modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ExtendedFloatingActionButton(onClick =  {navController.navigate(route = Navegacion.PantallaAniadir.ruta) },

            ){
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
                Text(text = "Añadir")
            }
            ExtendedFloatingActionButton(onClick =  { borrarPulsado++  }
            ) {
                Text(text = "Eliminar")
                Icon(imageVector = Icons.Default.Delete, contentDescription = null)
            }
        }
    }


