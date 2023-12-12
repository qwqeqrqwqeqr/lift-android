package com.gradation.lift.database.di

import com.gradation.lift.database.dao.BadgeDao
import com.gradation.lift.database.dao.HistoryDao
import com.gradation.lift.database.dao.NoticeDao
import com.gradation.lift.database.dao.PictureDao
import com.gradation.lift.database.dao.RoutineDao
import com.gradation.lift.database.dao.UserDao
import com.gradation.lift.database.dao.WorkCategoryDao
import com.gradation.lift.database.dao.WorkDao
import com.gradation.lift.database.datasource.badge.BadgeDataSource
import com.gradation.lift.database.datasource.badge.DefaultBadgeDataSource
import com.gradation.lift.database.datasource.history.DefaultHistoryDataSource
import com.gradation.lift.database.datasource.history.HistoryDataSource
import com.gradation.lift.database.datasource.notice.DefaultNoticeDataSource
import com.gradation.lift.database.datasource.notice.NoticeDataSource
import com.gradation.lift.database.datasource.picture.DefaultPictureDataSource
import com.gradation.lift.database.datasource.picture.PictureDataSource
import com.gradation.lift.database.datasource.routine.DefaultRoutineDataSource
import com.gradation.lift.database.datasource.routine.RoutineDataSource
import com.gradation.lift.database.datasource.user.DefaultUserDataSource
import com.gradation.lift.database.datasource.user.UserDataSource
import com.gradation.lift.database.datasource.work.DefaultWorkDataSource
import com.gradation.lift.database.datasource.work.WorkDataSource
import com.gradation.lift.database.datasource.work_category.DefaultWorkCategoryDataSource
import com.gradation.lift.database.datasource.work_category.WorkCategoryDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Provides
    fun provideBadgeDataSource(
        badgeDao: BadgeDao,
    ): BadgeDataSource = DefaultBadgeDataSource(badgeDao)


    @Provides
    fun provideHistoryDataSource(
        historyDao: HistoryDao,
    ): HistoryDataSource = DefaultHistoryDataSource(historyDao)


    @Provides
    fun provideNoticeDataSource(
        noticeDao: NoticeDao
    ): NoticeDataSource = DefaultNoticeDataSource(noticeDao)

    @Provides
    fun providePictureDataSource(
        pictureDao: PictureDao,
    ): PictureDataSource = DefaultPictureDataSource(pictureDao)


    @Provides
    fun provideRoutineDataSource(
        routineDao: RoutineDao,
    ): RoutineDataSource = DefaultRoutineDataSource(routineDao)


    @Provides
    fun provideUserDataSource(
        userDao: UserDao,
    ): UserDataSource = DefaultUserDataSource(userDao)


    @Provides
    fun provideWorkCategoryDataSource(
        workCategoryDao: WorkCategoryDao,
    ): WorkCategoryDataSource = DefaultWorkCategoryDataSource(workCategoryDao)

    @Provides
    fun provideWorkDataSource(
        workDao: WorkDao,
    ): WorkDataSource = DefaultWorkDataSource(workDao)


}
