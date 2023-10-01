package com.cinematica.sdk.grpc.factory

import io.grpc.ManagedChannel

public interface GrpcEngineBuilder {
    public fun createGrpcEngine(endpoint: String): ManagedChannel
}