package com.gradation.lift.database.datasource.database

import com.gradation.lift.database.LiftDatabase
import javax.inject.Inject

class DefaultDatabaseSettingDataSource @Inject constructor(
    private val database: LiftDatabase,
) : DatabaseSettingDataSource {

    override fun clearDatabase() {
        database.clearAllTables()
    }

}