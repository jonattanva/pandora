package com.monolieta.pandora.service

import com.monolieta.pandora.database.Game
import com.monolieta.pandora.database.GameQueries
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GameService(
    private val repository: GameQueries,
) : Service("") {

    fun findByAll(): Flow<List<Game>> = flow {}

    fun findById(id: String): Flow<Game?> = flow {}

    /*
    suspend fun d(): List<Game> {
        try {
            val games: List<Game> = client.get("$url/game")
            database.gameQueries.transaction {
                games.forEach {
                    database.gameQueries.exists(it.id)
                }
            }
            return emptyList()
        } catch (e: Exception) {
            return emptyList()
        }
    }

    suspend fun a(id: Long): Game? {
        try {
            var game: Game? = null
            val response: HttpResponse = client.get("$url/game?id=$id")

            if (response.status.isSuccess()) {
                game = response.receive() ?: return null

                database.gameQueries.transaction {
                    database.gameQueries.update(
                        id = game.id,
                        name = game.name,
                        description = game.description
                    )
                }
            }

            client.close()
            return game
        } catch (e: Exception) {
            client.close()
            return null
        }
    }

    fun findById(id: Long): Flow<Game> = flow {
        val current = getById(id)
        if (current != null) {
            emit(current)
        }

        val response: HttpResponse = client.get("$url/game?id=$id")
        if (response.status.isSuccess()) {
            val game: Game = response.receive()
            emit(game)
        }
    }

    private fun getById(id: Long): Game? {
        return try {
            database.gameQueries.findById(id)
                .executeAsOne()
        } catch (e: Exception) {
            null
        }
    }
     */
}