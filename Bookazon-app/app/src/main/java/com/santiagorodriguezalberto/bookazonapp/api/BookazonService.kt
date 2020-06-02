package com.santiagorodriguezalberto.bookazonapp.api


import com.santiagorodriguezalberto.bookazonapp.api.request.EditUserRequest
import com.santiagorodriguezalberto.bookazonapp.api.request.LoginRequest
import com.santiagorodriguezalberto.bookazonapp.api.request.RegisterRequest
import com.santiagorodriguezalberto.bookazonapp.api.response.LoginResponse
import com.santiagorodriguezalberto.bookazonapp.model.Biblioteca
import com.santiagorodriguezalberto.bookazonapp.model.Copia
import com.santiagorodriguezalberto.bookazonapp.model.Reserva
import com.santiagorodriguezalberto.bookazonapp.model.Usuario
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface BookazonService {

    //METODOS DE USUARIO
    @POST("/auth/login")
    suspend fun doLogin(@Body request: LoginRequest): Response<LoginResponse>

    @POST("/user/")
    suspend fun doSignup(@Body request: RegisterRequest): Response<Usuario>

    @GET("/user/me")
    suspend fun getUser(): Response<Usuario>

    @PUT("/user/edit")
    suspend fun editUsuario(@Body request: EditUserRequest): Response<Usuario>

    //METODOS DE BIBLIOTECA
    @GET("/bibliotecas/")
    suspend fun getBibliotecas(): Response<List<Biblioteca>>

    @GET("/bibliotecas/{nombre}")
    suspend fun getBibliotecaByName(@Path("nombre") nombre: String): Response<Biblioteca>

    //METODOS DE COPIA
    @GET("/copias/biblioteca/{biblioteca_name}")
    suspend fun getCopiasByBiblioteca(@Path("biblioteca_name") biblioteca_name: String): Response<List<Copia>>

    @GET("/copias/{id}")
    suspend fun getCopia(@Path("id") id: String): Response<Copia>

    @GET("/copias/biblioteca/{biblioteca_name}/generos")
    suspend fun getGeneros(@Path("biblioteca_name") biblioteca_name: String): Response<List<String>>

    @GET("/copias/biblioteca/{biblioteca_name}/autores")
    suspend fun getAutores(@Path("biblioteca_name") biblioteca_name: String): Response<List<String>>

    @GET("/copias/biblioteca/{biblioteca_name}/genero/{genero}")
    suspend fun getCopiasByGenero(@Path("biblioteca_name") biblioteca_name: String, @Path("genero") genero: String): Response<List<Copia>>

    @GET("/copias/biblioteca/{biblioteca_name}/autor/{autor}")
    suspend fun getCopiasByAutor(@Path("biblioteca_name") biblioteca_name: String, @Path("autor") autor: String): Response<List<Copia>>


    //METODOS DE RESERVA
    @GET("/reservas/")
    suspend fun getReservasByUser(): Response<List<Reserva>>

    @GET("/reservas/reserva/{id_copia}")
    suspend fun getReservaByCopia(@Path("id_copia") id: String): Response<Reserva>

    @POST("/reservas/{id_copia}")
    suspend fun doReserva(@Path("id_copia") id: String): Response<Reserva>

    @DELETE("/reservas/{id}")
    suspend fun deleteReserva(@Path("id") id: String): Response<Void>


}