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
import com.santiagorodriguezalberto.bookazonapp.model.Usuario
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookazonRepository  @Inject constructor(var damKeepService: BookazonService) {
    var user: MutableLiveData<Usuario> = MutableLiveData()
    var newUser: MutableLiveData<Usuario> = MutableLiveData()

    fun doLogin(request: LoginRequest): MutableLiveData<Usuario> {
        val call: Call<LoginResponse>? = damKeepService.doLogin(request)

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
        val call: Call<Usuario> = damKeepService.doSignup(registerRequest)

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

}