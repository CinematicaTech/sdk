package com.cinematica.sdk.core.authorization.api

import com.cinematica.sdk.core.authorization.requests.GetAuthorizationStateRequest
import com.cinematica.sdk.core.common.engine.CinematicaRequestsEngine
import com.cinematica.sdk.core.users.types.EmailAddress

public class AuthorizationApi(private val engine: CinematicaRequestsEngine) {
    public suspend fun getAuthorizationState(email: EmailAddress): Result<GetAuthorizationStateRequest.Result> {
        return engine.execute(
            GetAuthorizationStateRequest(email)
        )
    }
}