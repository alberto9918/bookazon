package com.santiagorodriguezalberto.bookazonapp.ui.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import butterknife.BindView
import butterknife.ButterKnife
import coil.api.load
import com.santiagorodriguezalberto.bookazonapp.R
import com.santiagorodriguezalberto.bookazonapp.api.request.EditUserRequest
import com.santiagorodriguezalberto.bookazonapp.common.MyApp
import com.santiagorodriguezalberto.bookazonapp.common.Resource
import com.santiagorodriguezalberto.bookazonapp.data.UserViewModel
import com.santiagorodriguezalberto.bookazonapp.ui.DashboardActivity
import java.util.regex.Pattern
import javax.inject.Inject

class EditUserActivity : AppCompatActivity() {
    @Inject
    lateinit var userViewModel: UserViewModel

    @BindView(R.id.et_editName)
    lateinit var nombre: EditText

    @BindView(R.id.et_editApellidos)
    lateinit var apellidos: EditText

    @BindView(R.id.et_editEmail)
    lateinit var correo: EditText

    @BindView(R.id.et_editDNI)
    lateinit var dni: EditText

    @BindView(R.id.et_editPhone)
    lateinit var telefono: EditText

    @BindView(R.id.btn_editar)
    lateinit var btn_editProfile: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_user)
        (this.applicationContext as MyApp).getAppCompontent().inject(this)

        ButterKnife.bind(this)

        val EMAIL_REGEX = Pattern.compile(
            "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
            Pattern.CASE_INSENSITIVE
        )

        userViewModel.getUsuario()
        userViewModel.usuario.observe(this, Observer {response ->
            when(response) {
                is Resource.Success ->  {
                    nombre.setText(response.data!!.nombre)
                    apellidos.setText(response.data!!.apellidos)
                    correo.setText(response.data!!.email)
                    dni.setText(response.data!!.dni)
                    telefono.setText(response.data!!.telefono.toString())
                }

                is Resource.Loading -> {
                    //CARGANDO
                }

                is Resource.Error -> {
                    Toast.makeText(MyApp.instance,"Error, ${response.message}", Toast.LENGTH_LONG).show()
                }
            }

        })

        btn_editProfile.setOnClickListener{
            if (nombre.text.isEmpty()||apellidos.text.isEmpty()||correo.text.isEmpty()||dni.text.isEmpty()||telefono.text.toString().isEmpty()) {
                Toast.makeText(MyApp.instance, "¡Los campos no pueden estar vacíos!", Toast.LENGTH_SHORT).show()
            } else if (!EMAIL_REGEX.matcher(correo.text).matches()) {
                Toast.makeText(MyApp.instance, "¡Introduce un email válido!", Toast.LENGTH_LONG).show()
            }else{

                userViewModel.editUsuario(EditUserRequest(
                    nombre.text.toString(),
                    apellidos.text.toString(),
                    correo.text.toString(),
                    dni.text.toString(),
                    telefono.text.toString().toInt()
                ))

                userViewModel.usuario.observe(this, Observer { respuesta ->
                    when(respuesta) {
                        is Resource.Success ->  {
                            var i = Intent(MyApp.instance, DashboardActivity::class.java).apply {
                                flags = Intent.FLAG_ACTIVITY_NEW_TASK
                            }
                            MyApp.instance.startActivity(i)
                            finish()

                            Toast.makeText(MyApp.instance,"Los datos del usuario han sido editados correctamente", Toast.LENGTH_SHORT).show()
                        }

                        is Resource.Loading -> {
                            //CARGANDO
                        }

                        is Resource.Error -> {
                            Toast.makeText(MyApp.instance,"Error, ${respuesta.message}", Toast.LENGTH_LONG).show()
                        }
                    }
                })

            }

        }
    }
}
