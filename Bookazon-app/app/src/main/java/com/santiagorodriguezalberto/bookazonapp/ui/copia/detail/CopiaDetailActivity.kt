package com.santiagorodriguezalberto.bookazonapp.ui.copia.detail

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import butterknife.BindView
import butterknife.ButterKnife
import coil.api.load
import coil.transform.CircleCropTransformation
import com.santiagorodriguezalberto.bookazonapp.R
import com.santiagorodriguezalberto.bookazonapp.common.Constantes
import com.santiagorodriguezalberto.bookazonapp.common.MyApp
import com.santiagorodriguezalberto.bookazonapp.common.Resource
import com.santiagorodriguezalberto.bookazonapp.data.BibliotecaViewModel
import com.santiagorodriguezalberto.bookazonapp.data.CopiaViewModel
import com.santiagorodriguezalberto.bookazonapp.data.ReservaViewModel
import javax.inject.Inject

class CopiaDetailActivity : AppCompatActivity() {

    @Inject
    lateinit var copiaViewModel: CopiaViewModel
    @Inject
    lateinit var reservaViewModel: ReservaViewModel

    @BindView(R.id.imageView2)
    lateinit var img_copia: ImageView
    @BindView(R.id.tv_dtCopyTitle)
    lateinit var titulo: TextView
    @BindView(R.id.tv_dtCopyAuthor)
    lateinit var autor: TextView
    @BindView(R.id.tv_dtCopyEditorial)
    lateinit var editorial: TextView
    @BindView(R.id.tv_dtCopyGenero)
    lateinit var genero: TextView
    @BindView(R.id.tv_dtCopyISBN)
    lateinit var isbn: TextView
    @BindView(R.id.tv_dtCopyResumen)
    lateinit var resumen: TextView
    @BindView(R.id.btn_reservar)
    lateinit var btn_reserva: Button

    private var esta_reservada: Boolean = false
    private lateinit var idCopia: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_copia_detail)
        (applicationContext as MyApp).getAppCompontent().inject(this)

        ButterKnife.bind(this)

        idCopia = intent.getStringExtra(Constantes.INTENT_DETAIL_KEYWORD_COPY_ID)

        copiaViewModel.getCopia(idCopia)
        copiaViewModel.copia.observe(this, Observer {response ->
            when(response) {
                is Resource.Success ->  {
                    img_copia.load(response.data?.imagen){
                        crossfade(true)
                    }
                    titulo.text = response.data?.titulo
                    autor.text = response.data?.autor
                    editorial.text = response.data?.editorial
                    genero.text = response.data?.genero
                    isbn.text = response.data?.isbn
                    resumen.text = response.data?.resumen

                    esta_reservada = response.data!!.esta_reservada

                    btn_reserva.setOnClickListener(View.OnClickListener {

                        if(!esta_reservada){
                            reservaViewModel.doReserva(idCopia)

                            reservaViewModel.reserva.observe(this, Observer {response ->
                                when(response) {
                                    is Resource.Success ->  {
                                        Toast.makeText(MyApp.instance, "Reserva realizada con éxito", Toast.LENGTH_SHORT).show()
                                        esta_reservada = true
                                    }

                                    is Resource.Loading -> {
                                        //CARGANDO
                                    }

                                    is Resource.Error -> {
                                        Toast.makeText(MyApp.instance,"Error, ${response.message}", Toast.LENGTH_LONG).show()
                                    }
                                }
                            })

                        }else{
                            Toast.makeText(MyApp.instance, "Esta copia ya está reservada", Toast.LENGTH_SHORT).show()
                        }

                    })
                }

                is Resource.Loading -> {
                    //CARGANDO
                }

                is Resource.Error -> {
                    Toast.makeText(MyApp.instance,"Error, ${response.message}", Toast.LENGTH_LONG).show()
                }
            }
        })
    }
}
