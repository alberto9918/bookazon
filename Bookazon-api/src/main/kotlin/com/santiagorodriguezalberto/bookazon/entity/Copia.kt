package com.santiagorodriguezalberto.bookazon.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@Entity
@Table(name = "Copia")
data class Copia(
        @NotEmpty
        @NotNull
        override var titulo: String,

        @NotEmpty
        @NotNull
        override var autor: String,

        @NotEmpty
        @NotNull
        override var genero: String,

        @NotEmpty
        @NotNull
        override var isbn: String,

        @NotEmpty
        @NotNull
        override var editorial: String,

        override var imagen: String? = null,

        @NotEmpty
        var numero_copia: Int? = null,

        @NotEmpty
        var esta_reservada: Boolean = false,

        @JsonBackReference @ManyToOne @NotEmpty
        var biblioteca: Biblioteca,

        @OneToOne(mappedBy = "copia", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY)
        var reserva: Reserva? = null


): Libro(titulo,autor,genero,isbn,editorial,imagen) {
}