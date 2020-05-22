package com.santiagorodriguezalberto.bookazon

import com.santiagorodriguezalberto.bookazon.entity.Biblioteca
import com.santiagorodriguezalberto.bookazon.entity.Usuario
import com.santiagorodriguezalberto.bookazon.repository.BibliotecaRepository
import com.santiagorodriguezalberto.bookazon.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class MyData(
        val userRepository: UserRepository,
        val bibliotecaRepository: BibliotecaRepository,
        private val encoder: PasswordEncoder
) {
    @PostConstruct
    fun initData(){
        val usuarios = listOf(
                Usuario("Alberto","Santiago Rodríguez","albertosantiagorodriguez@gmail.com",
                encoder.encode("12345678"),"12345678A",123456789, mutableSetOf("USER"))
        )

        val bibliotecas = listOf(
                Biblioteca("Biblioteca Los Carteros",37.411256,-5.971490),
                Biblioteca("Biblioteca Antonio Machado", 37.370383, -5.985854)
        )

        userRepository.saveAll(usuarios)
        bibliotecaRepository.saveAll(bibliotecas)
    }
}