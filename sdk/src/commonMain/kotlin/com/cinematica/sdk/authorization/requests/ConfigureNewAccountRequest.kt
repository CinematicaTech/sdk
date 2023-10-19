package com.cinematica.sdk.authorization.requests

import com.cinematica.sdk.authorization.types.AuthorizationState
import com.cinematica.sdk.common.types.CinematicaEntity
import com.cinematica.sdk.common.types.CinematicaRequest
import com.cinematica.sdk.users.types.EmailAddress

public data class GetAuthorizationStateRequest(
    val emailAddress: EmailAddress,
) : CinematicaRequest<GetAuthorizationStateRequest.Result>() {
    public data class Result(val authorizationState: AuthorizationState) : CinematicaEntity()
}