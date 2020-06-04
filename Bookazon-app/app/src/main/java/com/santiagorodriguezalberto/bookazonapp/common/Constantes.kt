package com.santiagorodriguezalberto.bookazonapp.common

import com.santiagorodriguezalberto.bookazonapp.model.Biblioteca

class Constantes {
    companion object {
        /*const val API_BASE_URL = "https://quiet-dusk-45914.herokuapp.com/"*/
        const val API_BASE_URL = "http://10.0.2.2:9000"
        val SHARED_PREFS_FILE: String? = "SHARED_PREFERENCES_FILE"
        const val SHARED_PREFERENCES_TOKEN_KEYWORD = "token"
        const val INTENT_DETAIL_KEYWORD_LIBRARY_NAME="name"
        const val INTENT_DETAIL_KEYWORD_COPY_ID="id"
        const val INTENT_DETAIL_KEYWORD_TITLE="title"
        const val INTENT_EDIT_KEYWORD = "edit"
        var LISTA_BIBLIOTECAS: List<Biblioteca>? = ArrayList()
    }
}