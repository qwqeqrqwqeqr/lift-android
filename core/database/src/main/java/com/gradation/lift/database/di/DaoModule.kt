package com.gradation.lift.database.di

import com.gradation.lift.database.dao.RoutineSetDao
import com.gradation.lift.database.dao.WorkCategoryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object DaoModule {

    @Provides
    fun providesWorkCategoryDao(
        database: LiftDatabase,
    ): WorkCategoryDao = database.workCategoryDao()

    @Provides
    fun providesRoutineSetDao(
        database: LiftDatabase,
    ): RoutineSetDao = database.routineSetDao()
}