package com.santiagorodriguezalberto.bookazonapp.api.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.santiagorodriguezalberto.bookazonapp.model.Usuario

data class LoginResponse(
    @SerializedName("token")
    @Expose
    val token: String,
    @SerializedName("user")
    @Expose
    val user: Usuario
)