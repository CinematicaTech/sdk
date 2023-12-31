@file:OptIn(InternalApi::class, ExperimentalApi::class)

package com.cinematica.sdk.core.common.exceptions.handler

import com.cinematica.sdk.core.common.annotations.ApiStatus
import com.cinematica.sdk.core.common.annotations.ExperimentalApi
import com.cinematica.sdk.core.common.annotations.InternalApi
import com.cinematica.sdk.core.common.exceptions.CinematicaException
import com.cinematica.sdk.core.common.exceptions.UnauthorizedException
import com.cinematica.sdk.core.common.exceptions.UnavailableException
import com.cinematica.sdk.core.common.types.CinematicaEntity

/**
 * Represents a type-safe wrapper class for handling exceptions in the context of an operation's result.
 *
 * The [SafeExceptionResult] class provides a convenient way to handle exceptions and control the flow of execution based on the encountered exception types.
 * It is designed to ensure type safety by using the generic type parameters [T] for the result value and [E] for the exception type.
 *
 * @param T The type of the result value.
 * @param E The type of the exception.
 * @property result The original result of the operation.
 */
@ExperimentalApi(status = ApiStatus.NEEDS_REVISION)
@JvmInline
public value class SafeExceptionResult<T, E : Throwable> @InternalApi constructor(
    @property:InternalApi public val result: Result<T>
)

/**
 * Converts a [Result] into a [SafeExceptionResult] for safe exception handling.
 *
 * The [safeResult] function is used to wrap the original [Result] in a [SafeExceptionResult],
 * which allows for more controlled handling of exceptions within the operation's result.
 *
 * @receiver The original result of the operation.
 * @return The [SafeExceptionResult] containing the original result.
 */
@ExperimentalApi(status = ApiStatus.NEEDS_REVISION)
public fun <T : CinematicaEntity> Result<T>.safeResult(): SafeExceptionResult<T, UnauthorizedException> {
    return SafeExceptionResult(this)
}

/**
 * Executes a specified block if the encountered exception is of type [UnauthorizedException].
 *
 * The [whenUnauthorized] function enables controlled handling of the [UnauthorizedException] by executing the provided block
 * if the encountered exception matches the type [UnauthorizedException].
 *
 * @param block The block of code to be executed if the encountered exception is [UnauthorizedException].
 * @return The [SafeExceptionResult] after executing the block.
 */
public inline fun <T> SafeExceptionResult<T, UnauthorizedException>.whenUnauthorized(
    block: (UnauthorizedException) -> Unit
): SafeExceptionResult<T, UnavailableException> {
    val exception = result.exceptionOrNull()
    if (exception is UnauthorizedException)
        block(exception)

    return SafeExceptionResult(result)
}


/**
 * Executes a specified block if any exception is encountered.
 *
 * **Any inheritor of [CinematicaException] are ignored here.**
 *
 * The [whenOtherError] function allows for handling any encountered exception by executing the provided block.
 * It is useful for executing common error-handling code regardless of the specific exception type.
 *
 * @param block The block of code to be executed if any exception is encountered.
 * @return The [SafeExceptionResult] after executing the block.
 */
@ExperimentalApi(status = ApiStatus.NEEDS_REVISION)
public inline fun <T, E : Throwable> SafeExceptionResult<T, E>.whenOtherError(
    block: (E) -> Unit
): SafeExceptionResult<T, Nothing> {
    result.exceptionOrNull()?.takeIf { it !is CinematicaException }?.let {
        @Suppress("UNCHECKED_CAST")
        block(it as E)
    }

    return SafeExceptionResult(result)
}

/**
 * Handles **ANY** throwable, even inheritors of [CinematicaException]. You should use it if
 * you want to handle some failure logic more than one time or when you want to ignore type-safe
 * way of handling failures with [whenUnauthorized], etc.
 *
 * But, if it's possible, it's better to use just [Result] API instead of [SafeExceptionResult].
 */
@ExperimentalApi(status = ApiStatus.NEEDS_REVISION)
public inline fun <T> SafeExceptionResult<T, *>.whenAnyError(
    block: (Throwable) -> Unit
): SafeExceptionResult<T, Nothing> {
    result.exceptionOrNull()?.let(block)

    return SafeExceptionResult(result)
}

/**
 * Executes a specified block if the operation is successful.
 *
 * The [whenSuccess] function allows for executing a block of code if the operation is successful,
 * providing access to the result value for further processing.
 *
 * @param block The block of code to be executed if the operation is successful.
 */
public inline fun <T> SafeExceptionResult<T, Nothing>.whenSuccess(
    block: (T) -> Unit
) {
    if(result.isSuccess)
        block(result.getOrThrow())
}

/**
 * Ignores the [UnauthorizedException] exception and continues execution.
 *
 * The [ignoreUnauthorized] function allows for ignoring the [UnauthorizedException] exception
 * and continuing the flow of execution without interruption.
 *
 * @receiver The [SafeExceptionResult].
 * @return The [SafeExceptionResult] after ignoring the [UnauthorizedException].
 */
public fun <T> SafeExceptionResult<T, UnauthorizedException>.ignoreUnauthorized(): SafeExceptionResult<T, UnavailableException> {
    return SafeExceptionResult(result)
}