package com.gradation.lift.database.di

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

    fun provideRoutineSetRoutineDao(
        database: LiftDatabase,
    ): RoutineSetRoutineDao = database.routineSetRoutineDao()

    @Provides
    fun providePictureDao(
        database: LiftDatabase,
    ): PictureDao = database.pictureDao()

    @Provides
    @Singleton

    fun provideHistoryDao(
        database: LiftDatabase,
    ): HistoryDao = database.historyDao()

    @Singleton

    @Provides
    fun provideWorkDao(
        database: LiftDatabase,
    ): WorkDao = database.workDao()

    @Provides
    @Singleton

    fun provideUserDao(
        database: LiftDatabase,
    ): UserDao = database.userDao()

}