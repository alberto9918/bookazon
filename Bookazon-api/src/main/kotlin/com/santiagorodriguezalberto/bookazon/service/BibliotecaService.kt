package com.santiagorodriguezalberto.bookazon.service

import com.santiagorodriguezalberto.bookazon.entity.Biblioteca
import com.santiagorodriguezalberto.bookazon.repository.BibliotecaRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class BibliotecaService(
        private val repo: BibliotecaRepository
) {
    fun findAll() = repo.findAll()

    fun findById(id : UUID) = repo.findById(id)

    fun crearBiblioteca(biblioteca: Biblioteca) = repo.save(biblioteca)

    fun editarBiblioteca(biblioteca: Biblioteca) = repo.save(biblioteca)

    fun eliminarBiblioteca(id: UUID) = repo.deleteById(id)
}