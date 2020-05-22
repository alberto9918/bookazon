package com.santiagorodriguezalberto.bookazon.repository

import com.santiagorodriguezalberto.bookazon.entity.Biblioteca
import com.santiagorodriguezalberto.bookazon.entity.Copia
import com.santiagorodriguezalberto.bookazon.entity.Reserva
import com.santiagorodriguezalberto.bookazon.entity.Usuario
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ReservaRepository: JpaRepository<Reserva,UUID> {

    fun findAllByUsuario(usuario: Usuario): List<Reserva>
}