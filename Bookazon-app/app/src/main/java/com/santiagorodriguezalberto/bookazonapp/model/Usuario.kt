package com.santiagorodriguezalberto.bookazonapp.model

import java.util.*

data class Usuario(
    val nombre: String,
    val apellidos: String,
    val email: String,
    val dni: String,
    val telefono: Int,
    val profileImage: String,
    val reservas: List<Reserva>,
    val id: UUID

)