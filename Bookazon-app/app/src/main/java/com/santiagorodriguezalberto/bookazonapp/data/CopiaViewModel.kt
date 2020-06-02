package com.santiagorodriguezalberto.bookazonapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santiagorodriguezalberto.bookazonapp.common.Resource
import com.santiagorodriguezalberto.bookazonapp.model.Biblioteca
import com.santiagorodriguezalberto.bookazonapp.model.Copia
import com.santiagorodriguezalberto.bookazonapp.repository.BookazonRepository
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

class CopiaViewModel @Inject constructor(bookazonRepository: BookazonRepository): ViewModel() {
    val repository = bookazonRepository

    var copias: MutableLiveData<Resource<List<Copia>>> = MutableLiveData()
    var copia: MutableLiveData<Resource<Copia>> = MutableLiveData()

    var generos: MutableLiveData<Resource<List<String>>> = MutableLiveData()
    var autores: MutableLiveData<Resource<List<String>>> = MutableLiveData()

    fun getCopiasByBibliotecaName(biblioteca_name: String) = viewModelScope.launch {
        copias.value = Resource.Loading()
        val response = repository.getCopiasByBibliotecaName(biblioteca_name)

        copias.value = handleListCopias(response)
    }

    fun getCopia(id:String) = viewModelScope.launch{
        copia.value = Resource.Loading()
        val response = repository.getCopia(id)

        copia.value = handleCopia(response)
    }

    fun getGeneros(biblioteca_name: String) = viewModelScope.launch {
        generos.value = Resource.Loading()
        val response = repository.getGeneros(biblioteca_name)

        generos.value = handleGeneros(response)
    }

    fun getAutores(biblioteca_name: String) = viewModelScope.launch {
        autores.value = Resource.Loading()
        val response = repository.getAutores(biblioteca_name)

        autores.value = handleAutores(response)
    }

    fun getCopiasByGenero(biblioteca_name: String, genero: String) = viewModelScope.launch {
        copias.value = Resource.Loading()
        val response = repository.getCopiasByGenero(biblioteca_name, genero)

        copias.value = handleListCopias(response)
    }

    fun getCopiasByAutor(biblioteca_name: String, autor: String) = viewModelScope.launch {
        copias.value = Resource.Loading()
        val response = repository.getCopiasByAutor(biblioteca_name, autor)

        copias.value = handleListCopias(response)
    }

    //METODOS HANDLE
    private fun handleListCopias(response: Response<List<Copia>>): Resource<List<Copia>>? {
        if(response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleCopia(response: Response<Copia>): Resource<Copia>? {
        if(response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleGeneros(response: Response<List<String>>): Resource<List<String>>? {
        if(response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleAutores(response: Response<List<String>>): Resource<List<String>>? {
        if(response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }
}