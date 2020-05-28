package com.santiagorodriguezalberto.bookazonapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.santiagorodriguezalberto.bookazonapp.api.request.LoginRequest
import com.santiagorodriguezalberto.bookazonapp.api.request.RegisterRequest
import com.santiagorodriguezalberto.bookazonapp.model.Usuario
import com.santiagorodriguezalberto.bookazonapp.repository.BookazonRepository
import javax.inject.Inject

class UserViewModel @Inject constructor(bookazonRepository: BookazonRepository): ViewModel() {
    val repository = bookazonRepository

    fun doLogin(request: LoginRequest): LiveData<Usuario> {
        return repository.doLogin(request)
    }

    fun doSignUp(request: RegisterRequest): LiveData<Usuario> {
        return repository.doSignUp(request)
    }

    fun getUsuario(): LiveData<Usuario> {
        return repository.getUsuario()
    }
}