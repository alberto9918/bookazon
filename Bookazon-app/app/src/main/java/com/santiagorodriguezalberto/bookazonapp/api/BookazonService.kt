package com.santiagorodriguezalberto.bookazonapp.api


import com.santiagorodriguezalberto.bookazonapp.api.request.LoginRequest
import com.santiagorodriguezalberto.bookazonapp.api.request.RegisterRequest
import com.santiagorodriguezalberto.bookazonapp.api.response.LoginResponse
import com.santiagorodriguezalberto.bookazonapp.model.Biblioteca
import com.santiagorodriguezalberto.bookazonapp.model.Copia
import com.santiagorodriguezalberto.bookazonapp.model.Usuario
import retrofit2.Call
import retrofit2.http.*

interface BookazonService {
    @POST("/auth/login")
    fun doLogin(@Body request: LoginRequest): Call<LoginResponse>

    @POST("/user/")
    fun doSignup(@Body request: RegisterRequest): Call<Usuario>

    @GET("/bibliotecas/")
    fun getBibliotecas(): Call<List<Biblioteca>>

    @GET("/bibliotecas/{nombre}")
    fun getBibliotecaByName(@Path("nombre") nombre: String): Call<Biblioteca>

    @GET("/copias/biblioteca/{biblioteca_name}")
    fun getCopiasByBiblioteca(@Path("biblioteca_name") biblioteca_name: String): Call<List<Copia>>

    @GET("/copias/{id}")
    fun getCopia(@Path("id") id: String): Call<Copia>


}