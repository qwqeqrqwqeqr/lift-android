package com.gradation.lift.database.di

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gradation.lift.database.dao.RoutineSetDao
import com.gradation.lift.database.dao.WorkCategoryDao
import com.gradation.lift.database.util.*

@Database(
    entities = [

    ],
    version = 2,
    exportSchema = true,
)
@TypeConverters(value = [
    WeekdayTypeConverter::class,
    IntListTypeConverter::class,
    FloatListTypeConverter::class,
    StringListTypeConverter::class,
    WorkSetListTypeConverter::class,
    LocalTimeTypeConverter::class,
    LocalDateTypeConverter::class
])
abstract class LiftDatabase : RoomDatabase() {
    abstract fun routineSetDao(): RoutineSetDao
    abstract fun workCategoryDao(): WorkCategoryDao
}


