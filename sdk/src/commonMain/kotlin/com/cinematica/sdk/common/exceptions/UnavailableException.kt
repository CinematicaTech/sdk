package com.cinematica.sdk.common.exceptions

public data class UnavailableException(
    override val message: String,
    override val cause: Throwable
) : CinematicaException("Method or service is unavailable: $message", cause)