package com.santiagorodriguezalberto.bookazon.dtos

import com.santiagorodriguezalberto.bookazon.entity.Reserva
import com.santiagorodriguezalberto.bookazon.entity.Usuario
import java.util.*
import kotlin.collections.ArrayList

data class UserDTO(
        var nombre : String,
        var apellidos: String,
        var email: String,
        var dni: String,
        var telefono: Int,
        var profileImage: String? = null,
        var reservas: MutableList<Reserva>? = ArrayList(),
        val id: UUID? = null
)

fun Usuario.toUserDTO() = UserDTO(nombre, apellidos, email, dni, telefono, profileImage, reservas, id)

data class CreateUserDTO(
        var nombre: String,
        var apellidos: String,
        val email: String,
        val password: String,
        val password2: String,
        val dni: String,
        val telefono: Int
)
