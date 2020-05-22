package com.santiagorodriguezalberto.bookazonapp.api

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.santiagorodriguezalberto.bookazonapp.common.Constantes
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Module
class BookazonClient {

    @Singleton
    @Provides
    @Named("url")
    fun provideBaseUrl(): String = Constantes.API_BASE_URL


    @Singleton
    @Provides
    fun createClient(@Named("url") baseUrl: String): BookazonService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).addInterceptor(BookazonTokenInterceptor()).build())
            .build()
            .create(BookazonService::class.java)
    }
}