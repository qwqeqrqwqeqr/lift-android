package com.gradation.database.di

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gradation.database.dao.RoutineSetDao
import com.gradation.database.dao.WorkCategoryDao
import com.gradation.database.model.RoutineSetEntity
import com.gradation.database.model.WorkCategoryEntity
import com.gradation.database.util.ImageTypeConverter
import com.gradation.database.util.WeekTypeConverter
import com.gradation.database.util.WorkPartTypeConverter
import com.gradation.database.util.RoutineListTypeConverter

@Database(
    entities = [
        WorkCategoryEntity::class,
        RoutineSetEntity::class,
    ],
    version = 1,
    exportSchema = false,
)
@TypeConverters(
    WeekTypeConverter::class,
    WorkPartTypeConverter::class,
    ImageTypeConverter::class,
    RoutineListTypeConverter::class
)
abstract class LiftDatabase : RoomDatabase() {
    abstract fun routineSetDao(): RoutineSetDao
    abstract fun workCategoryDao(): WorkCategoryDao
}


