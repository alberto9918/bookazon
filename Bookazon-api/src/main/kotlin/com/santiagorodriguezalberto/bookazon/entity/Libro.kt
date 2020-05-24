package com.santiagorodriguezalberto.bookazon.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "Libro")
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
open class Libro(
        open var titulo: String,

        open var autor: String,

        open var resumen: String,

        open var genero: String,

        open var isbn: String,

        open var editorial: String,

        open var imagen: String? = null,

        @Id @GeneratedValue
        open val id: UUID? = null
) {

}