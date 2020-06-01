package com.santiagorodriguezalberto.bookazonapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santiagorodriguezalberto.bookazonapp.api.request.EditUserRequest
import com.santiagorodriguezalberto.bookazonapp.api.request.LoginRequest
import com.santiagorodriguezalberto.bookazonapp.api.request.RegisterRequest
import com.santiagorodriguezalberto.bookazonapp.api.response.LoginResponse
import com.santiagorodriguezalberto.bookazonapp.common.Resource
import com.santiagorodriguezalberto.bookazonapp.model.Usuario
import com.santiagorodriguezalberto.bookazonapp.repository.BookazonRepository
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

class UserViewModel @Inject constructor(bookazonRepository: BookazonRepository): ViewModel() {
    val repository = bookazonRepository

    var usuariologeado: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    var usuarioregistrado: MutableLiveData<Resource<Usuario>> = MutableLiveData()
    var usuario: MutableLiveData<Resource<Usuario>> = MutableLiveData()

    fun doLogin(request: LoginRequest) = viewModelScope.launch {
        usuariologeado.value = Resource.Loading()
        val response = repository.doLogin(request)

        usuariologeado.value = handleLoginResponse(response)

    }

    fun doSignUp(request: RegisterRequest) = viewModelScope.launch {
        usuarioregistrado.value = Resource.Loading()
        val response = repository.doSignUp(request)

        usuarioregistrado.value = handleUserSignedUp(response)
    }

    fun getUsuario() = viewModelScope.launch {
        usuario.value = Resource.Loading()
        val response = repository.getUsuario()

        usuario.value = handleUserSignedUp(response)
    }

    fun editUsuario(request: EditUserRequest) = viewModelScope.launch {
        usuario.value = Resource.Loading()
        val response = repository.editUsuario(request)

        usuario.value = handleUserSignedUp(response)
    }

    private fun handleLoginResponse(response: Response<LoginResponse>): Resource<LoginResponse>? {
        if(response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleUserSignedUp(response: Response<Usuario>): Resource<Usuario>? {
        if(response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }
}