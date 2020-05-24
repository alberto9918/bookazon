package com.santiagorodriguezalberto.bookazonapp.ui.copia.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import butterknife.BindView
import butterknife.ButterKnife
import coil.api.load
import coil.transform.CircleCropTransformation
import com.santiagorodriguezalberto.bookazonapp.R
import com.santiagorodriguezalberto.bookazonapp.common.Constantes
import com.santiagorodriguezalberto.bookazonapp.common.MyApp
import com.santiagorodriguezalberto.bookazonapp.data.BibliotecaViewModel
import com.santiagorodriguezalberto.bookazonapp.data.CopiaViewModel
import javax.inject.Inject

class CopiaDetailActivity : AppCompatActivity() {

    @Inject
    lateinit var copiaViewModel: CopiaViewModel

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

    private lateinit var idCopia: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_copia_detail)
        (applicationContext as MyApp).getAppCompontent().inject(this)

        ButterKnife.bind(this)

        idCopia = intent.getStringExtra(Constantes.INTENT_DETAIL_KEYWORD_COPY_ID)

        copiaViewModel.getCopia(idCopia).observe(this, Observer {
            img_copia.load(it.imagen){
                crossfade(true)
            }
            titulo.text = it.titulo
            autor.text = it.autor
            editorial.text = it.editorial
            genero.text = it.genero
            isbn.text = it.isbn
            resumen.text = it.resumen

            //FALTA AÑADIR EL METODO ONCLICK DEL BOTON

        })
    }
}
