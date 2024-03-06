package com.gradation.lift.database.di

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gradation.lift.database.dao.BadgeDao
import com.gradation.lift.database.dao.HistoryDao
import com.gradation.lift.database.dao.RoutineDao
import com.gradation.lift.database.dao.UserBadgeDao
import com.gradation.lift.database.dao.WorkCategoryDao
import com.gradation.lift.database.dao.WorkDao
import com.gradation.lift.database.dao.WorkPartDao
import com.gradation.lift.database.entity.badge.BadgeEntity
import com.gradation.lift.database.entity.history.HistoryEntity
import com.gradation.lift.database.entity.history.HistoryRoutineEntity
import com.gradation.lift.database.entity.routine.RoutineEntity
import com.gradation.lift.database.entity.routine.RoutineSetRoutineEntity
import com.gradation.lift.database.entity.userBadge.UserBadgeEntity
import com.gradation.lift.database.entity.work.WorkEntity
import com.gradation.lift.database.entity.work.WorkRoutineEntity
import com.gradation.lift.database.entity.workCategory.WorkCategoryEntity
import com.gradation.lift.database.entity.workCategory.WorkPartEntity
import com.gradation.lift.database.util.CheckedWorkSetInfoListTypeConverter
import com.gradation.lift.database.util.IntListTypeConverter
import com.gradation.lift.database.util.LocalDateTimeTypeConverter
import com.gradation.lift.database.util.LocalDateTypeConverter
import com.gradation.lift.database.util.LocalTimeTypeConverter
import com.gradation.lift.database.util.StringListTypeConverter
import com.gradation.lift.database.util.WorkSetListTypeConverter

@Database(
    entities = [
        BadgeEntity::class,
        UserBadgeEntity::class,
        HistoryEntity::class,
        HistoryRoutineEntity::class,
        RoutineEntity::class,
        RoutineSetRoutineEntity::class,
        WorkEntity::class,
        WorkRoutineEntity::class,
        WorkCategoryEntity::class,
        WorkPartEntity::class,
    ],
    version = 1,
    exportSchema = false,
)
@TypeConverters(
    value = [
        IntListTypeConverter::class,
        StringListTypeConverter::class,
        CheckedWorkSetInfoListTypeConverter::class,
        WorkSetListTypeConverter::class,
        LocalTimeTypeConverter::class,
        LocalDateTimeTypeConverter::class,
        LocalDateTypeConverter::class
    ]
)
abstract class LiftDatabase : RoomDatabase() {
    abstract fun badgeDao(): BadgeDao
    abstract fun userBadgeDao(): UserBadgeDao
    abstract fun historyDao(): HistoryDao
    abstract fun routineSetRoutineDao(): RoutineDao
    abstract fun workCategoryDao(): WorkCategoryDao
    abstract fun workDao(): WorkDao
    abstract fun workPartDao(): WorkPartDao


}


