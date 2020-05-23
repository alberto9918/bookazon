package com.santiagorodriguezalberto.bookazon.repository

import com.santiagorodriguezalberto.bookazon.entity.Biblioteca
import com.santiagorodriguezalberto.bookazon.entity.Copia
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface BibliotecaRepository: JpaRepository<Biblioteca, UUID> {

    fun findByNombre(nombre: String): Optional<Biblioteca>
}