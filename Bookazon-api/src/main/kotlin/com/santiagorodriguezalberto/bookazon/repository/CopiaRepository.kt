package com.santiagorodriguezalberto.bookazon.repository

import com.santiagorodriguezalberto.bookazon.entity.Biblioteca
import com.santiagorodriguezalberto.bookazon.entity.Copia
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CopiaRepository: JpaRepository<Copia,UUID> {

    fun findAllByBiblioteca(biblioteca: Biblioteca): List<Copia>
}