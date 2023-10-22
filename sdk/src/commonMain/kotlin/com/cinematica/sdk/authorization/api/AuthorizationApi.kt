package com.cinematica.sdk.authorization.api

import com.cinematica.sdk.authorization.requests.GetAuthorizationStateRequest
import com.cinematica.sdk.common.engine.CinematicaRequestsEngine
import com.cinematica.sdk.users.types.EmailAddress

public class AuthorizationApi(private val engine: CinematicaRequestsEngine) {
    public suspend fun getAuthorizationState(email: EmailAddress): Result<GetAuthorizationStateRequest.Result> {
        return engine.execute(GetAuthorizationStateRequest(email))
    }
}