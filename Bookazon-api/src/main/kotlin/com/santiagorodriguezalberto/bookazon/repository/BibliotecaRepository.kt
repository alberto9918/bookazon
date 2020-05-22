package com.santiagorodriguezalberto.bookazon.repository

import com.santiagorodriguezalberto.bookazon.entity.Biblioteca
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface BibliotecaRepository: JpaRepository<Biblioteca, UUID> {
}