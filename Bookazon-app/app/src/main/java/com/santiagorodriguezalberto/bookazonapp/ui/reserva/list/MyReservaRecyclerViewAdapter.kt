package com.santiagorodriguezalberto.bookazonapp.ui.reserva.list

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import coil.api.load
import coil.transform.CircleCropTransformation
import com.santiagorodriguezalberto.bookazonapp.R
import com.santiagorodriguezalberto.bookazonapp.common.Constantes
import com.santiagorodriguezalberto.bookazonapp.model.Reserva

import kotlinx.android.synthetic.main.fragment_reserva.view.*

class MyReservaRecyclerViewAdapter() : RecyclerView.Adapter<MyReservaRecyclerViewAdapter.ViewHolder>() {
    private var reservas: List<Reserva> = ArrayList()
    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val reservaClicked = v.tag as Reserva
            val idReservaSeleccionada = reservaClicked.id.toString()

            //AQUI HACER EL INTENT AL DETALLE DE RESERVA

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_reserva, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = reservas[position]
        holder.titulo_copia.text = item.copia.titulo
        holder.biblioteca_reserva.text = item.copia.editorial
        holder.fecha_reserva.text = item.fecha_reserva

        holder.imagen.load(item.copia.imagen){
            transformations(CircleCropTransformation())
        }

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = reservas.size

    fun setData(reservas: List<Reserva>?) {
        this.reservas = reservas!!
        notifyDataSetChanged()
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val titulo_copia: TextView = mView.tv_TitleCopyReservada
        val biblioteca_reserva: TextView = mView.tv_BibliotecaCopyReservada
        val fecha_reserva: TextView = mView.tv_FechaReservaCopy
        val imagen: ImageView = mView.imageViewCopyReservada
    }
}
