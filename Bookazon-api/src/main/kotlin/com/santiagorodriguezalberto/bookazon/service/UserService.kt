package com.santiagorodriguezalberto.bookazon.service

import com.santiagorodriguezalberto.bookazon.dtos.CreateUserDTO
import com.santiagorodriguezalberto.bookazon.entity.Usuario
import com.santiagorodriguezalberto.bookazon.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(
        private val repo: UserRepository,
        private val encoder: PasswordEncoder
) {
    fun create(newUser : CreateUserDTO): Optional<Usuario> {
        if (findByEmail(newUser.email)!!.isPresent)
            return Optional.empty()
        return Optional.of(
                with(newUser) {
                    repo.save(Usuario(nombre,apellidos,email,encoder.encode(password),dni,telefono,mutableSetOf("USER")))
                }

        )
    }

    fun findByEmail(email : String) = repo.findByEmail(email)

    fun findById(id : UUID) = repo.findById(id)

    fun editarUsuario(usuario: Usuario) = repo.save(usuario)
}