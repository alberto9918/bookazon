package com.santiagorodriguezalberto.bookazonapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santiagorodriguezalberto.bookazonapp.api.response.LoginResponse
import com.santiagorodriguezalberto.bookazonapp.common.Resource
import com.santiagorodriguezalberto.bookazonapp.model.Biblioteca
import com.santiagorodriguezalberto.bookazonapp.repository.BookazonRepository
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

class BibliotecaViewModel @Inject constructor(bookazonRepository: BookazonRepository): ViewModel() {
    val repository = bookazonRepository

    var bibliotecas: MutableLiveData<Resource<List<Biblioteca>>> = MutableLiveData()
    var biblioteca: MutableLiveData<Resource<Biblioteca>> = MutableLiveData()

    fun getAll() = viewModelScope.launch {
        bibliotecas.value = Resource.Loading()
        val response = repository.getAllBibliotecas()

        bibliotecas.value = handleListBibliotecas(response)
    }

    fun getBibliotecaByName(nombre: String) = viewModelScope.launch {
        biblioteca.value = Resource.Loading()
        val response = repository.getBibliotecaByName(nombre)

        biblioteca.value = handleBiblioteca(response)
    }

    private fun handleListBibliotecas(response: Response<List<Biblioteca>>): Resource<List<Biblioteca>>? {
        if(response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleBiblioteca(response: Response<Biblioteca>): Resource<Biblioteca>? {
        if(response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }
}