package com.santiagorodriguezalberto.bookazonapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import butterknife.BindView
import butterknife.ButterKnife
import com.santiagorodriguezalberto.bookazonapp.R
import com.santiagorodriguezalberto.bookazonapp.api.request.RegisterRequest
import com.santiagorodriguezalberto.bookazonapp.common.MyApp
import com.santiagorodriguezalberto.bookazonapp.common.Resource
import com.santiagorodriguezalberto.bookazonapp.data.UserViewModel
import java.util.regex.Pattern
import javax.inject.Inject

class RegisterActivity : AppCompatActivity() {

    @Inject
    lateinit var userViewModel: UserViewModel

    @BindView(R.id.rg_nombre)
    lateinit var nombre: EditText

    @BindView(R.id.rg_apellidos)
    lateinit var apellidos: EditText

    @BindView(R.id.rg_email)
    lateinit var email: EditText

    @BindView(R.id.rg_password)
    lateinit var password: EditText

    @BindView(R.id.rg_password2)
    lateinit var password2: EditText

    @BindView(R.id.rg_dni)
    lateinit var dni: EditText

    @BindView(R.id.rg_phone)
    lateinit var telefono: EditText

    @BindView(R.id.btn_signUp)
    lateinit var btn_signUp: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        (applicationContext as MyApp).getAppCompontent().inject(this)

        val EMAIL_REGEX = Pattern.compile(
            "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
            Pattern.CASE_INSENSITIVE
        )

        ButterKnife.bind(this)

        btn_signUp.setOnClickListener(View.OnClickListener {
            if (email.text.isEmpty() || password.text.isEmpty()) {
                Toast.makeText(MyApp.instance, "¡Los campos no pueden estar vacíos!", Toast.LENGTH_SHORT).show()
            } else if (!EMAIL_REGEX.matcher(email.text).matches()) {
                Toast.makeText(MyApp.instance, "¡Introduce un email válido!", Toast.LENGTH_LONG).show()
            } else if (password.text.length < 8) {
                Toast.makeText(MyApp.instance, "!La contraseña debe tener al menos 8 caracteres!", Toast.LENGTH_LONG)
                    .show()
            } else if (password.text.toString() != password2.text.toString()) {
                Toast.makeText(MyApp.instance, "Las contraseñas no coinciden!", Toast.LENGTH_SHORT).show()
            } else{
                userViewModel.doSignUp(RegisterRequest(
                    nombre = nombre.text.toString(),
                    apellidos = apellidos.text.toString(),
                    email = email.text.toString(),
                    password = password.text.toString(),
                    password2 = password2.text.toString(),
                    dni = dni.text.toString(),
                    telefono = Integer.parseInt(telefono.text.toString())
                ))

                userViewModel.usuarioregistrado.observe(this, Observer {response ->

                    when(response) {
                        is Resource.Success ->  {
                            val login: Intent = Intent(MyApp.instance, MainActivity::class.java).apply {
                                flags = Intent.FLAG_ACTIVITY_NEW_TASK
                            }
                            startActivity(login)
                            finish()
                        }

                        is Resource.Loading -> {
                            //CARGANDO
                        }

                        is Resource.Error -> {
                            Toast.makeText(MyApp.instance,"Error, ese email ya está registrado", Toast.LENGTH_LONG).show()
                        }
                    }
                })
            }
        })
    }
}
