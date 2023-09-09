package com.cinematica.api.authorization.engine

import com.cinematica.api.authorization.repository.AuthorizationRepository
import com.cinematica.api.authorization.service.CinematicaAuthorizationService
import com.skydoves.retrofit.adapters.result.ResultCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class CinematicaAuthorizationEngine {

    private val interceptor = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .readTimeout(1, TimeUnit.MINUTES)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(CinematicaAuthorizationService.BASE_AUTH_URL)
        .addCallAdapterFactory(ResultCallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(CinematicaAuthorizationService::class.java)

    val authorizationRepository = AuthorizationRepository(service)
}