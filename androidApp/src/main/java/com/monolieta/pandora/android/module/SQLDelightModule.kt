package com.monolieta.pandora.android.module

import android.content.Context
import com.monolieta.pandora.database.Database
import com.monolieta.pandora.database.DriverFactory
import com.monolieta.pandora.database.Game
import com.monolieta.pandora.database.listOfStringsAdapter
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SQLDelightModule {

    @Singleton
    @Provides
    fun provideDriver(@ApplicationContext context: Context): SqlDriver =
        DriverFactory(context).create()

    @Singleton
    @Provides
    fun provideSQLDelightDatabase(driver: SqlDriver) = Database(
        driver, Game.Adapter(
            genreAdapter = listOfStringsAdapter,
            platformsAdapter = listOfStringsAdapter
        )
    )
}