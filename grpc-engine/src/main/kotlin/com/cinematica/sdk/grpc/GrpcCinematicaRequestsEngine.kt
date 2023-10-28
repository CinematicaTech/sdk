@file:Suppress("UNCHECKED_CAST")

package com.cinematica.sdk.grpc

import com.cinematica.backend.authorization.AuthorizationServiceGrpcKt
import com.cinematica.sdk.core.authorization.requests.GetAuthorizationStateRequest
import com.cinematica.sdk.authorization.types.AuthorizationState
import com.cinematica.sdk.common.engine.CinematicaRequestsEngine
import com.cinematica.sdk.common.exceptions.InternalServerError
import com.cinematica.sdk.common.exceptions.UnauthorizedException
import com.cinematica.sdk.common.exceptions.UnavailableException
import com.cinematica.sdk.common.exceptions.UnsupportedException
import com.cinematica.sdk.common.types.CinematicaEntity
import com.cinematica.sdk.common.types.CinematicaRequest
import com.cinematica.sdk.grpc.factory.GrpcEngineBuilder
import com.cinematica.sdk.grpc.internal.mapException
import io.grpc.Metadata
import io.grpc.Status
import io.grpc.StatusException
import kotlinx.datetime.Instant
import com.cinematica.backend.authorization.requests.GetAuthorizationStateRequestOuterClass.GetAuthorizationStateRequest as GrpcGetAuthorizationStateRequest
import kotlin.reflect.KClass

public class GrpcCinematicaRequestsEngine(
    //endpoint: String = "cinematica-server-arfeseedaa-uc.a.run.app",
    endpoint: String = "localhost:8787",
    grpcEngineBuilder: GrpcEngineBuilder,
) : CinematicaRequestsEngine {

    private companion object {
        val ACCESS_TOKEN: Metadata.Key<String> = Metadata.Key.of("access-token", Metadata.ASCII_STRING_MARSHALLER)
    }

    private val channel = grpcEngineBuilder.createGrpcEngine(endpoint)

    private val authorizationService = AuthorizationServiceGrpcKt.AuthorizationServiceCoroutineStub(channel)
    // private val authMapper = AuthorizationsMapper()

    public override suspend fun <T : CinematicaEntity> execute(request: CinematicaRequest<T>): Result<T> = runCatching {
        when (request) {
            is com.cinematica.sdk.core.authorization.requests.GetAuthorizationStateRequest -> authorizationService.getAuthorizationState(
                GrpcGetAuthorizationStateRequest
                    .newBuilder()
                    .setEmailAddress(request.emailAddress.string)
                    .build()
            ).let {
                com.cinematica.sdk.core.authorization.requests.GetAuthorizationStateRequest.Result(
                    AuthorizationState(it.authorizationMethod)
                )
            }
            else -> unsupported(request::class)
        } as T
    }.mapException {
        val exception = (it as? StatusException) ?: return@mapException it
        val status = exception.status
        val message = exception.message ?: NO_MESSAGE

        when (status) {
            Status.UNAUTHENTICATED -> UnauthorizedException(message, exception)
            Status.INTERNAL -> InternalServerError(message, exception)
            Status.UNAVAILABLE -> UnavailableException(message, exception)

            else -> InternalServerError(message, exception)
        }
    }

    private fun unsupported(kClass: KClass<*>): Nothing =
        throw UnsupportedException("Request of type ${kClass.simpleName} is not supported")
}

private const val NO_MESSAGE = "No message is provided."