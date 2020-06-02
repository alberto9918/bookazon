package com.santiagorodriguezalberto.bookazon.controller

import com.santiagorodriguezalberto.bookazon.dtos.BibliotecaDTO
import com.santiagorodriguezalberto.bookazon.dtos.toBibliotecaDTO
import com.santiagorodriguezalberto.bookazon.entity.Biblioteca
import com.santiagorodriguezalberto.bookazon.service.BibliotecaService
import io.swagger.annotations.ApiOperation
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import java.util.*

@RestController
@RequestMapping("/bibliotecas")
class BibliotecaController(
    val bibliotecaService: BibliotecaService
) {
    private fun allBibliotecas() : List<Biblioteca> {
        var result: List<Biblioteca>
        with(bibliotecaService) {
            result = findAll()

        }
        if (result.isEmpty())
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "No hay ninguna biblioteca almacenada")
        return result
    }

    private fun oneBiblioteca(nombre: String) : BibliotecaDTO {
        var result: Optional<Biblioteca>
        with(bibliotecaService) {
            result =  findByNombre(nombre)

        }
        if(result.isPresent) return result.get().toBibliotecaDTO()
        else throw  ResponseStatusException(HttpStatus.NOT_FOUND, "No se ha encontrado la biblioteca con el nombre $nombre")
    }

    @GetMapping("/")
    @ApiOperation(value = "Devuelve una lista con todas las bibliotecas que hay")
    fun getBibliotecas() = allBibliotecas().map {
        it
    }

    @GetMapping("/{nombre}")
    @ApiOperation(value = "Devuelve la biblioteca cuyo nombre sea el que se ha pasado como parámetro")
    fun getBiblioteca(@PathVariable nombre: String) = oneBiblioteca(nombre)

}