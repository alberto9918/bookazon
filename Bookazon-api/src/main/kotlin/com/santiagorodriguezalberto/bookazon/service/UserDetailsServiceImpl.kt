package com.santiagorodriguezalberto.bookazon.service

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service("userDetailsService")
class UserDetailsServiceImpl(
        private val userService: UserService
): UserDetailsService {

    override fun loadUserByUsername(email: String): UserDetails =
            userService.findByEmail(email)!!.orElseThrow {
                UsernameNotFoundException("Usuario $email no encontrado")
            }
}