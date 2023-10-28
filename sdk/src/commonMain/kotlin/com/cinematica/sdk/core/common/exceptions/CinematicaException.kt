package com.cinematica.sdk.core.common.exceptions

public abstract class CinematicaException internal constructor(
    override val message: String,
    override val cause: Throwable? = null,
) : Exception(message, cause)