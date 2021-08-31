package com.monolieta.pandora.domain.http

data class HttpDetail<T>(
    val body: T
)

data class HttpCollection<T>(
    val body: MutableList<T>
)