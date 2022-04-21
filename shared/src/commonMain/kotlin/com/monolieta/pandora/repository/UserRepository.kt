package com.monolieta.pandora.repository

import com.monolieta.pandora.model.User
import com.pandora.database.UserQueries
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToOne
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepository(
    private val queries: UserQueries
) {

    fun findById(id: String) = queries.findById(id)
        .asFlow()
        .mapToOne()

    fun findByEmail(email: String) = queries.findByEmail(email)
        .asFlow()
        .mapToOne()

    fun exists(email: String) = queries.exists(email)
        .asFlow()
        .mapToOne()

    fun save(user: User): Flow<Unit> {
        return exists(user.email).map { exists ->
            if (!exists) {
                insert(user)
            } else update(user)
        }
    }

    private fun insert(user: User) {
        queries.insert(
            id = user.id!!,
            email = user.email,
            password = user.password,
            verified = user.verified
        )
    }

    private fun update(user: User) {
        queries.update(
            email = user.email,
            password = user.password,
            verified = user.verified,
            id = user.id!!
        )
    }
}