package com.cinematica.sdk.common.exceptions

public data class UnsupportedException(
    override val message: String
) : CinematicaException(message)
