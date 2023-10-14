package com.cinematica.sdk.common.exceptions

import com.cinematica.sdk.common.exceptions.CinematicaException

public data class UnauthorizedException(
    override val message: String,
    override val cause: Throwable? = null
) : CinematicaException(message, cause) {
    public companion object {
    }
}