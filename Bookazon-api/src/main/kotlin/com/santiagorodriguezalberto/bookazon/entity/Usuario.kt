package com.santiagorodriguezalberto.bookazon.entity

import com.fasterxml.jackson.annotation.JsonManagedReference
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import kotlin.collections.ArrayList

@Entity
@Table(name = "Usuario")
data class Usuario(

        @NotEmpty
        var nombre: String,

        @NotEmpty
        var apellidos: String,

        @NotEmpty
        var email: String,

        @NotEmpty
        @NotNull
        private var password: String,

        @NotEmpty
        var dni: String,

        @NotEmpty
        var telefono: Int,

        @ElementCollection(fetch = FetchType.EAGER)
        val roles: MutableSet<String> = HashSet(),

        @NotEmpty
        var profileImage: String? = null,

        @JsonManagedReference
        @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER, cascade = [CascadeType.REMOVE])
        var reservas: MutableList<Reserva>? = ArrayList(),

        private val nonExpired: Boolean = true,
        private val nonLocked: Boolean = true,
        private val enabled: Boolean = true,
        private val credentialsNonExpired: Boolean = true,


        @Id @GeneratedValue
        val id: UUID? = null

): UserDetails {

        constructor(nombre: String, apellidos: String, email: String, password: String, dni: String, telefono: Int, role: String, profileImage: String?, reservas: MutableList<Reserva>?) :
                this(nombre, apellidos, email, password, dni, telefono, mutableSetOf(role), profileImage, reservas, true, true, true, true)

        override fun getAuthorities(): MutableCollection<out GrantedAuthority> =
                roles.map { SimpleGrantedAuthority("ROLE_$it") }.toMutableList()

        override fun isEnabled() = enabled
        override fun getUsername() = email
        override fun isCredentialsNonExpired() = credentialsNonExpired
        override fun getPassword() = password
        override fun isAccountNonExpired() = nonExpired
        override fun isAccountNonLocked() = nonLocked

        override fun equals(other: Any?): Boolean {
                if (this === other)
                        return true
                if (other === null || other !is Usuario)
                        return false
                if (this::class != other::class)
                        return false
                return id == other.id
        }

        override fun hashCode(): Int {
                if (id == null)
                        return super.hashCode()
                return id.hashCode()
        }
}