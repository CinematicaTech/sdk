package com.cinematica.sdk.core.authorization.requests

import com.cinematica.sdk.core.authorization.types.AuthorizationState
import com.cinematica.sdk.core.common.types.CinematicaEntity
import com.cinematica.sdk.core.common.types.CinematicaRequest
import com.cinematica.sdk.core.users.types.EmailAddress

public data class GetAuthorizationStateRequest(
    val emailAddress: EmailAddress,
) : CinematicaRequest<GetAuthorizationStateRequest.Result>() {
    public data class Result(val authorizationState: AuthorizationState) : CinematicaEntity()
}