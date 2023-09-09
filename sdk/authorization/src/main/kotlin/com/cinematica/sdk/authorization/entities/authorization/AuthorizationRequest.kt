package com.cinematica.sdk.authorization.entities.authorization

import kotlinx.serialization.Serializable

@Serializable
data class AuthorizationRequest(
    val email: String,
    val password: String
)