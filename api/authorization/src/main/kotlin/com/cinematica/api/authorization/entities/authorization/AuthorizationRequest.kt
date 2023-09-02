package com.cinematica.api.authorization.entities.authorization

data class AuthorizationRequest(
    val email: String,
    val password: String
)