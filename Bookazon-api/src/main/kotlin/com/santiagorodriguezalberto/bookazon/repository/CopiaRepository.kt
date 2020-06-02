package com.santiagorodriguezalberto.bookazon.repository

import com.santiagorodriguezalberto.bookazon.entity.Biblioteca
import com.santiagorodriguezalberto.bookazon.entity.Copia
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface CopiaRepository: JpaRepository<Copia,UUID> {

    @Query("select c from Copia c where c.biblioteca.nombre = :biblioteca_name")
    fun findAllByBiblioteca(biblioteca_name: String): List<Copia>

    @Query("select distinct genero from Copia c where c.biblioteca.nombre = :biblioteca_name")
    fun findGeneros(biblioteca_name: String): List<String>

    @Query("select c from Copia c where c.biblioteca.nombre = :biblioteca_name and c.genero = :genero")
    fun findCopiasByGenero(biblioteca_name: String, genero: String): List<Copia>

    @Query("select distinct autor from Copia c where c.biblioteca.nombre = :biblioteca_name")
    fun findAutores(biblioteca_name: String): List<String>

    @Query("select c from Copia c where c.biblioteca.nombre = :biblioteca_name and c.autor = :autor")
    fun findCopiasByAutor(biblioteca_name: String, autor: String): List<Copia>
}