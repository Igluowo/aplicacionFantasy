package com.example.libreria.conexionapi

import com.example.libreria.clases.Autor
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface LibreriaApi {
    @GET("autores")
    @Headers("Accept: Application/json")
    suspend fun GetAllAutores(): Response<List<Autor>>

    @GET("autores/id/{autorid}")
    @Headers("Accept: Application/json")
    suspend fun GetAutoresById(@Path("autorid") autorId: Int): Response<List<Autor>>

    @GET("autores/name/{autor}")
    @Headers("Accept: Application/json")
    suspend fun GetAutoresByName(@Path("autor") autorId: Int): Response<List<Autor>>

    @GET("autores/grouped-nacionalidad")
    @Headers("Accept: Application/json")
    suspend fun GetAutoresGroupedByNacionalidad(): Response<List<Autor>>
}