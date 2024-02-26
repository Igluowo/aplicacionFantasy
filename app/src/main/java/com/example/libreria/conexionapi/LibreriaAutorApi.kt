package com.example.libreria.conexionapi

import com.example.libreria.clases.Autor
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface LibreriaAutorApi {
    @GET("autores")
    @Headers("Accept: Application/json")
    suspend fun GetAllAutores(): Response<List<Autor>>

    @GET("autores/id/{autorid}")
    @Headers("Accept: Application/json")
    suspend fun GetAutoresById(@Path("autorid") autorId: Int): Response<Autor>

    @GET("autores/name/{autor}")
    @Headers("Accept: Application/json")
    suspend fun GetAutoresByName(@Path("autor") autorId: String): Response<List<Autor>>

    @GET("autores/grouped-nacionalidad")
    @Headers("Accept: Application/json")
    suspend fun GetAutoresGroupedByNacionalidad(): Response<List<Autor>>

    @POST("autores")
    fun postAutor(@Body datos: Autor): Call<Autor>

    @PUT("autores/{autorid}")
    fun putAutor(@Path("autorid") idAutor: Int, @Body datos: Autor): Call<Autor>

    @DELETE("autores/{autorid}")
    fun deleteAutor(@Path("autorid") idAutor: Int): Call<Autor>
}