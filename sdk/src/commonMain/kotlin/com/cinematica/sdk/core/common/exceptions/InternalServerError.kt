package com.cinematica.sdk.core.common.exceptions

public class InternalServerError(
    message: String,
    cause: Throwable?,
) : CinematicaException(message, cause)