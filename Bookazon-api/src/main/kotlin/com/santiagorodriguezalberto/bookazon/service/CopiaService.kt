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

    fun crearCopia(copia: Copia) = repo.save(copia)

    fun editarCopia(copia: Copia) = repo.save(copia)

    fun eliminarCopia(id: UUID) = repo.deleteById(id)
}