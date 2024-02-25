package com.example.libreria.viewmodels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.libreria.clases.Autor
import com.example.libreria.conexionapi.LibreriaApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ViewModelGeneral: ViewModel() {
    private var _listaLibros = MutableStateFlow<MutableList<Autor>>(mutableListOf())
    val listaLibros = _listaLibros.asStateFlow()

    private var _listaAutores = MutableStateFlow<MutableList<Autor>>(mutableListOf())
    val listaAutores = _listaAutores.asStateFlow()

    val retrofit = Retrofit.Builder()
        .baseUrl("https://apt-trainer-414523.ew.r.appspot.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val retrofitService: LibreriaApi by lazy {
        retrofit.create(LibreriaApi::class.java)
    }

    fun getRecursos(recurso: String) {
        if (recurso == "autor") {
            viewModelScope.launch {
                try {
                    val response = retrofitService.GetAllAutores()
                    if (response.isSuccessful) {
                        val autoresList = response.body() ?: emptyList()
                        _listaLibros.value = autoresList.toMutableList()
                    } else {
                        Log.e("APIError", "Error en la respuesta: ${response.code()}")
                    }
                } catch (e: Exception) {
                    Log.e("APIError", "Excepción al obtener autores: $e")
                }
            }
        }else{
            viewModelScope.launch {
                try {
                    val response = retrofitService.GetAllAutores()
                    if (response.isSuccessful) {
                        val autoresList = response.body() ?: emptyList()
                        _listaAutores.value = autoresList.toMutableList()
                    } else {
                        Log.e("APIError", "Error en la respuesta: ${response.code()}")
                    }
                } catch (e: Exception) {
                    Log.e("APIError", "Excepción al obtener autores: $e")
                }
            }
        }
    }

    fun postRecurso() {

    }

    fun putRecurso() {

    }

    fun deleteRecurso() {

    }
}