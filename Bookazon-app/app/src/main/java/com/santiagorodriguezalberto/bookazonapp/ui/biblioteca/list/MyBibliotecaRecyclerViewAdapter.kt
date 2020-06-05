package com.santiagorodriguezalberto.bookazonapp.ui.biblioteca.list

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
import com.santiagorodriguezalberto.bookazonapp.model.Biblioteca
import com.santiagorodriguezalberto.bookazonapp.ui.biblioteca.detail.BibliotecaDetailActivity


import kotlinx.android.synthetic.main.fragment_biblioteca.view.*


class MyBibliotecaRecyclerViewAdapter() : RecyclerView.Adapter<MyBibliotecaRecyclerViewAdapter.ViewHolder>() {
    private var bibliotecas: List<Biblioteca> = ArrayList()
    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val bibliotecaClicked = v.tag as Biblioteca
            val nombreBibliotecaSeleccionada = bibliotecaClicked.nombre

            var i = Intent(MyApp.instance, BibliotecaDetailActivity::class.java).apply {
                putExtra(Constantes.INTENT_DETAIL_KEYWORD_LIBRARY_NAME, nombreBibliotecaSeleccionada)
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }

            MyApp.instance.startActivity(i)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_biblioteca, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = bibliotecas[position]
        holder.nombre_biblioteca.text = item.nombre

        holder.bibliotecaImage.load("file:///android_asset/biblioteca.png"){
            transformations(CircleCropTransformation())
        }

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = bibliotecas.size

    fun setData(bibliotecas: List<Biblioteca>?) {
        this.bibliotecas = bibliotecas!!
        notifyDataSetChanged()
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val nombre_biblioteca: TextView = mView.tv_biblioteca
        val bibliotecaImage: ImageView = mView.iv_bibliotecaImg

    }
}
