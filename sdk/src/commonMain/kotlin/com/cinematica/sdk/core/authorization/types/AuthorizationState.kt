package com.cinematica.sdk.core.authorization.types

import com.cinematica.sdk.core.common.types.CinematicaEntity

public data class AuthorizationState(
    val state: String
) : CinematicaEntity()

public enum class AuthorizationMethod(public val value: String) {
    SIGN_UP("sign_up"),
    SIGN_IN("sign_in")
}