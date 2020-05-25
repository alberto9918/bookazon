package com.santiagorodriguezalberto.bookazon.controller

import com.santiagorodriguezalberto.bookazon.dtos.*
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

        if(result != null) return ResponseEntity<ReservaDTO>(result, HttpStatus.CREATED)
        else throw ResponseStatusException(
                HttpStatus.BAD_REQUEST, "No se ha creado la reserva para la copia $idCopia")
    }

    //CREAR METODO PARA BUSCAR UNA RESERVA POR ID DE COPIA
    @GetMapping("/{idCopia}")
    fun getCopia(@PathVariable idCopia: UUID): ReservaDTO {

        val copia = copiaService.findById(idCopia).get()

        var result = reservaService.findByCopia(copia)

        if (result.isPresent) return result.get().toReservaDTO()
        else throw ResponseStatusException(HttpStatus.NOT_FOUND, "No se ha encontrado una reserva para la copia $idCopia")
    }
}