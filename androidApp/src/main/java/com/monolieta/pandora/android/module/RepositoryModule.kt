package com.monolieta.pandora.android.module

import com.monolieta.pandora.database.Database
import com.monolieta.pandora.repository.AuthenticationRepository
import com.monolieta.pandora.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {

    @Provides
    fun provideAuthenticationRepository(database: Database): AuthenticationRepository {
        return AuthenticationRepository(
            repository = UserRepository(queries = database.userQueries)
        )
    }
}