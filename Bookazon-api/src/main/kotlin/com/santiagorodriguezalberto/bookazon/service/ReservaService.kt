package com.santiagorodriguezalberto.bookazon.service

import com.santiagorodriguezalberto.bookazon.entity.Biblioteca
import com.santiagorodriguezalberto.bookazon.entity.Copia
import com.santiagorodriguezalberto.bookazon.entity.Reserva
import com.santiagorodriguezalberto.bookazon.entity.Usuario
import com.santiagorodriguezalberto.bookazon.repository.ReservaRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class ReservaService(
        private val repo: ReservaRepository
) {
    fun findAllByUsuario(usuario: Usuario) = repo.findAllByUsuario(usuario)

    fun findAll() = repo.findAll()

    fun findById(id : UUID) = repo.findById(id)

    fun findByCopia(copia: Copia) = repo.findByCopia(copia)//CONTINUAR POR AQUI

    fun crearReserva(reserva: Reserva) = repo.save(reserva)

    fun editarReserva(reserva: Reserva) = repo.save(reserva)

    fun eliminarReserva(id: UUID) = repo.deleteById(id)
}