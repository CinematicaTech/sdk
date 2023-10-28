package com.cinematica.sdk.core.common.exceptions

public data class UnsupportedException(
    override val message: String
) : CinematicaException(message)
