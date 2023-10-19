package com.cinematica.sdk.authorization.types

import com.cinematica.sdk.common.types.CinematicaEntity

public data class AuthorizationState(
    val state: String
) : CinematicaEntity()

public enum class AuthorizationMethod(public val value: String) {
    SIGN_UP("sign_up"),
    SIGN_IN("sign_in")
}