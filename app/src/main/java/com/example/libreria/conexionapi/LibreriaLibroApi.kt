package com.example.libreria.conexionapi

import com.example.libreria.clases.Autor
import com.example.libreria.clases.Libro
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface LibreriaLibroApi {
    @GET("libros")
    @Headers("Accept: Application/json")
    suspend fun GetAllLibros(): Response<List<Libro>>

    @GET("libros/id/{libroid}")
    @Headers("Accept: Application/json")
    suspend fun GetLibrosById(@Path("libroid") libroId: Int): Response<Libro>

    @GET("libros/title/{libro}")
    @Headers("Accept: Application/json")
    suspend fun GetLibrosByName(@Path("libro") autorId: String): Response<List<Libro>>

    @POST("libros")
    fun postLibro(@Body datos: Libro): Call<Libro>

    @PUT("libros/{libroid}")
    fun putLibro(@Path("libroid") idLibro: Int, @Body datos: Libro): Call<Libro>

    @DELETE("libros/{libroid}")
    fun deleteLibro(@Path("libroid") idLibro: Int): Call<Libro>
}