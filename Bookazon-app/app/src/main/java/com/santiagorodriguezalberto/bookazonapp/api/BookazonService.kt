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
    /*@POST("/auth/login")
    fun doLogin(@Body request: LoginRequest): Call<LoginResponse>*/

    @POST("/auth/login")
    suspend fun doLogin(@Body request: LoginRequest): Response<LoginResponse>

    @POST("/user/")
    suspend fun doSignup(@Body request: RegisterRequest): Response<Usuario>

    @GET("/user/me")
    suspend fun getUser(): Response<Usuario>

    @PUT("/user/edit")
    suspend fun editNote(@Body request: EditUserRequest): Response<Usuario>

    @GET("/bibliotecas/")
    suspend fun getBibliotecas(): Response<List<Biblioteca>>

    @GET("/bibliotecas/{nombre}")
    suspend fun getBibliotecaByName(@Path("nombre") nombre: String): Response<Biblioteca>

    @GET("/copias/biblioteca/{biblioteca_name}")
    suspend fun getCopiasByBiblioteca(@Path("biblioteca_name") biblioteca_name: String): Response<List<Copia>>

    @GET("/copias/{id}")
    suspend fun getCopia(@Path("id") id: String): Response<Copia>

    @GET("/reservas/")
    suspend fun getReservasByUser(): Response<List<Reserva>>

    @GET("/reservas/reserva/{id_copia}")
    suspend fun getReservaByCopia(@Path("id_copia") id: String): Response<Reserva>

    @POST("/reservas/{id_copia}")
    suspend fun doReserva(@Path("id_copia") id: String): Response<Reserva>

    @DELETE("/reservas/{id}")
    suspend fun deleteReserva(@Path("id") id: String): Response<Void>


}