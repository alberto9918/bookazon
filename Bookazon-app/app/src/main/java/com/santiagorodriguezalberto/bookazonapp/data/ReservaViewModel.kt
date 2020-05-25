package com.santiagorodriguezalberto.bookazonapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.santiagorodriguezalberto.bookazonapp.model.Copia
import com.santiagorodriguezalberto.bookazonapp.model.Reserva
import com.santiagorodriguezalberto.bookazonapp.repository.BookazonRepository
import javax.inject.Inject

class ReservaViewModel @Inject constructor(bookazonRepository: BookazonRepository): ViewModel() {
    val repository = bookazonRepository

    fun getReservasByUser(): LiveData<List<Reserva>> {
        return repository.getReservasByUser()
    }

    fun getReservaByCopia(id:String): LiveData<Reserva> {
        return repository.getReservaByCopia(id)
    }

    fun doReserva(id:String): LiveData<Reserva> {
        return repository.newReserva(id)
    }
}