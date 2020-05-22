package com.santiagorodriguezalberto.bookazon.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotEmpty

@Entity
@Table(name = "Reserva")
data class Reserva(
        @Id
        @GeneratedValue
        val id: UUID? = null,

        @OneToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "copia_id")
        var copia: Copia,

        @JsonBackReference @ManyToOne
        var usuario: Usuario,

        @NotEmpty
        var fecha_reserva: LocalDateTime
) {
}