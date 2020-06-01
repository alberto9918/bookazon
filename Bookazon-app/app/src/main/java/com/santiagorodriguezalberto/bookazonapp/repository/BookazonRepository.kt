package com.santiagorodriguezalberto.bookazonapp.repository

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.santiagorodriguezalberto.bookazonapp.api.BookazonService
import com.santiagorodriguezalberto.bookazonapp.api.request.EditUserRequest
import com.santiagorodriguezalberto.bookazonapp.api.request.LoginRequest
import com.santiagorodriguezalberto.bookazonapp.api.request.RegisterRequest
import com.santiagorodriguezalberto.bookazonapp.api.response.LoginResponse
import com.santiagorodriguezalberto.bookazonapp.common.Constantes
import com.santiagorodriguezalberto.bookazonapp.common.MyApp
import com.santiagorodriguezalberto.bookazonapp.common.SharedPreferencesManager
import com.santiagorodriguezalberto.bookazonapp.model.Biblioteca
import com.santiagorodriguezalberto.bookazonapp.model.Copia
import com.santiagorodriguezalberto.bookazonapp.model.Reserva
import com.santiagorodriguezalberto.bookazonapp.model.Usuario
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookazonRepository  @Inject constructor(var bookazonService: BookazonService) {

    //REGISTRO, LOGIN Y DETALLE DE USER

    suspend fun doLogin(request: LoginRequest) = bookazonService.doLogin(request)

    suspend fun doSignUp(registerRequest: RegisterRequest) = bookazonService.doSignup(registerRequest)

    suspend fun getUsuario() = bookazonService.getUser()

    suspend fun editUsuario(editUserRequest: EditUserRequest) = bookazonService.editNote(editUserRequest)

    //METODOS DE BIBLIOTECA

    suspend fun getAllBibliotecas() = bookazonService.getBibliotecas()


    suspend fun getBibliotecaByName(nombre: String) = bookazonService.getBibliotecaByName(nombre)

    //METODOS DE COPIA

    suspend fun getCopiasByBibliotecaName(biblioteca_name: String) = bookazonService.getCopiasByBiblioteca(biblioteca_name)

    suspend fun getCopia(id: String) = bookazonService.getCopia(id)

    //METODOS DE RESERVA

    suspend fun getReservasByUser() = bookazonService.getReservasByUser()

    suspend fun getReservaByCopia(id: String) = bookazonService.getReservaByCopia(id)

    suspend fun newReserva(id: String) = bookazonService.doReserva(id)

    suspend fun deleteReserva(id: String) = bookazonService.deleteReserva(id)
}