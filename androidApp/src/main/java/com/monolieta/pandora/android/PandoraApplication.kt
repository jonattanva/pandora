package com.monolieta.pandora.android

import android.app.Application
import com.monolieta.pandora.database.Database
import com.monolieta.pandora.database.DriverFactory
import com.monolieta.pandora.database.Game
import com.monolieta.pandora.database.listOfStringsAdapter
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Singleton

@HiltAndroidApp
class PandoraApplication : Application() {

    @Singleton
    fun a() {
        val driver = DriverFactory(this).create()
        Database(
            driver = driver, Game.Adapter(
                genreAdapter = listOfStringsAdapter,
                platformsAdapter = listOfStringsAdapter
            )
        )
    }

}