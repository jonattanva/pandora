package com.monolieta.pandora.repository

import com.monolieta.pandora.dayToMillis
import com.monolieta.pandora.model.http.HttpDetail
import com.pandora.database.Game
import com.pandora.database.GameQueries
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToOne
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.util.date.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class GameRepository(
    private val url: String,
    private val queries: GameQueries
) : Repository() {

    fun findByKey(key: String): Flow<Game> {
        fetchByKey(key)
        return getByKey(key)
    }

    private fun fetchByKey(key: String): Flow<Game> = flow {
        if (!hasGame(key)) {
            val response: HttpDetail<Game> = client.get("$url/pandora/game/$key")
                .body()

            queries.transaction {
                save(response.body)
            }
        }
    }

    private fun getByKey(key: String): Flow<Game> = queries.findById(key)
        .asFlow()
        .mapToOne()

    private fun hasGame(key: String) = queries.hasGame(key, FRESH_TIMEOUT)
        .executeAsOne()

    private fun exists(key: String) = queries.exists(key)
        .asFlow()
        .mapToOne()

    private fun save(game: Game): Flow<Unit> {
        return exists(game.key).map { exists ->
            if (!exists) {
                insert(game)
            } else update(game)
        }
    }

    private fun insert(game: Game) {
        queries.insert(
            key = game.key,
            cover = game.cover,
            name = game.name,
            description = game.description,
            lastUpdate = getTimeMillis(),
            screenshots = game.screenshots,
            developer = game.developer
        )
    }

    private fun update(game: Game) {
        queries.update(
            cover = game.cover,
            name = game.name,
            description = game.description,
            lastUpdate = getTimeMillis(),
            screenshots = game.screenshots,
            developer = game.developer,
            key = game.key
        )
    }

    companion object {
        val FRESH_TIMEOUT = dayToMillis(1)
    }
}