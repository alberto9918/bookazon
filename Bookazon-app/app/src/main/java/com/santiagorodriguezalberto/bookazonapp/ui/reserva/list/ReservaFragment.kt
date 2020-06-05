package com.santiagorodriguezalberto.bookazonapp.ui.reserva.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.santiagorodriguezalberto.bookazonapp.R
import com.santiagorodriguezalberto.bookazonapp.common.MyApp
import com.santiagorodriguezalberto.bookazonapp.common.Resource
import com.santiagorodriguezalberto.bookazonapp.data.ReservaViewModel
import com.santiagorodriguezalberto.bookazonapp.model.Reserva
import javax.inject.Inject


class ReservaFragment : Fragment() {
    @Inject
    lateinit var reservaViewModel: ReservaViewModel
    private lateinit var reservaAdapter: MyReservaRecyclerViewAdapter
    private var reservas: List<Reserva> = ArrayList()
    private var columnCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.applicationContext as MyApp).getAppCompontent().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_reserva_list, container, false)

        reservaAdapter = MyReservaRecyclerViewAdapter(reservaViewModel)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = reservaAdapter
            }
        }

        reservaViewModel.getReservasByUser()
            reservaViewModel.reservas.observe(viewLifecycleOwner, Observer {response ->
                when(response) {
                    is Resource.Success ->  {
                        reservas = response.data!!
                        reservaAdapter.setData(reservas)
                    }

                    is Resource.Loading -> {
                        //CARGANDO
                    }

                    is Resource.Error -> {
                        Toast.makeText(MyApp.instance,"Lista vacía", Toast.LENGTH_LONG).show()
                    }
                }
            })

        return view
    }


}
