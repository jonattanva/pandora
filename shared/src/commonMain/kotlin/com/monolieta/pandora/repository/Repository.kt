package com.monolieta.pandora.repository

import io.ktor.client.*

abstract class Repository {
    protected val client = HttpClient()
}