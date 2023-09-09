package com.cinematica.sdk.authorization.entities.authorization

import kotlinx.serialization.Serializable

@Serializable
data class AuthorizationResponse(
    val token: String
)