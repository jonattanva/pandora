package com.monolieta.pandora.repository

import com.monolieta.pandora.model.User
import com.pandora.database.UserQueries
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToOne
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.map

class UserRepository(
    private val queries: UserQueries
) {
    fun findById(id: String) = queries.findById(id)
        .asFlow()
        .mapToOne()
        .map { User.convert(it) }

    fun findByEmail(email: String) = queries.findByEmail(email)
        .asFlow()
        .mapToOne()
        .map { User.convert(it) }

    fun exists(email: String) = queries.exists(email)
        .asFlow()
        .mapToOne()

    @OptIn(FlowPreview::class)
    fun save(user: User): Flow<User> = exists(user.email)
        .flatMapConcat { save(it, user) }

    private fun save(exists: Boolean, user: User): Flow<User> {
        if (exists) {
            insert(user)
        } else update(user)
        return findByEmail(user.email)
    }

    private fun insert(user: User) {
        queries.insert(
            id = user.id,
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
            id = user.id
        )
    }
}