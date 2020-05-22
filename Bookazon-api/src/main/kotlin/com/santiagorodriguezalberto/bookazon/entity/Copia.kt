package com.santiagorodriguezalberto.bookazon.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotEmpty

@Entity
@Table(name = "Copia")
data class Copia(
        @NotEmpty
        var numero_copia: Int? = null,

        @NotEmpty
        var esta_reservada: Boolean = false,

        @JsonBackReference @ManyToOne @NotEmpty
        var biblioteca: Biblioteca,

        @OneToOne(mappedBy = "copia", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY)
        var reserva: Reserva


): Libro() {
}