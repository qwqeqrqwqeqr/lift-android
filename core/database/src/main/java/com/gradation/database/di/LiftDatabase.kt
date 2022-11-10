package com.gradation.database.di

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gradation.database.model.RoutineEntity
import com.gradation.database.util.WorkSetListConverter

@Database(
    entities = [
    ],
    version = 1,
    exportSchema = true,
)
@TypeConverters(
    WorkSetListConverter::class,
)
abstract class LiftDatabase : RoomDatabase() {

}