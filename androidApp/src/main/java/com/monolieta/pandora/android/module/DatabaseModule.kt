package com.monolieta.pandora.android.module

import android.content.Context
import com.monolieta.pandora.DriverFactory
import com.monolieta.pandora.database.Database
import com.monolieta.pandora.listOfStringAdapter
import com.pandora.database.Game
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDriver(@ApplicationContext context: Context) = DriverFactory(context).create()

    @Singleton
    @Provides
    fun provideDatabase(driver: SqlDriver) = Database(
        driver = driver, Game.Adapter(
            screenshotsAdapter = listOfStringAdapter
        )
    )
}