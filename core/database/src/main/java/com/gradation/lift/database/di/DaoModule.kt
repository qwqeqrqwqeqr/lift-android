package com.gradation.lift.database.di

import com.gradation.lift.database.dao.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object DaoModule {

    @Provides
    fun provideWorkCategoryDao(
        database: LiftDatabase,
    ): WorkCategoryDao = database.workCategoryDao()

    @Provides
    fun provideWorkPartDao(
        database: LiftDatabase,
    ): WorkPartDao = database.workPartDao()

    @Provides
    fun provideRoutineSetPictureDao(
        database: LiftDatabase,
    ): RoutineSetPictureDao = database.routineSetPictureDao()

    @Provides
    fun provideRoutineSetRoutineDao(
        database: LiftDatabase,
    ): RoutineSetRoutineDao = database.routineSetRoutineDao()

    @Provides
    fun provideUserProfilePictureDao(
        database: LiftDatabase,
    ): UserProfilePictureDao = database.userProfilePictureDao()

    @Provides
    fun provideHistoryDao(
        database: LiftDatabase,
    ): HistoryDao = database.historyDao()

    @Provides
    fun provideWorkDao(
        database: LiftDatabase,
    ): WorkDao = database.workDao()

}