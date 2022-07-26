package com.monolieta.pandora.repository

import com.monolieta.pandora.util.dayToMillis
import com.pandora.database.Screenshot
import com.pandora.database.ScreenshotQueries
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToOne
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.util.date.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class ScreenshotRepository(
    private val queries: ScreenshotQueries
) : Repository() {

    fun findById(id: Long): Flow<Screenshot> {
        return getById(id)
    }

    private fun fetchById(id: Long): Flow<Unit> = flow {
        if (!hasScreenshot(id)) {
            val screenshots: MutableList<Screenshot> = client.post(URL) {
                setBody("fields ${FIELDS}; where id = $id;")
                headers {
                    append(HttpHeaders.Accept, "application/json")
                    append(HttpHeaders.Authorization, "")
                    append("Client-ID", "")
                }
            }.body()

            queries.transaction {
                screenshots.forEach { screenshot ->
                    save(screenshot)
                }
            }
        }
    }

    private fun getById(id: Long) = queries.findById(id)
        .asFlow()
        .mapToOne()

    private fun hasScreenshot(id: Long) = queries.hasScreenshot(id, FRESH_TIMEOUT)
        .executeAsOne()

    private fun exists(id: Long) = queries.exists(id)
        .asFlow()
        .mapToOne()

    private fun save(screenshot: Screenshot): Flow<Unit> {
        return exists(screenshot.id).map { exists ->
            if (!exists) {
                insert(screenshot)
            } else update(screenshot)
        }
    }

    private fun insert(screenshot: Screenshot) =
        queries.insert(id = screenshot.id, name = screenshot.name, lastUpdate = getTimeMillis())

    private fun update(screenshot: Screenshot) =
        queries.update(name = screenshot.name, lastUpdate = getTimeMillis(), id = screenshot.id)

    companion object {
        private val FRESH_TIMEOUT = dayToMillis(1)

        private const val FIELDS = "id,name"
        private const val URL = "https://api.igdb.com/v4/screenshots"
    }
}