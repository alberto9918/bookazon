package com.santiagorodriguezalberto.bookazonapp.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import butterknife.BindView
import butterknife.ButterKnife
import coil.api.load
import com.santiagorodriguezalberto.bookazonapp.R
import com.santiagorodriguezalberto.bookazonapp.api.request.LoginRequest
import com.santiagorodriguezalberto.bookazonapp.common.Constantes
import com.santiagorodriguezalberto.bookazonapp.common.MyApp
import com.santiagorodriguezalberto.bookazonapp.common.SharedPreferencesManager
import com.santiagorodriguezalberto.bookazonapp.data.UserViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var userViewModel: UserViewModel

    @BindView(R.id.et_correo)
    lateinit var email: EditText

    @BindView(R.id.et_passw)
    lateinit var password: EditText

    @BindView(R.id.btn_login)
    lateinit var buttonLogin: Button

    @BindView(R.id.tv_signup)
    lateinit var buttonSignUp: TextView

    @BindView(R.id.iv_logo)
    lateinit var logoBookazon: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (applicationContext as MyApp).getAppCompontent().inject(this)

        ButterKnife.bind(this)

        val token: String? = SharedPreferencesManager().getSharedPreferences()
            .getString(Constantes.SHARED_PREFERENCES_TOKEN_KEYWORD, "")

        if (!token.isNullOrEmpty()) {
            val autologin: Intent = Intent(MyApp.instance, DashboardActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            startActivity(autologin)
            finish()
        }

        logoBookazon.load(
            Uri.parse("file:///android_asset/icono-bookazon.png")
        )

        buttonLogin.setOnClickListener(View.OnClickListener {
            userViewModel.doLogin(
                LoginRequest(
                    username = email.text.toString(),
                    password = password.text.toString()
                )
            )
                .observe(this, Observer {
                    if(it==null){
                        Log.d("LOG IN","error al loguearte")
                    }else{
                        Log.d("LOG IN","login con exito")
                        val success: Intent = Intent(MyApp.instance, DashboardActivity::class.java).apply {
                            flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        }
                        startActivity(success)
                        finish()
                    }
                })
        })

        buttonSignUp.setOnClickListener{
            val register: Intent = Intent(MyApp.instance, RegisterActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            startActivity(register)
            finish()
        }

    }
}
