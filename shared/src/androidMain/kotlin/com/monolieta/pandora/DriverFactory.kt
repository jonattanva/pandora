package com.monolieta.pandora

import android.content.Context
import com.monolieta.pandora.database.Database
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DriverFactory(private val context: Context) {
    actual fun create(): SqlDriver {
        return AndroidSqliteDriver(Database.Schema, context, "pandora.db")
    }
}