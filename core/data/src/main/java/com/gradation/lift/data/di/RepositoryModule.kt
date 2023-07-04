package com.gradation.lift.data.di

import com.gradation.lift.data.repository.*
import com.gradation.lift.data.utils.RefreshManager
import com.gradation.lift.datastore.datasource.UserDataStoreDataSource
import com.gradation.lift.domain.repository.*
import com.gradation.lift.network.datasource.*
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
        refreshManager: RefreshManager,
    ): WorkRepository = DefaultWorkRepository(workDataSource, refreshManager)


    @ViewModelScoped
    @Provides
    fun provideRoutineRepository(
        routineDataSource: RoutineDataSource,
        refreshManager: RefreshManager,
        userDataStoreDataSource: UserDataStoreDataSource,
    ): RoutineRepository =
        DefaultRoutineRepository(routineDataSource, refreshManager, userDataStoreDataSource)


    @ViewModelScoped
    @Provides
    fun provideAuthRepository(
        authDataSource: AuthDataSource,
        userDataStoreDataSource: UserDataStoreDataSource,
    ): AuthRepository = DefaultAuthRepository(authDataSource, userDataStoreDataSource)


    @ViewModelScoped
    @Provides
    fun provideCheckerRepository(
        checkerDataSource: CheckerDataSource,
    ): CheckerRepository = DefaultCheckerRepository(checkerDataSource=checkerDataSource)

    @ViewModelScoped
    @Provides
    fun provideUserRepository(
        userDataSource: UserDataSource,
        refreshManager: RefreshManager,
        userDataStoreDataSource: UserDataStoreDataSource,
    ): UserRepository = DefaultUserRepository(
        userDataSource = userDataSource,
        refreshManager = refreshManager,
        userDataStoreDataSource = userDataStoreDataSource
    )

}