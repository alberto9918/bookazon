package com.santiagorodriguezalberto.bookazon.repository

import com.santiagorodriguezalberto.bookazon.entity.Biblioteca
import com.santiagorodriguezalberto.bookazon.entity.Copia
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface CopiaRepository: JpaRepository<Copia,UUID> {

    @Query("select c from Copia c where c.biblioteca.nombre = :biblioteca_name")
    fun findAllByBiblioteca(biblioteca_name: String): List<Copia>
}