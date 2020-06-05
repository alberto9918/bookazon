package com.santiagorodriguezalberto.bookazonapp.ui.biblioteca.map

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.santiagorodriguezalberto.bookazonapp.R
import com.santiagorodriguezalberto.bookazonapp.common.Constantes
import com.santiagorodriguezalberto.bookazonapp.common.MyApp
import com.santiagorodriguezalberto.bookazonapp.common.Resource
import com.santiagorodriguezalberto.bookazonapp.data.BibliotecaViewModel
import com.santiagorodriguezalberto.bookazonapp.model.Biblioteca
import com.santiagorodriguezalberto.bookazonapp.ui.biblioteca.detail.BibliotecaDetailActivity
import com.santiagorodriguezalberto.bookazonapp.ui.biblioteca.list.BibliotecaListActivity
import java.util.*
import javax.inject.Inject


class MapBibliotecaFragment : Fragment() {
    @Inject
    lateinit var bibliotecaViewModel: BibliotecaViewModel
    var listaBibliotecas: List<Biblioteca> = ArrayList()
    var doubleClick = true

    private val callback = OnMapReadyCallback { googleMap ->


        for (biblioteca in listaBibliotecas){

            googleMap.addMarker(MarkerOptions()
                .position(LatLng(biblioteca.latitud,biblioteca.longitud))
                .title(biblioteca.nombre))


        }

        googleMap.setOnMarkerClickListener {
            if (doubleClick) {
                var i = Intent(MyApp.instance, BibliotecaDetailActivity::class.java).apply {
                    putExtra(Constantes.INTENT_DETAIL_KEYWORD_LIBRARY_NAME, it.title)
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                }

                MyApp.instance.startActivity(i)

            } else {

                doubleClick = true
                Handler().postDelayed(Runnable { doubleClick = false }, 1500) //1.5 segundos
            }
            false
        }


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.applicationContext as MyApp).getAppCompontent().inject(this)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bibliotecaViewModel.getAll()

        bibliotecaViewModel.bibliotecas.observe(viewLifecycleOwner, Observer {response ->
            when(response) {
                is Resource.Success ->  {
                    listaBibliotecas = response.data!!
                    Log.d("bibliotecas2", listaBibliotecas.toString())
                }

                is Resource.Loading -> {
                    //CARGANDO
                }

                is Resource.Error -> {
                    Toast.makeText(MyApp.instance,"Error, ${response.message}", Toast.LENGTH_LONG).show()
                }
            }
        })

        return inflater.inflate(R.layout.fragment_map_biblioteca, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_biblioteca_map, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_biblioteca_map_goList -> {

                var i = Intent(MyApp.instance, BibliotecaListActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                }
                MyApp.instance.startActivity(i)
            }
        }
        return super.onOptionsItemSelected(item)
    }

}


