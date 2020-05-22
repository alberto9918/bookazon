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
    fun findAllByBiblioteca(biblioteca: Biblioteca) = repo.findAllByBiblioteca(biblioteca)

    fun findAll() = repo.findAll()

    fun findById(id : UUID) = repo.findById(id)

    fun crearBiblioteca(copia: Copia) = repo.save(copia)

    fun editarBiblioteca(copia: Copia) = repo.save(copia)

    fun eliminarBiblioteca(id: UUID) = repo.deleteById(id)
}