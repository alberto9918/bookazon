package com.santiagorodriguezalberto.bookazon.repository

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.santiagorodriguezalberto.bookazon.entity.Usuario
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository: JpaRepository<Usuario,UUID> {
    fun findByEmail(email: String): Optional<Usuario>?

}