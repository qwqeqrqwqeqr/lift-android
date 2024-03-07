package com.gradation.lift.database.di

import com.gradation.lift.database.LiftDatabase
import com.gradation.lift.database.dao.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DaoModule {


    @Provides
    @Singleton
    fun provideBadgeDao(
        database: LiftDatabase,
    ): BadgeDao = database.badgeDao()

    @Provides
    @Singleton
    fun provideUserBadgeDao(
        database: LiftDatabase,
    ): UserBadgeDao = database.userBadgeDao()

    @Provides
    @Singleton
    fun provideHistoryDao(
        database: LiftDatabase,
    ): HistoryDao = database.historyDao()

    @Provides
    @Singleton
    fun provideRoutineSetRoutineDao(
        database: LiftDatabase,
    ): RoutineDao = database.routineSetRoutineDao()

    @Provides
    @Singleton
    fun provideWorkCategoryDao(
        database: LiftDatabase,
    ): WorkCategoryDao = database.workCategoryDao()

    @Provides
    @Singleton
    fun provideWorkPartDao(
        database: LiftDatabase,
    ): WorkPartDao = database.workPartDao()

    @Provides
    @Singleton
    fun provideWorkDao(
        database: LiftDatabase,
    ): WorkDao = database.workDao()
}