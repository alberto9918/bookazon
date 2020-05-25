package com.santiagorodriguezalberto.bookazonapp.ui.copia.list

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import coil.api.load
import coil.transform.CircleCropTransformation
import com.santiagorodriguezalberto.bookazonapp.R
import com.santiagorodriguezalberto.bookazonapp.common.Constantes
import com.santiagorodriguezalberto.bookazonapp.common.MyApp
import com.santiagorodriguezalberto.bookazonapp.model.Copia
import com.santiagorodriguezalberto.bookazonapp.ui.biblioteca.detail.BibliotecaDetailActivity
import com.santiagorodriguezalberto.bookazonapp.ui.copia.detail.CopiaDetailActivity


import kotlinx.android.synthetic.main.fragment_copia.view.*

class MyCopiaRecyclerViewAdapter() : RecyclerView.Adapter<MyCopiaRecyclerViewAdapter.ViewHolder>() {
    private var copias: List<Copia> = ArrayList()
    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val copiaClicked = v.tag as Copia
            val idCopiaSeleccionada = copiaClicked.id.toString()

            var i = Intent(MyApp.instance, CopiaDetailActivity::class.java).apply {
                putExtra(Constantes.INTENT_DETAIL_KEYWORD_COPY_ID, idCopiaSeleccionada)
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }

            MyApp.instance.startActivity(i)

            Toast.makeText(MyApp.instance, idCopiaSeleccionada, Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_copia, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = copias[position]
        holder.titulo.text = item.titulo
        holder.autor.text = item.autor
        holder.genero.text = item.genero

        holder.imagen.load(item.imagen){
            transformations(CircleCropTransformation())
        }

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = copias.size

    fun setData(copias: List<Copia>?) {
        this.copias = copias!!
        notifyDataSetChanged()
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val titulo: TextView = mView.tv_copyName
        val autor: TextView = mView.tv_copyAuthor
        val genero: TextView = mView.tv_copyGender
        val imagen: ImageView = mView.imageView

    }
}
