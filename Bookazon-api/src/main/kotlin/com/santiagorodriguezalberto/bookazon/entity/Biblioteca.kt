package com.santiagorodriguezalberto.bookazon.entity

import com.fasterxml.jackson.annotation.JsonManagedReference
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotEmpty
import kotlin.collections.ArrayList

@Entity
@Table(name = "Biblioteca")
data class Biblioteca(

        @NotEmpty
        var nombre: String,

        @NotEmpty
        var latitud: Double,

        @NotEmpty
        var longitud: Double,

        @JsonManagedReference
        @OneToMany(mappedBy = "biblioteca", fetch = FetchType.EAGER, cascade = [CascadeType.REMOVE])
        var copias: MutableList<Copia>? = ArrayList(),

        @Id
        @GeneratedValue
        val id: UUID? = null

) {
}