package com.gradation.lift.data.di

import com.gradation.lift.data.repository.DefaultRoutineRepository
import com.gradation.lift.data.repository.DefaultWorkRepository
import com.gradation.lift.datastore.datasource.DataStoreDataSource
import com.gradation.lift.domain.repository.RoutineRepository
import com.gradation.lift.domain.repository.WorkRepository
import com.gradation.lift.network.datasource.RoutineDataSource
import com.gradation.lift.network.datasource.WorkDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun provideWorkRepository(
        workDataSource: WorkDataSource,
    ): WorkRepository = DefaultWorkRepository(workDataSource)


    @Provides
    fun provideRoutineRepository(
        routineDataSource: RoutineDataSource,
        dataStoreDataSource: DataStoreDataSource
    ): RoutineRepository = DefaultRoutineRepository(routineDataSource,dataStoreDataSource)

}