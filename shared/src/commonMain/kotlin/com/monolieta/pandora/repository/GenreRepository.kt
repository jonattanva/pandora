package com.monolieta.pandora.repository

import com.monolieta.pandora.util.dayToMillis
import com.pandora.database.Genre
import com.pandora.database.GenreQueries
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToOne
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.util.date.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class GenreRepository(
    private val queries: GenreQueries
) : Repository() {

    fun findById(id: Long): Flow<Genre> {
        fetchById(id)
        return getById(id)
    }

    private fun fetchById(id: Long): Flow<Unit> = flow {
        if (!hasGenre(id)) {
            val genres: MutableList<Genre> = client.post(URL) {
                setBody("fields ${FIELDS}; where id = $id;")
                headers {
                    append(HttpHeaders.Accept, "application/json")
                    append(HttpHeaders.Authorization, "")
                    append("Client-ID", "")
                }
            }.body()

            queries.transaction {
                genres.forEach { genre ->
                    save(genre)
                }
            }
        }
    }

    private fun getById(id: Long) = queries.findById(id)
        .asFlow()
        .mapToOne()

    private fun hasGenre(id: Long) = queries.hasGenre(id, FRESH_TIMEOUT)
        .executeAsOne()

    private fun exists(id: Long) = queries.exists(id)
        .asFlow()
        .mapToOne()

    private fun save(genre: Genre): Flow<Unit> {
        return exists(genre.id).map { exists ->
            if (!exists) {
                insert(genre)
            } else update(genre)
        }
    }

    private fun insert(genre: Genre) =
        queries.insert(id = genre.id, name = genre.name, lastUpdate = getTimeMillis())

    private fun update(genre: Genre) =
        queries.update(name = genre.name, lastUpdate = getTimeMillis(), id = genre.id)

    companion object {
        private val FRESH_TIMEOUT = dayToMillis(1)

        private const val FIELDS = "id,name"
        private const val URL = "https://api.igdb.com/v4/genres"
    }
}