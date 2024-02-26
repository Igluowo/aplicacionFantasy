package com.example.libreria.pantallas

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.libreria.clases.Autor
import com.example.libreria.clases.Libro
import com.example.libreria.viewmodels.ViewModelGeneral

@SuppressLint("CoroutineCreationDuringComposition", "SuspiciousIndentation")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PantallaMostrar(navController: NavController) {
    var showDialog by remember { mutableStateOf(false) }
    val viewModelDatos: ViewModelGeneral = viewModel()
    val recurso = viewModelDatos.escogerDato.collectAsState().value
    println("Hola soy $recurso")
    val lista = viewModelDatos.listaAutores.collectAsState().value
    var accion by remember { mutableStateOf("") }
    var searchId by remember { mutableStateOf("") }
    var shouldFetchBooks = remember { mutableStateOf(true) }
    var indice by remember { mutableStateOf(0) }
    var showDeleteDialog by remember { mutableStateOf(false) }
    LaunchedEffect(lista) {
        if (shouldFetchBooks.value) {
            println("Entra")
            viewModelDatos.getAutores("autor")
            shouldFetchBooks.value = false
        }
    }
    val pagerState = rememberPagerState(pageCount = { lista.size })
    HorizontalPager(
        state = pagerState,
        pageSize = PageSize.Fill,
        modifier = Modifier.fillMaxSize(),
        ) { index ->
        indice = index
            Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()) {
                Row {
                    OutlinedTextField(
                        value = searchId,
                        onValueChange = { searchId = it },
                        label = { Text("Buscar por ID o nombre") },
                    )
                    IconButton(
                        onClick = {
                            if (searchId.isDigitsOnly()) {
                                val idToSearch = searchId.toIntOrNull()
                                if (idToSearch != null) {
                                    viewModelDatos.getAutorById(idToSearch)
                                } else {
                                    viewModelDatos.getAutores(recurso)
                                }
                            }else{
                                viewModelDatos.getAutorByName(searchId)
                            }
                        },
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "")
                    }
                }
                var imagenUrl = ""
                    imagenUrl = (lista[index]).imagenUrl
                AsyncImage(
                    model = imagenUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .width(350.dp)
                        .height(350.dp)
                )
                    Text(text = (lista[index]).nombreAutor)
                    Text(text = (lista[index]).nacionalidad)

                Row(verticalAlignment = Alignment.Bottom, modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 150.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    ExtendedFloatingActionButton(onClick =  { accion = "Añadir"; showDialog = true }
                    ) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = null)
                        Text(text = "Añadir")
                    }
                    ExtendedFloatingActionButton(onClick =  { accion = "Modificar"; showDialog = true  }
                    ) {
                        Text(text = "Modificar")
                        Icon(imageVector = Icons.Default.Create, contentDescription = null)
                    }
                    ExtendedFloatingActionButton(onClick =  {
                        showDeleteDialog = true
                    }
                    ) {
                        Text(text = "Eliminar")
                        Icon(imageVector = Icons.Default.Delete, contentDescription = null)
                    }
                }
            }
    }
    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text("Eliminar recurso") },
            text = { Text("¿Estás seguro de que quieres eliminar este recurso?") },
            confirmButton = {
                Button(onClick = {
                    if (recurso == "autor") {
                        (lista[indice] as Autor).idAutor?.let {
                            viewModelDatos.deleteRecurso(
                                it, recurso
                            )
                            println("eliminado")
                            println("${lista[indice].idAutor}")
                    }
                    }else {
                        (lista[indice] as Libro).idLibro?.let {
                            viewModelDatos.deleteRecurso(
                                it, recurso
                            )
                        }
                    }
                    println("eliminado")
                    showDeleteDialog = false
                }) {
                    Text("Eliminar")
                }
            },
            dismissButton = {
                Button(onClick = { showDeleteDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }
    if (showDialog) {
        var nombre by remember { mutableStateOf("") }
        var nacionalidad by remember { mutableStateOf("") }
        var imagenUrl by remember { mutableStateOf("") }
        var nombreTitu = "Titulo"
        var nacionalidadAnyo = "Año de publicación"
        var autorId by remember { mutableStateOf("") }
        if (recurso == "autor") {
            nombreTitu = "Nombre"
            nacionalidadAnyo = "Nacionalidad"
        }
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = {
                Text(
                    text = "$accion $recurso",
                )
            },
            text = {
                Column {
                    OutlinedTextField(
                        value = nombre,
                        onValueChange = { nombre = it },
                        label = { Text(nombreTitu) },

                        )
                    OutlinedTextField(
                        value = nacionalidad,
                        onValueChange = { nacionalidad = it },
                        label = { Text(nacionalidadAnyo) },

                        )
                    if (recurso != "autor") {
                        OutlinedTextField(
                            value = autorId,
                            onValueChange = { autorId = it },
                            label = { Text("Id del autor") },
                            )
                    }
                    OutlinedTextField(
                        value = imagenUrl,
                        onValueChange = { imagenUrl = it },
                        label = { Text("URL de la imagen") },

                        )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (accion == "Añadir") {
                            if (lista[indice] is Autor) {
                                val autor = Autor(null, nombre, nacionalidad, imagenUrl)
                                viewModelDatos.postRecurso(autor)
                            }else{
                                val libro = Libro(null, nombre, nacionalidad, viewModelDatos.listaAutores.value.get(0), imagenUrl)
                                viewModelDatos.postRecurso(libro)
                            }
                        }else{
                            if (lista[indice] is Autor) {
                                val autor = Autor((lista[indice] as Autor).idAutor, nombre, nacionalidad, imagenUrl)
                                (lista[indice] as Autor).idAutor?.let { viewModelDatos.putRecurso(it, autor) }
                            }else{
                                val libro = Libro((lista[indice] as Libro).idLibro, nombre, nacionalidad, viewModelDatos.listaAutores.value.get(0), imagenUrl)
                                (lista[indice] as Libro).idLibro?.let { viewModelDatos.putRecurso(it, libro) }
                            }
                        }
                        shouldFetchBooks.value = true
                            showDialog = false
                    }, shape = RoundedCornerShape(50)

                ) {
                    Text("Añadir")
                }

            },
            dismissButton = {
                TextButton(
                    onClick = { showDialog = false },
                    shape = RoundedCornerShape(50)
                ) {
                    Text(
                        "Cancelar", color = MaterialTheme.colorScheme.error
                    )
                }
            },
            shape = RoundedCornerShape(12.dp)
        )
    }
}