package com.santiagorodriguezalberto.bookazonapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.santiagorodriguezalberto.bookazonapp.model.Biblioteca
import com.santiagorodriguezalberto.bookazonapp.model.Copia
import com.santiagorodriguezalberto.bookazonapp.repository.BookazonRepository
import javax.inject.Inject

class CopiaViewModel @Inject constructor(bookazonRepository: BookazonRepository): ViewModel() {
    val repository = bookazonRepository

    fun getCopiasByBibliotecaName(biblioteca_name: String): LiveData<List<Copia>> {
        return repository.getCopiasByBibliotecaName(biblioteca_name)
    }

    fun getCopia(id:String):LiveData<Copia>{
        return repository.getCopia(id)
    }
}