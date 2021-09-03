package com.monolieta.pandora.repository

import com.monolieta.pandora.database.Game
import com.monolieta.pandora.database.GameQueries
import com.monolieta.pandora.dayToMillis
import com.monolieta.pandora.domain.http.HttpDetail
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToOne
import io.ktor.client.request.*
import io.ktor.util.date.*
import kotlinx.coroutines.flow.*

class GameRepository(
    private val url: String,
    private val repository: GameQueries
) : Repository() {

    fun findByKey(key: String): Flow<Game> {
        fetchByKey(key)
        return getByKey(key)
    }

    private fun getByKey(key: String): Flow<Game> = repository.findById(key)
        .asFlow()
        .mapToOne()

    private fun exists(key: String) = repository.exists(key)
        .asFlow()
        .mapToOne()

    private fun hasGame(key: String) = repository.hasGame(key, FRESH_TIMEOUT)
        .executeAsOne()

    private fun fetchByKey(key: String): Flow<Game> = flow {
        if (!hasGame(key)) {
            val response: HttpDetail<Game> = client.get("$url/pandora/games/$key")
            repository.transaction {
                save(game = response.body)
            }
        }
    }

    private fun save(game: Game): Flow<Unit> {
        return exists(game.key).map { exists ->
            if (!exists) {
                insert(game)
            } else update(game)
        }
    }

    private fun insert(game: Game) {
        repository.insert(
            key = game.key,
            cover = game.cover,
            release = game.release,
            lastUpdate = getTimeMillis(),
            developer = game.developer,
            name = game.name,
            description = game.description,
            platforms = game.platforms,
            genre = game.genre,
            screenshots = game.screenshots

        )
    }

    private fun update(game: Game) {
        repository.update(
            release = game.release,
            lastUpdate = getTimeMillis(),
            developer = game.developer,
            cover = game.cover,
            name = game.name,
            description = game.description,
            genre = game.genre,
            platforms = game.platforms,
            screenshots = game.screenshots,
            key = game.key
        )
    }

    companion object {
        val FRESH_TIMEOUT = dayToMillis(1)
    }
}