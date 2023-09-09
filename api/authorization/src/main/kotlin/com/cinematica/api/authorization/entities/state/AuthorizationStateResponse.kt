package com.cinematica.api.authorization.entities.state

data class AuthorizationStateResponse(
    val state: String
) {
    fun mapToAuthorizationState(): AuthorizationState {
        return if (state == AuthorizationState.SignIn.value) {
            AuthorizationState.SignIn
        } else {
            AuthorizationState.SignUp
        }
    }
}