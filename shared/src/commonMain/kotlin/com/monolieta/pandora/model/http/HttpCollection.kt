package com.monolieta.pandora.model.http

data class HttpCollection<T>(
    val body: MutableList<T>
)