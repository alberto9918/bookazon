package com.santiagorodriguezalberto.bookazon.controller

import com.santiagorodriguezalberto.bookazon.entity.Biblioteca
import com.santiagorodriguezalberto.bookazon.service.BibliotecaService
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

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

    @GetMapping("/")
    fun getBibliotecas() = allBibliotecas().map {
        it
    }

}