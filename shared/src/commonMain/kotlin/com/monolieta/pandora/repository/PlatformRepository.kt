package com.monolieta.pandora.repository

import com.monolieta.pandora.util.dayToMillis
import com.pandora.database.Platform
import com.pandora.database.PlatformQueries
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToOne
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.util.date.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class PlatformRepository(
    private val queries: PlatformQueries
) : Repository() {

    fun findById(id: Long): Flow<Platform> {
        fetchById(id)
        return getById(id)
    }

    private fun fetchById(id: Long): Flow<Unit> = flow {
        if (!hasPlatform(id)) {
            val platforms: MutableList<Platform> = client.post(URL) {
                setBody("fields ${FIELDS}; where id = $id;")
                headers {
                    append(HttpHeaders.Accept, "application/json")
                    append(HttpHeaders.Authorization, "")
                    append("Client-ID", "")
                }
            }.body()

            queries.transaction {
                platforms.forEach { platform ->
                    save(platform)
                }
            }
        }
    }

    private fun getById(id: Long) = queries.findById(id)
        .asFlow()
        .mapToOne()

    private fun hasPlatform(id: Long) = queries.hasPlatform(id, FRESH_TIMEOUT)
        .executeAsOne()

    private fun exists(id: Long) = queries.exists(id)
        .asFlow()
        .mapToOne()

    private fun save(platform: Platform): Flow<Unit> {
        return exists(platform.id).map { exists ->
            if (!exists) {
                insert(platform)
            } else update(platform)
        }
    }

    private fun insert(platform: Platform) =
        queries.insert(id = platform.id, name = platform.name, lastUpdate = getTimeMillis())

    private fun update(platform: Platform) =
        queries.update(name = platform.name, lastUpdate = getTimeMillis(), id = platform.id)

    companion object {
        private val FRESH_TIMEOUT = dayToMillis(1)

        private const val FIELDS = "id,name"
        private const val URL = "https://api.igdb.com/v4/platforms"
    }
}