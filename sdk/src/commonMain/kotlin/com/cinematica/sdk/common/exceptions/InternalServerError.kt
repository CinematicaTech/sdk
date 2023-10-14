package com.cinematica.sdk.common.exceptions

public class InternalServerError(
    message: String,
    cause: Throwable?,
) : CinematicaException(message, cause)