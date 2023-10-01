package com.cinematica.sdk.grpc.android

import android.content.Context
import com.cinematica.sdk.grpc.factory.GrpcEngineBuilder
import io.grpc.ManagedChannel
import io.grpc.android.AndroidChannelBuilder

class AndroidGrpcEngineBuilder(private val context: Context): GrpcEngineBuilder {
    override fun createGrpcEngine(endpoint: String): ManagedChannel {
        return AndroidChannelBuilder.forTarget(endpoint)
            .useTransportSecurity()
            .context(context)
            .build()
    }
}