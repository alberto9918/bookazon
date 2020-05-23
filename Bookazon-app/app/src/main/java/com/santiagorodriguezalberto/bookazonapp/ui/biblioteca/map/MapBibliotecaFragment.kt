package com.santiagorodriguezalberto.bookazonapp.ui.biblioteca.map

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.santiagorodriguezalberto.bookazonapp.R
import com.santiagorodriguezalberto.bookazonapp.common.Constantes
import com.santiagorodriguezalberto.bookazonapp.common.MyApp
import com.santiagorodriguezalberto.bookazonapp.data.BibliotecaViewModel
import com.santiagorodriguezalberto.bookazonapp.model.Biblioteca
import com.santiagorodriguezalberto.bookazonapp.ui.RegisterActivity
import com.santiagorodriguezalberto.bookazonapp.ui.biblioteca.detail.BibliotecaDetailActivity
import com.santiagorodriguezalberto.bookazonapp.ui.biblioteca.list.BibliotecaListActivity
import java.util.*
import javax.inject.Inject


class MapBibliotecaFragment : Fragment() {
    @Inject
    lateinit var bibliotecaViewModel: BibliotecaViewModel
    var listaBibliotecas: List<Biblioteca> = ArrayList()
    var doubleClick = true

    //lateinit var dashboardActivity: DashboardActivity
    private var mMap: GoogleMap? = null

    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */

        /*val sydney = LatLng(-34.0, 151.0)
        googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))*/

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

                Toast.makeText(MyApp.instance, it.title, Toast.LENGTH_LONG).show()
                /*val intent = Intent(MyApp.instance, RegisterActivity::class.java).apply {
                    putExtra(Constantes.INTENT_DETAIL_KEYWORD_TITLE, it.title)
                    Log.d("bibliotecas3",it.title)
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                }

                MyApp.instance.startActivity(intent)*/
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
        bibliotecaViewModel.getAll().observe(viewLifecycleOwner, Observer {
            if (it != null) {
                listaBibliotecas = it
                Log.d("bibliotecas2", listaBibliotecas.toString())
            }
        })

        Log.d("bibliotecas", listaBibliotecas.toString())
        Log.d("bibliotecas1","PRUEBA")


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


