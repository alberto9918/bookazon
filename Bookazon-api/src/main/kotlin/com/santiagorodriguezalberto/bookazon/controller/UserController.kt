package com.santiagorodriguezalberto.bookazon.controller

import com.santiagorodriguezalberto.bookazon.dtos.CreateUserDTO
import com.santiagorodriguezalberto.bookazon.dtos.EditUserDTO
import com.santiagorodriguezalberto.bookazon.dtos.UserDTO
import com.santiagorodriguezalberto.bookazon.dtos.toUserDTO
import com.santiagorodriguezalberto.bookazon.entity.Usuario
import com.santiagorodriguezalberto.bookazon.service.UserService
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Controller
@RequestMapping("/user")
class UserController(
        val userService: UserService
) {
    @PostMapping("/")
    @ApiOperation(value = "Registra(crea) un nuevo usuario")
    fun nuevoUsuario(@RequestBody newUser : CreateUserDTO): ResponseEntity<UserDTO> =
            userService.create(newUser).map { ResponseEntity.status(HttpStatus.CREATED).body(it.toUserDTO()) }
                    .orElseThrow {
                ResponseStatusException(HttpStatus.BAD_REQUEST, "El email ${newUser.email} ya existe")
            }

    @PutMapping("/edit")
    @ApiOperation(value = "Edita los datos del usuario que está logueado en ese momento")
    fun editNote(@AuthenticationPrincipal user: Usuario, @RequestBody usuarioEdit: EditUserDTO): ResponseEntity<UserDTO> {

        val edited: Usuario =
                user.copy(
                        nombre = usuarioEdit.nombre,
                        apellidos = usuarioEdit.apellidos,
                        email = usuarioEdit.email,
                        dni = usuarioEdit.dni,
                        telefono = usuarioEdit.telefono
                )

        println(edited)

        val result = userService.editarUsuario(edited).toUserDTO()

        return ResponseEntity.status(HttpStatus.OK).body(result)

    }
}