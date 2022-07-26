package com.monolieta.pandora.android.module

import com.monolieta.pandora.database.Database
import com.monolieta.pandora.manager.PasswordManager
import com.monolieta.pandora.repository.AuthenticationRepository
import com.monolieta.pandora.repository.GameRepository
import com.monolieta.pandora.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {

    private const val url = "https://5ddki56eqe.execute-api.us-east-1.amazonaws.com"

    @Provides
    fun provideAuthenticationRepository(database: Database): AuthenticationRepository {
        return AuthenticationRepository(
            repository = UserRepository(
                queries = database.userQueries,
            ), keyStoreManager = PasswordManager()
        )
    }

    @Provides
    fun provideGameRepository(database: Database): GameRepository {
        return GameRepository(
            queries = database.gameQueries
        )
    }
}