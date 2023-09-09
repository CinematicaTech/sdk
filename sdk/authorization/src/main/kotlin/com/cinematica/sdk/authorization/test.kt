package com.cinematica.sdk.authorization

import com.cinematica.sdk.authorization.engine.CinematicaAuthorizationEngine
import com.cinematica.sdk.authorization.entities.authorization.AuthorizationRequest
import com.cinematica.sdk.authorization.entities.state.AuthorizationStateRequest

fun main() {
    val engine = CinematicaAuthorizationEngine()
    val authorizationRepository = engine.authorizationRepository
   // val authorizationData = AuthorizationRequest("grinikvadim0", "32131243")
    val result = authorizationRepository.state(AuthorizationStateRequest("vadwwxz@gmail.com"))
    println("sdd")
    println((result.getOrException() as ResultData.Success<*>).value)
    //println(result.getOrNull())
}

fun <T> Result<T>.getOrException(): ResultData {
    return if (this.isFailure) {
        ResultData.Error(this.exceptionOrNull()?.message ?: "data is null")
    } else {
        ResultData.Success(this.getOrThrow())
    }
}

sealed interface ResultData {
    class Success<T>(val value: T) : ResultData

    class Error(val errorMessage: String) : ResultData
}