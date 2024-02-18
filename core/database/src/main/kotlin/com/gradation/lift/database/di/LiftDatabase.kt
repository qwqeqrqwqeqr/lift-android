package com.gradation.lift.database.di

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomWarnings
import androidx.room.TypeConverters
import com.gradation.lift.database.dao.*
import com.gradation.lift.database.entity.*
import com.gradation.lift.database.entity.badge.BadgeEntity
import com.gradation.lift.database.entity.badge.UserBadgeEntity
import com.gradation.lift.database.entity.history.HistoryEntity
import com.gradation.lift.database.entity.history.HistoryRoutineEntity
import com.gradation.lift.database.entity.notice.NoticeEntity
import com.gradation.lift.database.entity.picture.RoutineSetPictureEntity
import com.gradation.lift.database.entity.picture.UserProfilePictureEntity
import com.gradation.lift.database.entity.routine.RoutineEntity
import com.gradation.lift.database.entity.routine.RoutineSetRoutineEntity
import com.gradation.lift.database.entity.user.UserEntity
import com.gradation.lift.database.entity.work.WorkEntity
import com.gradation.lift.database.entity.work.WorkRoutineEntity
import com.gradation.lift.database.entity.work_category.WorkCategoryEntity
import com.gradation.lift.database.entity.work_category.WorkPartEntity
import com.gradation.lift.database.util.*

@SuppressWarnings(RoomWarnings.PRIMARY_KEY_FROM_EMBEDDED_IS_DROPPED)
@Database(
    entities = [
        BadgeEntity::class,
        UserBadgeEntity::class,
        HistoryEntity::class,
        HistoryRoutineEntity::class,
        NoticeEntity::class,
        RoutineSetPictureEntity::class,
        UserProfilePictureEntity::class,
        RoutineEntity::class,
        RoutineSetRoutineEntity::class,
        UserEntity::class,
        WorkEntity::class,
        WorkRoutineEntity::class,
        WorkCategoryEntity::class,
        WorkPartEntity::class,
    ],
    version = 4,
    exportSchema = true,
)
@TypeConverters(
    value = [
        IntListTypeConverter::class,
        StringListTypeConverter::class,
        WorkSetListTypeConverter::class,
        LocalTimeTypeConverter::class,
        GenderTypeConverter::class,
        UnitOfWeightTypeConverter::class,
        LocalDateTimeTypeConverter::class,
        LocalDateTypeConverter::class
    ]
)
abstract class LiftDatabase : RoomDatabase() {
    abstract fun badgeDao(): BadgeDao
    abstract fun historyDao(): HistoryDao
    abstract fun noticeDao(): NoticeDao
    abstract fun pictureDao(): PictureDao
    abstract fun routineSetRoutineDao(): RoutineDao
    abstract fun userDao(): UserDao
    abstract fun workCategoryDao(): WorkCategoryDao
    abstract fun workDao(): WorkDao
    abstract fun workPartDao(): WorkPartDao


}


