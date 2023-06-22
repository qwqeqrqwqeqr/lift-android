package com.gradation.lift.data.di

import com.gradation.lift.data.repository.DefaultAuthRepository
import com.gradation.lift.data.repository.DefaultRoutineRepository
import com.gradation.lift.data.repository.DefaultWorkRepository
import com.gradation.lift.data.utils.RefreshManager
import com.gradation.lift.datastore.datasource.DataStoreDataSource
import com.gradation.lift.domain.repository.AuthRepository
import com.gradation.lift.domain.repository.RoutineRepository
import com.gradation.lift.domain.repository.WorkRepository
import com.gradation.lift.network.datasource.AuthDataSource
import com.gradation.lift.network.datasource.RoutineDataSource
import com.gradation.lift.network.datasource.WorkDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {


    @ViewModelScoped
    @Provides
    fun provideWorkRepository(
        workDataSource: WorkDataSource,
        refreshManager: RefreshManager
    ): WorkRepository = DefaultWorkRepository(workDataSource,refreshManager)


    @ViewModelScoped
    @Provides
    fun provideRoutineRepository(
        routineDataSource: RoutineDataSource,
        refreshManager: RefreshManager,
        dataStoreDataSource: DataStoreDataSource,
    ): RoutineRepository = DefaultRoutineRepository(routineDataSource,refreshManager ,dataStoreDataSource)


    @ViewModelScoped
    @Provides
    fun provideAuthRepository(
        authDataSource: AuthDataSource,
        dataStoreDataSource: DataStoreDataSource,
    ): AuthRepository = DefaultAuthRepository(authDataSource, dataStoreDataSource)

}