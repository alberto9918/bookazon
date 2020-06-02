package com.santiagorodriguezalberto.bookazon.controller

import com.santiagorodriguezalberto.bookazon.dtos.*
import com.santiagorodriguezalberto.bookazon.entity.Reserva
import com.santiagorodriguezalberto.bookazon.entity.Usuario
import com.santiagorodriguezalberto.bookazon.service.CopiaService
import com.santiagorodriguezalberto.bookazon.service.ReservaService
import io.swagger.annotations.ApiOperation
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
    @ApiOperation(value = "Crea una reserva, pasando como parámetros el id de la copia que se quiere añadir a la reserva," +
            "y el usuario que está logueado en ese momento")
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
    @ApiOperation(value = "Devuelve una reserva que contenga la copia cuyo id coincide con el que se ha pasado por parámetro")
    fun getReservaByCopia(@PathVariable idCopia: UUID): ReservaDTO {

        val copia = copiaService.findById(idCopia).get()

        var result = reservaService.findByCopia(copia)

        if (result.isPresent) return result.get().toReservaDTO()
        else throw ResponseStatusException(HttpStatus.NOT_FOUND, "No se ha encontrado una reserva para la copia $idCopia")
    }

    @GetMapping("/")
    @ApiOperation(value = "Devuelve todas las reservas del usuario que está logueado")
    fun getReservasByUser(@AuthenticationPrincipal user: Usuario): List<ReservaDTO> {
        val result: List<Reserva> = reservaService.findAllByUsuario(user)

        if (result.isNotEmpty()) return result.map {
            it.toReservaDTO()
        }
        else throw ResponseStatusException(HttpStatus.NO_CONTENT, "No hay reservas para este usuario")
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Elimina la reserva cuyo id coincide con el que se ha pasado como parámetro")
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