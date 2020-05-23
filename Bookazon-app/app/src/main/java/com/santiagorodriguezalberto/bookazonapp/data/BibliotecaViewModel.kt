package com.santiagorodriguezalberto.bookazonapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.santiagorodriguezalberto.bookazonapp.model.Biblioteca
import com.santiagorodriguezalberto.bookazonapp.repository.BookazonRepository
import javax.inject.Inject

class BibliotecaViewModel @Inject constructor(bookazonRepository: BookazonRepository): ViewModel() {
    val repository = bookazonRepository

    fun getAll(): LiveData<List<Biblioteca>> {
        return repository.getAllBibliotecas()
    }

    fun getBibliotecaByName(nombre: String): LiveData<Biblioteca> {
        return repository.getBibliotecaByName(nombre)
    }
}