package com.cinematica.api.authorization.entities.authorization

import kotlinx.serialization.Serializable

@Serializable
data class AuthorizationResponse(
    val token: String
)