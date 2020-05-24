package com.santiagorodriguezalberto.bookazonapp.repository

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.santiagorodriguezalberto.bookazonapp.api.BookazonService
import com.santiagorodriguezalberto.bookazonapp.api.request.LoginRequest
import com.santiagorodriguezalberto.bookazonapp.api.request.RegisterRequest
import com.santiagorodriguezalberto.bookazonapp.api.response.LoginResponse
import com.santiagorodriguezalberto.bookazonapp.common.Constantes
import com.santiagorodriguezalberto.bookazonapp.common.MyApp
import com.santiagorodriguezalberto.bookazonapp.common.SharedPreferencesManager
import com.santiagorodriguezalberto.bookazonapp.model.Biblioteca
import com.santiagorodriguezalberto.bookazonapp.model.Copia
import com.santiagorodriguezalberto.bookazonapp.model.Usuario
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookazonRepository  @Inject constructor(var bookazonService: BookazonService) {
    var user: MutableLiveData<Usuario> = MutableLiveData()
    var newUser: MutableLiveData<Usuario> = MutableLiveData()

    var bibliotecas: MutableLiveData<List<Biblioteca>> = MutableLiveData()
    var biblioteca: MutableLiveData<Biblioteca> = MutableLiveData()

    var copias: MutableLiveData<List<Copia>> = MutableLiveData()
    var copia: MutableLiveData<Copia> = MutableLiveData()

    fun doLogin(request: LoginRequest): MutableLiveData<Usuario> {
        val call: Call<LoginResponse>? = bookazonService.doLogin(request)

        call?.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>
            ) {
                if (response.isSuccessful) {
                    user.value = response.body()?.user!!
                    SharedPreferencesManager().setStringValue(
                        Constantes.SHARED_PREFERENCES_TOKEN_KEYWORD,
                        response.body()!!.token
                    )
                    Log.d(
                        "TOKEN",
                        SharedPreferencesManager().getSharedPreferences().getString(
                            Constantes.SHARED_PREFERENCES_TOKEN_KEYWORD,
                            "null"
                        )
                    )
                }else{
                    user.postValue(null)
                    SharedPreferencesManager().removeStringValue(Constantes.SHARED_PREFERENCES_TOKEN_KEYWORD)
                    Toast.makeText(MyApp.instance, "El usuario o contraseña no es correcto", Toast.LENGTH_SHORT).show()

                }
            }

            override fun onFailure(
                call: Call<LoginResponse>,
                t: Throwable
            ) {
                Toast.makeText(MyApp.instance, t.message, Toast.LENGTH_SHORT).show()
            }
        })
        return user
    }

    fun doSignUp(registerRequest: RegisterRequest): MutableLiveData<Usuario>{
        val call: Call<Usuario> = bookazonService.doSignup(registerRequest)

        call?.enqueue(object : Callback<Usuario> {

            override fun onResponse(
                call: Call<Usuario>,
                response: Response<Usuario>
            ) {
                if (!response.isSuccessful) {
                    // error
                    Log.e("RequestError", response.message())
                    Toast.makeText(MyApp.instance, "Error while trying to login", Toast.LENGTH_SHORT).show()
                } else {
                    // exito
                    newUser.value = response.body()
                }
            }
            override fun onFailure(
                call: Call<Usuario>,
                t: Throwable
            ) {
                Log.e("NetworkFailure", t.message)
                Toast.makeText(MyApp.instance, "Error. Can't connect to server", Toast.LENGTH_SHORT).show()
            }

        })

        return  newUser
    }

    fun getAllBibliotecas(): MutableLiveData<List<Biblioteca>> {
        val call: Call<List<Biblioteca>>? = bookazonService.getBibliotecas()

        call?.enqueue(object : Callback<List<Biblioteca>> {
            override fun onResponse(call: Call<List<Biblioteca>>, response: Response<List<Biblioteca>>) {
                if (response.isSuccessful) bibliotecas.value = response.body()
                Constantes.LISTA_BIBLIOTECAS = response.body()
            }

            override fun onFailure(
                call: Call<List<Biblioteca>>,
                t: Throwable
            ) {
                Log.e("NetworkFailure", t.message)
                Toast.makeText(MyApp.instance, "Error. Can't connect to server", Toast.LENGTH_SHORT).show()
            }
        })
        return bibliotecas
    }

    fun getBibliotecaByName(nombre: String): MutableLiveData<Biblioteca> {
        val call: Call<Biblioteca> = bookazonService.getBibliotecaByName(nombre)

        call?.enqueue(object : Callback<Biblioteca> {

            override fun onResponse(
                call: Call<Biblioteca>,
                response: Response<Biblioteca>
            ) {
                if (!response.isSuccessful) {
                    // error
                    Log.e("RequestError", response.message())
                    Toast.makeText(MyApp.instance, "No se ha encontrado ninguna biblioteca con ese nombre", Toast.LENGTH_SHORT).show()
                } else {
                    // exito
                    biblioteca.value = response.body()

                }
            }

            override fun onFailure(
                call: Call<Biblioteca>,
                t: Throwable
            ) {
                Log.e("NetworkFailure", t.message)
                Toast.makeText(MyApp.instance, "Error. Can't connect to server", Toast.LENGTH_SHORT).show()
            }

        })

        return biblioteca
    }

    fun getCopiasByBibliotecaName(biblioteca_name: String): MutableLiveData<List<Copia>> {
        val call: Call<List<Copia>>? = bookazonService.getCopiasByBiblioteca(biblioteca_name)

        call?.enqueue(object : Callback<List<Copia>> {
            override fun onResponse(call: Call<List<Copia>>, response: Response<List<Copia>>) {
                if (response.isSuccessful)
                    copias.value = response.body()
            }

            override fun onFailure(
                call: Call<List<Copia>>,
                t: Throwable
            ) {
                Log.e("NetworkFailure", t.message)
                Toast.makeText(MyApp.instance, "ERROR AL LISTAR COPIAS", Toast.LENGTH_SHORT).show()
            }
        })
        return copias
    }

    fun getCopia(id: String): MutableLiveData<Copia> {
        val call: Call<Copia>? = bookazonService.getCopia(id)

        call?.enqueue(object : Callback<Copia> {
            override fun onResponse(call: Call<Copia>, response: Response<Copia>) {
                if (response.isSuccessful) copia.value = response.body()
            }

            override fun onFailure(call: Call<Copia>, t: Throwable) {
                Toast.makeText(MyApp.instance, t.message, Toast.LENGTH_SHORT).show()
            }
        })
        return copia
    }

}