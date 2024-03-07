package com.gradation.lift.database.di

import com.gradation.lift.database.dao.BadgeDao
import com.gradation.lift.database.dao.HistoryDao
import com.gradation.lift.database.dao.RoutineDao
import com.gradation.lift.database.dao.UserBadgeDao
import com.gradation.lift.database.dao.WorkCategoryDao
import com.gradation.lift.database.dao.WorkDao
import com.gradation.lift.database.dao.WorkPartDao
import com.gradation.lift.database.datasource.badge.BadgeLocalDataSource
import com.gradation.lift.database.datasource.badge.DefaultBadgeLocalDataSource
import com.gradation.lift.database.datasource.history.DefaultHistoryLocalDataSource
import com.gradation.lift.database.datasource.history.HistoryLocalDataSource
import com.gradation.lift.database.datasource.routine.DefaultRoutineLocalDataSource
import com.gradation.lift.database.datasource.routine.RoutineLocalDataSource
import com.gradation.lift.database.datasource.userBadge.DefaultUserBadgeLocalDataSource
import com.gradation.lift.database.datasource.userBadge.UserBadgeLocalDataSource
import com.gradation.lift.database.datasource.work.DefaultWorkLocalDataSource
import com.gradation.lift.database.datasource.work.WorkLocalDataSource
import com.gradation.lift.database.datasource.workCategory.DefaultWorkCategoryLocalDataSource
import com.gradation.lift.database.datasource.workCategory.WorkCategoryLocalDataSource
import com.gradation.lift.database.datasource.workPart.DefaultWorkPartLocalDataSource
import com.gradation.lift.database.datasource.workPart.WorkPartLocalDataSource
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
    ): BadgeLocalDataSource = DefaultBadgeLocalDataSource(badgeDao)


    @Provides
    fun provideUserBadgeDataSource(
        userBadgeDao: UserBadgeDao,
    ): UserBadgeLocalDataSource = DefaultUserBadgeLocalDataSource(userBadgeDao)


    @Provides
    fun provideHistoryDataSource(
        historyDao: HistoryDao,
    ): HistoryLocalDataSource = DefaultHistoryLocalDataSource(historyDao)


    @Provides
    fun provideRoutineDataSource(
        routineDao: RoutineDao,
    ): RoutineLocalDataSource = DefaultRoutineLocalDataSource(routineDao)


    @Provides
    fun provideWorkCategoryDataSource(
        workCategoryDao: WorkCategoryDao,
    ): WorkCategoryLocalDataSource = DefaultWorkCategoryLocalDataSource(workCategoryDao)


    @Provides
    fun provideWorkPartDataSource(
        workPartDao: WorkPartDao,
    ): WorkPartLocalDataSource = DefaultWorkPartLocalDataSource(workPartDao)


    @Provides
    fun provideWorkDataSource(
        workDao: WorkDao,
    ): WorkLocalDataSource = DefaultWorkLocalDataSource(workDao)


}
