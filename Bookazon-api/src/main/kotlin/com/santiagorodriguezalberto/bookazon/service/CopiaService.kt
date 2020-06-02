package com.santiagorodriguezalberto.bookazon.service

import com.santiagorodriguezalberto.bookazon.entity.Biblioteca
import com.santiagorodriguezalberto.bookazon.entity.Copia
import com.santiagorodriguezalberto.bookazon.repository.CopiaRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class CopiaService(
        private val repo: CopiaRepository
) {
    fun findAllByBiblioteca(biblioteca_name: String) = repo.findAllByBiblioteca(biblioteca_name)

    fun findAll() = repo.findAll()

    fun findById(id : UUID) = repo.findById(id)

    fun findGeneros(biblioteca_name: String) = repo.findGeneros(biblioteca_name)

    fun findCopiasByGenero(biblioteca_name: String, genero: String) = repo.findCopiasByGenero(biblioteca_name, genero)

    fun findAutores(biblioteca_name: String) = repo.findAutores(biblioteca_name)

    fun findCopiasByAutor(biblioteca_name: String, autor: String) = repo.findCopiasByAutor(biblioteca_name, autor)

    fun crearCopia(copia: Copia) = repo.save(copia)

    fun editarCopia(copia: Copia) = repo.save(copia)

    fun eliminarCopia(id: UUID) = repo.deleteById(id)
}