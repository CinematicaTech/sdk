package com.cinematica.sdk.common.engine

import com.cinematica.sdk.common.types.CinematicaEntity
import com.cinematica.sdk.common.types.CinematicaRequest

public interface CinematicaRequestsEngine {
    public suspend fun <T : CinematicaEntity> execute(request: CinematicaRequest<T>): Result<T>
}
