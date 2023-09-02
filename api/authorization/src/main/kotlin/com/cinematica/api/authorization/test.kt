package com.cinematica.api.authorization

import com.cinematica.api.authorization.service.CinematicaAuthorizationService
import com.cinematica.api.authorization.service.CinematicaAuthorizationService.Companion.BASE_AUTH_URL
import com.skydoves.retrofit.adapters.result.ResultCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

fun main() {
    val interceptor = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }
    val client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .readTimeout(1, TimeUnit.MINUTES)
        .build()
    val retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(BASE_AUTH_URL)
        .addCallAdapterFactory(ResultCallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val service = retrofit.create(CinematicaAuthorizationService::class.java)
//    val response = service.signIn(
//        AuthorizationRequest(
//            email = "someemail@gmail.com",
//            password = "23183782jsdjd 1"
//        )
//    )
    val response = service.getAuthorizationState("grinikvadim0@gmail.com")
    val data = response.execute()
    println("1  ${data.body()}")
    println("2  ${data.errorBody()?.string()}")
}