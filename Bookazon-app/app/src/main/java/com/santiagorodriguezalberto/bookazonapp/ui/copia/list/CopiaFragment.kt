package com.santiagorodriguezalberto.bookazonapp.ui.copia.list

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.annotation.Nullable
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import com.santiagorodriguezalberto.bookazonapp.R
import com.santiagorodriguezalberto.bookazonapp.common.Constantes
import com.santiagorodriguezalberto.bookazonapp.common.MyApp
import com.santiagorodriguezalberto.bookazonapp.common.Resource
import com.santiagorodriguezalberto.bookazonapp.data.CopiaViewModel
import com.santiagorodriguezalberto.bookazonapp.model.Copia

import javax.inject.Inject

class CopiaFragment : Fragment() {
    @Inject
    lateinit var copiaViewModel: CopiaViewModel
    private lateinit var copiaAdapter: MyCopiaRecyclerViewAdapter
    private var copias: List<Copia> = ArrayList()
    private var columnCount = 1
    var nombreBiblioteca: String? = null

    private val generosList: MutableList<String> = ArrayList()
    lateinit var adapterGeneros: ArrayAdapter<String>
    lateinit var generoSelect: String

    private val autoresList: MutableList<String> = ArrayList()
    lateinit var adapterAutores: ArrayAdapter<String>
    lateinit var autorSelect: String

    override fun onActivityCreated(@Nullable savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_copia_fragment, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.filtro_generos -> {
                val builder = AlertDialog.Builder(context)
                val title = TextView(context)
                title.text = "Seleccione un género"
                title.setPadding(20, 30, 20, 30)
                title.textSize = 20F
                title.setTextColor(resources.getColor(R.color.colorAccent))
                title.setBackgroundColor(resources.getColor(R.color.colorPrimaryDark))
                builder.setCustomTitle(title)

                val lvGeneros = ListView(context)
                lvGeneros.adapter = adapterGeneros

                val dialog = builder
                    .setView(lvGeneros)
                    .create()

                lvGeneros.setOnItemClickListener { parent, view, position, id ->
                    generoSelect= generosList[position]

                    Toast.makeText(MyApp.instance,"Género: $generoSelect", Toast.LENGTH_LONG).show()

                    copiaViewModel.getCopiasByGenero(nombreBiblioteca!!, generoSelect)
                    dialog.dismiss()
                }

                dialog.show()

            }

            R.id.filtro_autores -> {
                val builder = AlertDialog.Builder(context)
                val title = TextView(context)
                title.text = "Seleccione un autor"
                title.setPadding(20, 30, 20, 30)
                title.textSize = 20F
                title.setTextColor(resources.getColor(R.color.colorAccent))
                title.setBackgroundColor(resources.getColor(R.color.colorPrimaryDark))
                builder.setCustomTitle(title)

                val lvAutores = ListView(context)
                lvAutores.adapter = adapterAutores

                val dialog = builder
                    .setView(lvAutores)
                    .create()

                lvAutores.setOnItemClickListener { parent, view, position, id ->
                    autorSelect= autoresList[position]

                    Toast.makeText(MyApp.instance,"Género: $autorSelect", Toast.LENGTH_LONG).show()

                    copiaViewModel.getCopiasByAutor(nombreBiblioteca!!, autorSelect)
                    dialog.dismiss()
                }

                dialog.show()
            }
        }
        return super.onOptionsItemSelected(item)
    }


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

        copiaViewModel.getGeneros(nombreBiblioteca!!)

        copiaViewModel.generos.observe(viewLifecycleOwner, Observer {response ->
            when(response) {
                is Resource.Success ->  {
                    generosList.addAll(response.data!!)
                    adapterGeneros = ArrayAdapter(MyApp.instance,android.R.layout.simple_expandable_list_item_1,generosList)
                }

                is Resource.Loading -> {
                    //CARGANDO
                }

                is Resource.Error -> {
                    Toast.makeText(MyApp.instance,"Error, ${response.message}", Toast.LENGTH_LONG).show()
                }
            }
        })

        copiaViewModel.getAutores(nombreBiblioteca!!)

        copiaViewModel.autores.observe(viewLifecycleOwner, Observer {response ->
            when(response) {
                is Resource.Success ->  {
                    autoresList.addAll(response.data!!)
                    adapterAutores = ArrayAdapter(MyApp.instance,android.R.layout.simple_expandable_list_item_1,autoresList)
                }

                is Resource.Loading -> {
                    //CARGANDO
                }

                is Resource.Error -> {
                    Toast.makeText(MyApp.instance,"Error, ${response.message}", Toast.LENGTH_LONG).show()
                }
            }
        })

        copiaViewModel.getCopiasByBibliotecaName(nombreBiblioteca!!)

        copiaViewModel.copias.observe(viewLifecycleOwner, Observer {response ->
            when(response) {
                is Resource.Success ->  {
                    copias = response.data!!
                    copiaAdapter.setData(copias)
                }

                is Resource.Loading -> {
                    //CARGANDO
                }

                is Resource.Error -> {
                    Toast.makeText(MyApp.instance,"Error, ${response.message}", Toast.LENGTH_LONG).show()
                }
            }
        })

        return view
    }

}
