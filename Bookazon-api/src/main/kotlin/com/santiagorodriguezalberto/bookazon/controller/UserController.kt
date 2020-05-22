package com.santiagorodriguezalberto.bookazon.controller

import com.santiagorodriguezalberto.bookazon.dtos.CreateUserDTO
import com.santiagorodriguezalberto.bookazon.dtos.UserDTO
import com.santiagorodriguezalberto.bookazon.dtos.toUserDTO
import com.santiagorodriguezalberto.bookazon.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.server.ResponseStatusException

@Controller
@RequestMapping("/user")
class UserController(
        val userService: UserService
) {
    @PostMapping("/")
    fun nuevoUsuario(@RequestBody newUser : CreateUserDTO): ResponseEntity<UserDTO> =
            userService.create(newUser).map { ResponseEntity.status(HttpStatus.CREATED).body(it.toUserDTO()) }
                    .orElseThrow {
                ResponseStatusException(HttpStatus.BAD_REQUEST, "El email ${newUser.email} ya existe")
            }
}