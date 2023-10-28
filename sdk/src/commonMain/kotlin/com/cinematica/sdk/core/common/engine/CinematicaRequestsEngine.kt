package com.cinematica.sdk.core.common.engine

import com.cinematica.sdk.core.common.types.CinematicaEntity
import com.cinematica.sdk.core.common.types.CinematicaRequest

public interface CinematicaRequestsEngine {
    public suspend fun <T : CinematicaEntity> execute(request: CinematicaRequest<T>): Result<T>
}
