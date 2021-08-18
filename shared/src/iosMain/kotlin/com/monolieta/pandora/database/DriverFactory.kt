package com.monolieta.pandora.database

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class DriverFactory {
    actual fun create(): SqlDriver {
        return NativeSqliteDriver(Database.Schema, "pandora.db")
    }
}
