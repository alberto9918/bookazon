package com.santiagorodriguezalberto.bookazonapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santiagorodriguezalberto.bookazonapp.common.Resource
import com.santiagorodriguezalberto.bookazonapp.model.Copia
import com.santiagorodriguezalberto.bookazonapp.model.Reserva
import com.santiagorodriguezalberto.bookazonapp.repository.BookazonRepository
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

class ReservaViewModel @Inject constructor(bookazonRepository: BookazonRepository): ViewModel() {
    val repository = bookazonRepository

    var reservas: MutableLiveData<Resource<List<Reserva>>> = MutableLiveData()
    var reserva: MutableLiveData<Resource<Reserva>> = MutableLiveData()
    var deleteReserva: MutableLiveData<Resource<Void>> = MutableLiveData()

    fun getReservasByUser() = viewModelScope.launch {
        reservas.value = Resource.Loading()
        val response = repository.getReservasByUser()

        reservas.value = handleListReservas(response)
    }

    fun getReservaByCopia(id:String) = viewModelScope.launch {
        reserva.value = Resource.Loading()
        val response = repository.getReservaByCopia(id)

        reserva.value = handleReserva(response)
    }

    fun doReserva(id:String)= viewModelScope.launch {
        reserva.value = Resource.Loading()
        val response = repository.newReserva(id)

        reserva.value = handleReserva(response)
    }

    fun deleteReserva(id:String)= viewModelScope.launch {
        deleteReserva.value = Resource.Loading()
        val response = repository.deleteReserva(id)

        deleteReserva.value = handleDeleteReserva(response)
    }



    private fun handleListReservas(response: Response<List<Reserva>>): Resource<List<Reserva>>? {
        if(response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleReserva(response: Response<Reserva>): Resource<Reserva>? {
        if(response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleDeleteReserva(response: Response<Void>): Resource<Void>? {
        if(response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }

        }
        return Resource.Error(response.message())
    }
}