package com.santiagorodriguezalberto.bookazon.controller

import com.santiagorodriguezalberto.bookazon.dtos.*
import com.santiagorodriguezalberto.bookazon.entity.Reserva
import com.santiagorodriguezalberto.bookazon.entity.Usuario
import com.santiagorodriguezalberto.bookazon.service.CopiaService
import com.santiagorodriguezalberto.bookazon.service.ReservaService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.util.*

@RestController
@RequestMapping("/reservas")
class ReservaController(
        val reservaService: ReservaService,
        val copiaService: CopiaService
) {
    @PostMapping("/{idCopia}")
    fun crearReserva(@PathVariable idCopia: UUID, @AuthenticationPrincipal user :Usuario): ResponseEntity<*> {

        val copia = copiaService.findById(idCopia).get()

        val nuevaReserva = NuevaReservaDTO(copia)

        val result = reservaService.crearReserva(nuevaReserva.toReserva(user)).toReservaDTO()

        if(result != null){
            copia.esta_reservada = true
            copiaService.editarCopia(copia)

            return ResponseEntity<ReservaDTO>(result, HttpStatus.CREATED)
        }
        else throw ResponseStatusException(HttpStatus.BAD_REQUEST, "No se ha creado la reserva para la copia $idCopia")
    }

    @GetMapping("/reserva/{idCopia}")
    fun getReservaByCopia(@PathVariable idCopia: UUID): ReservaDTO {

        val copia = copiaService.findById(idCopia).get()

        var result = reservaService.findByCopia(copia)

        if (result.isPresent) return result.get().toReservaDTO()
        else throw ResponseStatusException(HttpStatus.NOT_FOUND, "No se ha encontrado una reserva para la copia $idCopia")
    }

    @GetMapping("/")
    fun getReservasByUser(@AuthenticationPrincipal user: Usuario): List<ReservaDTO> {
        val result: List<Reserva> = reservaService.findAllByUsuario(user)

        if (result.isNotEmpty()) return result.map {
            it.toReservaDTO()
        }
        else throw ResponseStatusException(HttpStatus.NO_CONTENT, "No hay reservas para este usuario")
    }

    @DeleteMapping("/{id}")
    fun deleteReserva(@PathVariable id: UUID): ResponseEntity<Void> {
        var result = reservaService.findById(id)

        if (result.isPresent) {

            result.get().copia.esta_reservada = false
            copiaService.editarCopia(result.get().copia)

            reservaService.eliminarReserva(id)
            return ResponseEntity.noContent().build()
        }
        else throw ResponseStatusException(HttpStatus.NOT_FOUND, "No se ha encontrado la reserva con el identificador $id")
    }
}