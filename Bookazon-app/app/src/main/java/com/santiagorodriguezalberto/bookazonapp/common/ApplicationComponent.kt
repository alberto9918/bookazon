package com.santiagorodriguezalberto.bookazonapp.common

import com.santiagorodriguezalberto.bookazonapp.ui.MainActivity
import com.santiagorodriguezalberto.bookazonapp.api.BookazonClient
import com.santiagorodriguezalberto.bookazonapp.ui.DashboardActivity
import com.santiagorodriguezalberto.bookazonapp.ui.RegisterActivity
import com.santiagorodriguezalberto.bookazonapp.ui.biblioteca.detail.BibliotecaDetailActivity
import com.santiagorodriguezalberto.bookazonapp.ui.biblioteca.list.BibliotecaFragment
import com.santiagorodriguezalberto.bookazonapp.ui.biblioteca.map.MapBibliotecaFragment
import com.santiagorodriguezalberto.bookazonapp.ui.copia.CopiaFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [BookazonClient::class])
interface ApplicationComponent {
    fun inject(main: MainActivity)
    fun inject(registerActivity: RegisterActivity)
    fun inject(dashboard: DashboardActivity)
    fun inject(bibliotecaFragment: BibliotecaFragment)
    fun inject(bibliotecaDetailActivity: BibliotecaDetailActivity)
    fun inject(copiaFragment: CopiaFragment)
    fun inject(mapBibliotecaFragment: MapBibliotecaFragment)
}