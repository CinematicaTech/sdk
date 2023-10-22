package com.cinematica.sdk.grpc

import com.cinematica.sdk.authorization.api.AuthorizationApi
import com.cinematica.sdk.common.constructor.createOrThrow
import com.cinematica.sdk.grpc.factory.DefaultGrpcEngineBuilder
import com.cinematica.sdk.users.types.EmailAddress
import kotlinx.coroutines.runBlocking

public fun main(): Unit = runBlocking {
    val authApi = AuthorizationApi(GrpcCinematicaRequestsEngine(grpcEngineBuilder = DefaultGrpcEngineBuilder()))
    val result = authApi.getAuthorizationState(EmailAddress.createOrThrow("vadwwxz@gmail.com"))
    print(result.getOrNull())
}