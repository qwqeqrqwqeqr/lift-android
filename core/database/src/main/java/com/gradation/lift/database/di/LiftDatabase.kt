package com.gradation.lift.database.di

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomWarnings
import androidx.room.TypeConverters
import com.gradation.lift.database.dao.*
import com.gradation.lift.database.model.*
import com.gradation.lift.database.model.history.HistoryEntity
import com.gradation.lift.database.model.history.HistoryRoutineEntity
import com.gradation.lift.database.model.picture.RoutineSetPictureEntity
import com.gradation.lift.database.model.picture.UserProfilePictureEntity
import com.gradation.lift.database.model.routine.RoutineEntity
import com.gradation.lift.database.model.routine.RoutineSetRoutineEntity
import com.gradation.lift.database.model.user.UserEntity
import com.gradation.lift.database.model.work.WorkEntity
import com.gradation.lift.database.model.work.WorkRoutineEntity
import com.gradation.lift.database.model.work_category.WorkCategoryEntity
import com.gradation.lift.database.model.work_category.WorkPartEntity
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
        WorkRoutineEntity::class,
        WorkPartEntity::class,
        UserEntity::class,
    ],
    version = 1,
    exportSchema = false,
)
@TypeConverters(
    value = [
        WeekdayTypeConverter::class,
        WorkSetListTypeConverter::class,
        LocalTimeTypeConverter::class,
        LocalDateTimeTypeConverter::class
    ]
)
abstract class LiftDatabase : RoomDatabase() {
    abstract fun workCategoryDao(): WorkCategoryDao
    abstract fun workPartDao(): WorkPartDao
    abstract fun routineSetRoutineDao(): RoutineSetRoutineDao
    abstract fun pictureDao(): PictureDao
    abstract fun historyDao(): HistoryDao
    abstract fun workDao(): WorkDao
    abstract fun userDao(): UserDao

}


