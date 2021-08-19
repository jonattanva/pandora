package com.monolieta.pandora.service

import io.ktor.client.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlin.native.concurrent.SharedImmutable

@SharedImmutable
internal expect val ApplicationDispatcher: CoroutineDispatcher

abstract class Service(protected val url: String) {
    protected val client = HttpClient()
}