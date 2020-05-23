package com.santiagorodriguezalberto.bookazonapp.model

import java.util.*

data class Biblioteca(
    val copias: List<Any>,
    val id: UUID,
    val latitud: Double,
    val longitud: Double,
    val nombre: String
)