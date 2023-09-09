package com.cinematica.api.authorization.repository

import com.cinematica.api.authorization.entities.authorization.AuthorizationRequest
import com.cinematica.api.authorization.entities.authorization.AuthorizationResponse
import com.cinematica.api.authorization.entities.state.AuthorizationState
import com.cinematica.api.authorization.entities.state.AuthorizationStateRequest
import com.cinematica.api.authorization.entities.state.AuthorizationStateResponse
import com.cinematica.api.authorization.service.CinematicaAuthorizationService

class AuthorizationRepository(
    private val cinematicaAuthorizationService: CinematicaAuthorizationService
) {

    fun signUp(authorizationRequest: AuthorizationRequest): Result<AuthorizationResponse> {
        println("1")
        val result = cinematicaAuthorizationService.signUp(authorizationRequest).execute()
        println("23")
        val data = AuthorizationResponse(result.body()?.token.toString())
        return if (result.isSuccessful) {
            Result.success(data)
        } else {
            Result.failure(Exception(result.errorBody()?.string()))
        }
    }

    fun state(
        authorizationStateRequest: AuthorizationStateRequest
    ): Result<AuthorizationState> {
        val result = cinematicaAuthorizationService.getAuthorizationState(
            authorizationStateRequest.email
        ).execute()
        val data = AuthorizationStateResponse(result.body()?.state.toString())
        return if (result.isSuccessful) {
            Result.success(data.mapToAuthorizationState())
        } else {
            Result.failure(Exception(result.errorBody()?.string()))
        }
    }
}