package com.monolieta.pandora

import com.squareup.sqldelight.db.SqlDriver

expect class DriverFactory {
    fun create(): SqlDriver
}