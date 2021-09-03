package com.monolieta.pandora.android.module

import com.monolieta.pandora.database.Database
import com.monolieta.pandora.repository.GameRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {

    private const val url = "https://5ddki56eqe.execute-api.us-east-1.amazonaws.com"

    @Provides
    fun provideGameRepository(database: Database): GameRepository {
        return GameRepository(
            url,
            repository = database.gameQueries
        )
    }
}