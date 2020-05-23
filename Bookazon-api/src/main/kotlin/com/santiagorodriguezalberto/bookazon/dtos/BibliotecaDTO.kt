package com.santiagorodriguezalberto.bookazon.dtos

import com.santiagorodriguezalberto.bookazon.entity.Biblioteca
import com.santiagorodriguezalberto.bookazon.entity.Copia
import com.santiagorodriguezalberto.bookazon.entity.Reserva
import com.santiagorodriguezalberto.bookazon.entity.Usuario
import java.util.*
import kotlin.collections.ArrayList

data class BibliotecaDTO(
        var nombre : String,
        var copias: MutableList<Copia>? = ArrayList(),
        val id: UUID? = null
)

fun Biblioteca.toBibliotecaDTO() = BibliotecaDTO(nombre, copias, id)
