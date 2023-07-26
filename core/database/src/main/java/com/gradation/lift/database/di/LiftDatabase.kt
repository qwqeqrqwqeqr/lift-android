package com.gradation.lift.database.di

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomWarnings
import androidx.room.TypeConverters
import com.gradation.lift.database.dao.*
import com.gradation.lift.database.model.*
import com.gradation.lift.database.util.*
@SuppressWarnings(RoomWarnings.PRIMARY_KEY_FROM_EMBEDDED_IS_DROPPED)
@Database(
    entities = [
        HistoryEntity::class,
        HistoryRoutineEntity::class,
        RoutineEntity::class,
        RoutineSetPictureEntity::class,
        RoutineSetRoutineEntity::class,
        UserProfilePictureEntity::class,
        WorkCategoryEntity::class,
        WorkEntity::class,
        WorkPartEntity::class,
        WorkRoutineEntity::class,
    ],
    version = 1,
    exportSchema = false,
)
@TypeConverters(
    value = [
        WeekdayTypeConverter::class,
        IntListTypeConverter::class,
        FloatListTypeConverter::class,
        StringListTypeConverter::class,
        WorkSetListTypeConverter::class,
        LocalTimeTypeConverter::class,
        LocalDateTypeConverter::class
    ]
)
abstract class LiftDatabase : RoomDatabase() {
    abstract fun workCategoryDao(): WorkCategoryDao
    abstract fun workPartDao(): WorkPartDao

    abstract fun routineSetPictureDao(): RoutineSetPictureDao
    abstract fun routineSetRoutineDao(): RoutineSetRoutineDao
    abstract fun userProfilePictureDao(): UserProfilePictureDao

    abstract fun historyDao(): HistoryDao
    abstract fun workDao(): WorkDao

}


