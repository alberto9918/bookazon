package com.santiagorodriguezalberto.bookazonapp.api.request

data class RegisterRequest(
    val nombre: String,
    val apellidos: String,
    val email: String,
    val password: String,
    val password2: String,
    val dni: String,
    val telefono: Int
)