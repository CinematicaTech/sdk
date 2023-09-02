package com.cinematica.api.authorization.service

import com.cinematica.api.authorization.entities.authorization.AuthorizationRequest
import com.cinematica.api.authorization.entities.authorization.AuthorizationResponse
import com.cinematica.api.authorization.entities.state.AuthorizationStateResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface CinematicaAuthorizationService {

    @GET("state")
    fun getAuthorizationState(
        @Query("email") email: String
    ): Call<AuthorizationStateResponse>

    @POST("signup")
    fun signUp(
        @Body authorizationRequest: AuthorizationRequest
    ): Call<AuthorizationResponse>

    @POST("signin")
    fun signIn(
        @Body authorizationRequest: AuthorizationRequest
    ): Call<AuthorizationResponse>

    companion object {
        const val BASE_AUTH_URL = "https://cinematica-server.onrender.com/auth/"
    }
}
