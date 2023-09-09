package com.cinematica.api.authorization.entities.state

sealed class AuthorizationState(val value: String) {
    object SignUp : AuthorizationState("sign_up")

    object SignIn : AuthorizationState("sign_in")
}