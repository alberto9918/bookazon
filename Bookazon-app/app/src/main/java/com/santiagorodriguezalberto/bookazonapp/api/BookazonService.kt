package com.santiagorodriguezalberto.bookazonapp.api


import com.santiagorodriguezalberto.bookazonapp.api.request.LoginRequest
import com.santiagorodriguezalberto.bookazonapp.api.request.RegisterRequest
import com.santiagorodriguezalberto.bookazonapp.api.response.LoginResponse
import com.santiagorodriguezalberto.bookazonapp.model.Usuario
import retrofit2.Call
import retrofit2.http.*

interface BookazonService {
    @POST("/auth/login")
    fun doLogin(@Body request: LoginRequest): Call<LoginResponse>


    @POST("/user/")
    fun doSignup(@Body request: RegisterRequest): Call<Usuario>
}