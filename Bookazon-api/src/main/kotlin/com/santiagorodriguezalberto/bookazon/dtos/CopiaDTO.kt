package com.santiagorodriguezalberto.bookazon.dtos

import com.fasterxml.jackson.annotation.JsonBackReference
import com.santiagorodriguezalberto.bookazon.entity.Biblioteca
import com.santiagorodriguezalberto.bookazon.entity.Copia
import com.santiagorodriguezalberto.bookazon.entity.Reserva
import java.util.*
import javax.persistence.CascadeType
import javax.persistence.FetchType
import javax.persistence.ManyToOne
import javax.persistence.OneToOne
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class CopiaDTO(

        var titulo: String,

        var autor: String,

        var resumen: String,

        var genero: String,

        var isbn: String,

        var editorial: String,

        var imagen: String? = null,

        var numero_copia: Int? = null,

        var esta_reservada: Boolean = false,

        var biblioteca: Biblioteca,

        var reserva: Reserva? = null,

        val id: UUID? = null
)

fun Copia.toCopiaDTO() = CopiaDTO(titulo, autor, resumen, genero, isbn, editorial, imagen, numero_copia, esta_reservada, biblioteca, reserva, id)