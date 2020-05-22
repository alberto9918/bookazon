package com.santiagorodriguezalberto.bookazon.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "Libro")
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
open class Libro(
        open var titulo: String? = null,

        open var autor: String? = null,

        open var isbn: String? = null,

        open var editorial: String? = null,

        open var imagen: String? = null,

        @Id @GeneratedValue
        open val id: UUID? = null
) {

}