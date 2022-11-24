package com.gradation.lift.database.di

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gradation.lift.database.dao.RoutineSetDao
import com.gradation.lift.database.dao.WorkCategoryDao
import com.gradation.lift.database.model.RoutineSetEntity
import com.gradation.lift.database.model.WorkCategoryEntity
import com.gradation.lift.database.util.ImageTypeConverter
import com.gradation.lift.database.util.WeekTypeConverter
import com.gradation.lift.database.util.WorkPartTypeConverter
import com.gradation.lift.database.util.RoutineListTypeConverter

@Database(
    entities = [
        WorkCategoryEntity::class,
        RoutineSetEntity::class,
    ],
    version = 1,
    exportSchema = true,
)
@TypeConverters(value = [RoutineListTypeConverter::class])
abstract class LiftDatabase : RoomDatabase() {
    abstract fun routineSetDao(): RoutineSetDao
    abstract fun workCategoryDao(): WorkCategoryDao
}


