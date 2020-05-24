package com.santiagorodriguezalberto.bookazonapp.ui.copia.list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.santiagorodriguezalberto.bookazonapp.R
import com.santiagorodriguezalberto.bookazonapp.common.Constantes
import com.santiagorodriguezalberto.bookazonapp.common.MyApp
import com.santiagorodriguezalberto.bookazonapp.data.BibliotecaViewModel
import com.santiagorodriguezalberto.bookazonapp.data.CopiaViewModel
import com.santiagorodriguezalberto.bookazonapp.model.Biblioteca
import com.santiagorodriguezalberto.bookazonapp.model.Copia
import com.santiagorodriguezalberto.bookazonapp.ui.biblioteca.list.MyBibliotecaRecyclerViewAdapter

import javax.inject.Inject

class CopiaFragment : Fragment() {
    @Inject
    lateinit var copiaViewModel: CopiaViewModel
    private lateinit var copiaAdapter: MyCopiaRecyclerViewAdapter
    private var copias: List<Copia> = ArrayList()
    private var columnCount = 1
    var nombreBiblioteca: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.applicationContext as MyApp).getAppCompontent().inject(this)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_copia_list, container, false)

        nombreBiblioteca = activity?.intent?.extras?.getString(Constantes.INTENT_DETAIL_KEYWORD_LIBRARY_NAME);


        copiaAdapter = MyCopiaRecyclerViewAdapter()

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = copiaAdapter
            }
        }

        copiaViewModel.getCopiasByBibliotecaName(nombreBiblioteca!!).observe(viewLifecycleOwner, Observer {
            if (it != null) {
                copias = it
                copiaAdapter.setData(copias)
            }
        })

        return view
    }

}
