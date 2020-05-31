package com.santiagorodriguezalberto.bookazonapp.ui.reserva.list

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import coil.api.load
import coil.transform.CircleCropTransformation
import com.santiagorodriguezalberto.bookazonapp.R
import com.santiagorodriguezalberto.bookazonapp.common.Constantes
import com.santiagorodriguezalberto.bookazonapp.common.MyApp
import com.santiagorodriguezalberto.bookazonapp.common.Resource
import com.santiagorodriguezalberto.bookazonapp.data.ReservaViewModel
import com.santiagorodriguezalberto.bookazonapp.model.Reserva
import com.santiagorodriguezalberto.bookazonapp.ui.DashboardActivity
import com.santiagorodriguezalberto.bookazonapp.ui.copia.detail.CopiaDetailActivity

import kotlinx.android.synthetic.main.fragment_reserva.view.*
import javax.inject.Inject

class MyReservaRecyclerViewAdapter(
    val reservaViewModel: ReservaViewModel
) : RecyclerView.Adapter<MyReservaRecyclerViewAdapter.ViewHolder>() {
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
        holder.biblioteca_reserva.text = item.copia.biblioteca.nombre
        holder.fecha_reserva.text = item.fecha_reserva

        holder.imagen.load(item.copia.imagen){
            transformations(CircleCropTransformation())
        }

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }

        holder.btn_delete.setOnClickListener{
            reservaViewModel.deleteReserva(item.id.toString())

            var i = Intent(MyApp.instance, DashboardActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            MyApp.instance.startActivity(i)

            Toast.makeText(MyApp.instance,"La reserva ha sido eliminada", Toast.LENGTH_SHORT).show()

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
        val btn_delete: Button = mView.btn_deleteReserva
    }
}
