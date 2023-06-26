package com.gradation.lift.data.di

import com.gradation.lift.data.repository.DefaultAuthRepository
import com.gradation.lift.data.repository.DefaultRoutineRepository
import com.gradation.lift.data.repository.DefaultWorkRepository
import com.gradation.lift.data.utils.RefreshManager
import com.gradation.lift.datastore.datasource.UserDataStoreDataSource
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
        userDataStoreDataSource: UserDataStoreDataSource,
    ): RoutineRepository = DefaultRoutineRepository(routineDataSource,refreshManager ,userDataStoreDataSource)


    @ViewModelScoped
    @Provides
    fun provideAuthRepository(
        authDataSource: AuthDataSource,
        userDataStoreDataSource: UserDataStoreDataSource,
    ): AuthRepository = DefaultAuthRepository(authDataSource, userDataStoreDataSource)

}