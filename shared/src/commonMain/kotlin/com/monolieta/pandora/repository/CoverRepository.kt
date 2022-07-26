package com.monolieta.pandora.repository

import com.monolieta.pandora.util.dayToMillis
import com.pandora.database.Cover
import com.pandora.database.CoverQueries
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToOne
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.util.date.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class CoverRepository(
    private val queries: CoverQueries
): Repository() {

    fun findById(id: Long): Flow<Cover> {
        fetchById(id)
        return getById(id)
    }

    private fun fetchById(id: Long): Flow<Unit> = flow {
        if (!hasCover(id)) {
            val covers: MutableList<Cover> = client.post(URL) {
                setBody("fields ${FIELDS}; where id = $id;")
                headers {
                    append(HttpHeaders.Accept, "application/json")
                    append(HttpHeaders.Authorization, "")
                    append("Client-ID", "")
                }
            }.body()

            queries.transaction {
                covers.forEach { cover ->
                    save(cover)
                }
            }
        }
    }

    private fun getById(id: Long) = queries.findById(id)
        .asFlow()
        .mapToOne()

    private fun hasCover(id: Long) = queries.hasCover(id, FRESH_TIMEOUT)
        .executeAsOne()

    private fun exists(id: Long) = queries.exists(id)
        .asFlow()
        .mapToOne()

    private fun save(cover: Cover): Flow<Unit> {
        return exists(cover.id).map { exists -> 
            if (!exists) {
                insert(cover)
            } else update(cover)
        }
    }

    private fun insert(cover: Cover) =
        queries.insert(id = cover.id, name = cover.name, lastUpdate = getTimeMillis())

    private fun update(cover: Cover) =
        queries.update(name = cover.name, lastUpdate = getTimeMillis(), id = cover.id)

    companion object {
        private val FRESH_TIMEOUT = dayToMillis(1)

        private const val FIELDS = "*"
        private const val URL = "https://api.igdb.com/v4/covers"
    }
}