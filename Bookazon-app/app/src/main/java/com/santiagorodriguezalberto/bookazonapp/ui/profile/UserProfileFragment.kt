package com.santiagorodriguezalberto.bookazonapp.ui.profile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import butterknife.BindView
import butterknife.ButterKnife
import coil.api.load
import coil.transform.CircleCropTransformation
import com.google.android.material.floatingactionbutton.FloatingActionButton

import com.santiagorodriguezalberto.bookazonapp.R
import com.santiagorodriguezalberto.bookazonapp.common.Constantes
import com.santiagorodriguezalberto.bookazonapp.common.MyApp
import com.santiagorodriguezalberto.bookazonapp.common.Resource
import com.santiagorodriguezalberto.bookazonapp.common.SharedPreferencesManager
import com.santiagorodriguezalberto.bookazonapp.data.UserViewModel
import com.santiagorodriguezalberto.bookazonapp.ui.DashboardActivity
import com.santiagorodriguezalberto.bookazonapp.ui.MainActivity
import javax.inject.Inject

class UserProfileFragment : Fragment() {
    @Inject
    lateinit var userViewModel: UserViewModel

    @BindView(R.id.tv_nombreProfile)
    lateinit var nombre: TextView
    @BindView(R.id.tv_apellidosProfile)
    lateinit var apellidos: TextView
    @BindView(R.id.tv_emailProfile)
    lateinit var email: TextView
    @BindView(R.id.tv_dniProfile)
    lateinit var dni: TextView
    @BindView(R.id.tv_telefonoProfile)
    lateinit var telefono: TextView
    @BindView(R.id.imageViewProfile)
    lateinit var fotoPerfil: ImageView

    @BindView(R.id.fb_editProfile)
    lateinit var btn_editProfile: FloatingActionButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.applicationContext as MyApp).getAppCompontent().inject(this)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_user_profile, container, false)

        ButterKnife.bind(this, view);

        userViewModel.getUsuario()

        userViewModel.usuario.observe(viewLifecycleOwner, Observer {response ->
            when(response) {
                is Resource.Success ->  {
                    nombre.text = response.data?.nombre
                    apellidos.text = response.data?.apellidos
                    email.text = response.data?.email
                    dni.text = response.data?.dni
                    telefono.text = response.data?.telefono.toString()

                    if(response.data?.profileImage.isNullOrEmpty()){
                        fotoPerfil.load(
                            Uri.parse("file:///android_asset/avatar.png")
                        ){
                            transformations(CircleCropTransformation())
                        }
                    }else{
                        fotoPerfil.load(response.data?.profileImage){
                            transformations(CircleCropTransformation())
                        }
                    }
                }

                is Resource.Loading -> {
                    //CARGANDO
                }

                is Resource.Error -> {
                    Toast.makeText(MyApp.instance,"Error, ${response.message}", Toast.LENGTH_LONG).show()
                }
            }
        })

        return view
    }


}
