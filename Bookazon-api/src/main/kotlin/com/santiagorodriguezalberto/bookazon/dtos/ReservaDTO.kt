package com.santiagorodriguezalberto.bookazon.dtos

import com.fasterxml.jackson.annotation.JsonFormat
import com.santiagorodriguezalberto.bookazon.entity.Copia
import com.santiagorodriguezalberto.bookazon.entity.Reserva
import com.santiagorodriguezalberto.bookazon.entity.Usuario
import java.time.LocalDateTime
import java.util.*

data class ReservaDTO(

        var copia: CopiaDTO,

        var usuario: Usuario,

        var fecha_reserva: LocalDateTime,

        val id: UUID? = null

)

fun Reserva.toReservaDTO() = ReservaDTO(copia.toCopiaDTO(), usuario, fecha_reserva, id)

data class NuevaReservaDTO(
        var copia: Copia,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
        var fecha_reserva: LocalDateTime = LocalDateTime.now()
)

fun NuevaReservaDTO.toReserva(user: Usuario) = Reserva(copia, usuario = user, fecha_reserva = fecha_reserva)