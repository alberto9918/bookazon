package com.santiagorodriguezalberto.bookazonapp.model

import java.time.LocalDateTime
import java.util.*

data class Reserva(
   val copia: Copia,

   val usuario: Usuario,

   val fecha_reserva: String,

   val id: UUID
)