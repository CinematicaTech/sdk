package com.cinematica.sdk.grpc

import com.cinematica.backend.authorization.AuthorizationServiceGrpcKt
import com.cinematica.sdk.common.engine.CinematicaRequestsEngine
import com.cinematica.sdk.common.exceptions.InternalServerError
import com.cinematica.sdk.common.exceptions.UnsupportedException
import com.cinematica.sdk.common.types.CinematicaEntity
import com.cinematica.sdk.common.types.CinematicaRequest
import com.cinematica.sdk.grpc.factory.GrpcEngineBuilder
import io.grpc.Metadata
import kotlin.reflect.KClass

public class GrpcCinematicaRequestsEngine(
    endpoint: String = "api.timemates.io",
    grpcEngineBuilder: GrpcEngineBuilder,
) : CinematicaRequestsEngine {

    private companion object {
        val ACCESS_TOKEN: Metadata.Key<String> = Metadata.Key.of("access-token", Metadata.ASCII_STRING_MARSHALLER)
    }

    private val channel = grpcEngineBuilder.createGrpcEngine(endpoint)

    private val authorizationService = AuthorizationServiceGrpcKt.AuthorizationServiceCoroutineStub(channel)
    // private val authMapper = AuthorizationsMapper()

    override suspend fun <T : CinematicaEntity> execute(request: CinematicaRequest<T>): Result<T> {
        when (request) {
            else -> unsupported(request::class)
        }
    }

    private fun unsupported(kClass: KClass<*>): Nothing =
        throw UnsupportedException("Request of type ${kClass.simpleName} is not supported")
}