package com.cinematica.sdk.common.exceptions

public abstract class CinematicaException internal constructor(
    override val message: String,
    override val cause: Throwable? = null,
) : Exception(message, cause)