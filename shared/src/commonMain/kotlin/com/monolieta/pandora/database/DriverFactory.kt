package com.monolieta.pandora.database

import com.squareup.sqldelight.ColumnAdapter
import com.squareup.sqldelight.db.SqlDriver

expect class DriverFactory {
    fun create(): SqlDriver
}

val listOfStringsAdapter = object : ColumnAdapter<List<String>, String> {

    override fun decode(databaseValue: String): List<String> = if (databaseValue.isNotEmpty()) {
        databaseValue.split(",")
    } else emptyList()

    override fun encode(value: List<String>): String = value.joinToString(",")
}