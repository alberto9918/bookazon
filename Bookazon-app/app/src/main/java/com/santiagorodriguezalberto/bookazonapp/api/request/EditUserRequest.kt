package com.santiagorodriguezalberto.bookazonapp.api.request

data class EditUserRequest (
    val nombre: String,
    val apellidos: String,
    val email: String,
    val dni: String,
    val telefono: Int
)