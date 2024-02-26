package com.example.libreria.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.libreria.clases.Autor
import com.example.libreria.clases.Libro
import com.example.libreria.conexionapi.LibreriaAutorApi
import com.example.libreria.conexionapi.LibreriaLibroApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Objects

class ViewModelGeneral: ViewModel() {
    private var _listaLibros = MutableStateFlow<MutableList<Libro>>(mutableListOf())
    val listaLibros = _listaLibros.asStateFlow()

    private val _autorEncontrado = MutableStateFlow<Autor?>(null)
    val autorEncontrado: StateFlow<Autor?> = _autorEncontrado

    private var _listaAutores = MutableStateFlow<MutableList<Autor>>(mutableListOf())
    val listaAutores = _listaAutores.asStateFlow()

    private var _escogerDato = MutableStateFlow("autor")
    val escogerDato = _escogerDato.asStateFlow()

    val retrofit = Retrofit.Builder()
        .baseUrl("https://apt-trainer-414523.ew.r.appspot.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val retrofitServiceAutor: LibreriaAutorApi by lazy {
        retrofit.create(LibreriaAutorApi::class.java)
    }

    private val retrofitServiceLibro: LibreriaLibroApi by lazy {
        retrofit.create(LibreriaLibroApi::class.java)
    }

    fun getAutores(recursoSo: String) {
        println("hola el recurso es $recursoSo}")
        if (recursoSo == "autor") {

            viewModelScope.launch {
                try {
                    println("esta en autor")
                    val response = retrofitServiceAutor.GetAllAutores()
                    if (response.isSuccessful) {
                        println("no furula")
                        val autores = response.body() ?: emptyList()
                        _listaAutores.value = autores.toMutableList()
                        println(_listaAutores.value.size)
                    } else {
                        Log.e("APIError", "Error en la respuesta: ${response.code()}")
                    }
                } catch (e: Exception) {
                    Log.e("APIError", "Excepción al obtener autores: ${e.printStackTrace()}")
                }
            }
        }
    }

    fun getLibros() {
        viewModelScope.launch {
            try {
                val response = retrofitServiceLibro.GetAllLibros()
                if (response.isSuccessful) {
                    val libros = response.body() ?: emptyList()
                    _listaLibros.value = libros.toMutableList()
                    println("HAY ${_listaLibros.value.size}")
                } else {
                    Log.e("APIError", "Error en la respuesta: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("APIError", "Excepción al obtener autores: ${e.printStackTrace()}")
            }
        }
    }

    fun postRecurso(recurso: Any) {
        if (recurso is Autor) {
            retrofitServiceAutor.postAutor(recurso).enqueue(object : Callback<Autor> {
                override fun onResponse(call: Call<Autor>, response: Response<Autor>) {
                    if (response.isSuccessful) {

                    } else {
                        Log.e("APIError", "Error en la respuesta: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<Autor>, t: Throwable) {
                    Log.e("APIError", "Error en la solicitud: $t")
                }
            })
        } else if (recurso is Libro) {
            retrofitServiceLibro.postLibro(recurso).enqueue(object : Callback<Libro> {
                override fun onResponse(call: Call<Libro>, response: Response<Libro>) {
                    if (response.isSuccessful) {
                    } else {
                        Log.e("APIError", "Error en la respuesta: ${response.code()}")
                        Log.e("Queso", response.message().toString())
                    }
                }

                override fun onFailure(call: Call<Libro>, t: Throwable) {
                    Log.e("APIError", "Error en la solicitud: $t")
                }
            })
        }
    }

    fun putRecurso(id: Int, recurso: Any) {
        if (recurso is Autor) {
            retrofitServiceAutor.putAutor(id, recurso).enqueue(object : Callback<Autor> {
                override fun onResponse(call: Call<Autor>, response: Response<Autor>) {
                    if (response.isSuccessful) {

                    } else {
                        Log.e("APIError", "Error en la respuesta: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<Autor>, t: Throwable) {
                    Log.e("APIError", "Error en la solicitud: $t")
                }
            })
        } else if (recurso is Libro) {
            retrofitServiceLibro.putLibro(id, recurso).enqueue(object : Callback<Libro> {
                override fun onResponse(call: Call<Libro>, response: Response<Libro>) {
                    if (response.isSuccessful) {

                    } else {
                        Log.e("APIError", "Error en la respuesta: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<Libro>, t: Throwable) {
                    Log.e("APIError", "Error en la solicitud: $t")
                }
            })
        }
    }

    fun deleteRecurso(id: Int, recurso: Any) {
            println("eliminado")
            retrofitServiceAutor.deleteAutor(id).enqueue(object : Callback<Autor> {
                override fun onResponse(call: Call<Autor>, response: Response<Autor>) {
                    if (response.isSuccessful) {
                        println("eliminado")
                    } else {
                        Log.e("APIError", "Error en la respuesta: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<Autor>, t: Throwable) {
                    Log.e("APIError", "Error en la solicitud: $t")
                }
            })
    }

    fun getLibroById(id: Int) {
        viewModelScope.launch {
            try {
                val response = retrofitServiceLibro.GetLibrosById(id)
                if (response.isSuccessful) {
                    val libros = response.body()
                    _listaLibros.value.clear()
                    if (libros != null) {
                        _listaLibros.value.add(libros)
                    }
                    println(_listaLibros.value.size)
                } else {
                    Log.e("APIError", "Error en la respuesta: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("APIError", "Excepción al obtener autores: ${e.printStackTrace()}")
            }
        }
    }

    fun getAutorById(id: Int) {
        viewModelScope.launch {
            try {
                val response = retrofitServiceAutor.GetAutoresById(id)
                if (response.isSuccessful) {
                    val autores = response.body()
                    _listaAutores.value.clear()
                    if (autores != null) {
                        _listaAutores.value.add(autores)
                    }
                } else {
                    Log.e("APIError", "Error en la respuesta: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("APIError", "Excepción al obtener autores: ${e.printStackTrace()}")
            }
        }
    }

    fun getLibroByNombre(nombre: String) {
        viewModelScope.launch {
            try {
                val response = retrofitServiceLibro.GetLibrosByName(nombre)
                if (response.isSuccessful) {
                    val libros = response.body()
                    _listaLibros.value = response.body() as MutableList<Libro>
                    println(_listaLibros.value.size)
                } else {
                    Log.e("APIError", "Error en la respuesta: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("APIError", "Excepción al obtener autores: ${e.printStackTrace()}")
            }
        }
    }

    fun getAutorByName(nombre: String) {
        viewModelScope.launch {
            try {
                val response = retrofitServiceAutor.GetAutoresByName(nombre)
                if (response.isSuccessful) {
                    val libros = response.body()
                    _listaAutores.value = response.body() as MutableList<Autor>
                } else {
                    Log.e("APIError", "Error en la respuesta: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("APIError", "Excepción al obtener autores: ${e.printStackTrace()}")
            }
        }
    }

    fun cambiarDatoMostrado(recurso: String) {
        _escogerDato.value = recurso
    }
}
