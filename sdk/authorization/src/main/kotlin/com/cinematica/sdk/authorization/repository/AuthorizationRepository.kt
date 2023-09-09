package com.cinematica.sdk.authorization.repository

import com.cinematica.sdk.authorization.entities.authorization.AuthorizationRequest
import com.cinematica.sdk.authorization.entities.authorization.AuthorizationResponse
import com.cinematica.sdk.authorization.entities.state.AuthorizationStateRequest
import com.cinematica.sdk.authorization.entities.state.AuthorizationStateResponse
import com.cinematica.sdk.authorization.service.CinematicaAuthorizationService

class AuthorizationRepository(
    private val cinematicaAuthorizationService: CinematicaAuthorizationService
) {

    fun signUp(authorizationRequest: AuthorizationRequest): Result<AuthorizationResponse> {
        val result = cinematicaAuthorizationService.signUp(authorizationRequest).execute()
        val data = AuthorizationResponse(result.body()?.token.toString())
        return if (result.isSuccessful) {
            Result.success(data)
        } else {
            Result.failure(Exception(result.errorBody()?.string()))
        }
    }

    fun signIn(authorizationRequest: AuthorizationRequest): Result<AuthorizationResponse> {
        val result = cinematicaAuthorizationService.signIn(authorizationRequest).execute()
        val data = AuthorizationResponse(result.body()?.token.toString())
        return if (result.isSuccessful) {
            Result.success(data)
        } else {
            Result.failure(Exception(result.errorBody()?.string()))
        }
    }

    fun state(
        authorizationStateRequest: AuthorizationStateRequest
    ): Result<AuthorizationStateResponse> {
        val result = cinematicaAuthorizationService.getAuthorizationState(
            authorizationStateRequest.email
        ).execute()
        val data = AuthorizationStateResponse(result.body()?.state.toString())
        return if (result.isSuccessful) {
            Result.success(data)
        } else {
            Result.failure(Exception(result.errorBody()?.string()))
        }
    }
}