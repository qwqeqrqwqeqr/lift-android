package com.gradation.lift.database.di

import com.gradation.lift.database.dao.BadgeDao
import com.gradation.lift.database.dao.HistoryDao
import com.gradation.lift.database.dao.RoutineDao
import com.gradation.lift.database.dao.UserBadgeDao
import com.gradation.lift.database.dao.WorkCategoryDao
import com.gradation.lift.database.dao.WorkDao
import com.gradation.lift.database.dao.WorkPartDao
import com.gradation.lift.database.datasource.badge.BadgeDataSource
import com.gradation.lift.database.datasource.badge.DefaultBadgeDataSource
import com.gradation.lift.database.datasource.history.DefaultHistoryDataSource
import com.gradation.lift.database.datasource.history.HistoryDataSource
import com.gradation.lift.database.datasource.routine.DefaultRoutineDataSource
import com.gradation.lift.database.datasource.routine.RoutineDataSource
import com.gradation.lift.database.datasource.userBadge.DefaultUserBadgeDataSource
import com.gradation.lift.database.datasource.userBadge.UserBadgeDataSource
import com.gradation.lift.database.datasource.work.DefaultWorkDataSource
import com.gradation.lift.database.datasource.work.WorkDataSource
import com.gradation.lift.database.datasource.workCategory.DefaultWorkCategoryDataSource
import com.gradation.lift.database.datasource.workCategory.WorkCategoryDataSource
import com.gradation.lift.database.datasource.workPart.DefaultWorkPartDataSource
import com.gradation.lift.database.datasource.workPart.WorkPartDataSource
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
    fun provideUserBadgeDataSource(
        userBadgeDao: UserBadgeDao,
    ): UserBadgeDataSource = DefaultUserBadgeDataSource(userBadgeDao)


    @Provides
    fun provideHistoryDataSource(
        historyDao: HistoryDao,
    ): HistoryDataSource = DefaultHistoryDataSource(historyDao)


    @Provides
    fun provideRoutineDataSource(
        routineDao: RoutineDao,
    ): RoutineDataSource = DefaultRoutineDataSource(routineDao)


    @Provides
    fun provideWorkCategoryDataSource(
        workCategoryDao: WorkCategoryDao,
    ): WorkCategoryDataSource = DefaultWorkCategoryDataSource(workCategoryDao)


    @Provides
    fun provideWorkPartDataSource(
        workPartDao: WorkPartDao,
    ): WorkPartDataSource = DefaultWorkPartDataSource(workPartDao)


    @Provides
    fun provideWorkDataSource(
        workDao: WorkDao,
    ): WorkDataSource = DefaultWorkDataSource(workDao)


}
