package com.gradation.lift.data.di

import com.gradation.lift.data.repository.*
import com.gradation.lift.datastore.datasource.TokenDataStoreDataSource
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
    ): WorkRepository = DefaultWorkRepository(workDataSource)


    @ViewModelScoped
    @Provides
    fun provideRoutineRepository(
        routineDataSource: RoutineDataSource,
        tokenDataStoreDataSource: TokenDataStoreDataSource,
    ): RoutineRepository =
        DefaultRoutineRepository(routineDataSource, tokenDataStoreDataSource)


    @ViewModelScoped
    @Provides
    fun provideAuthRepository(
        authDataSource: AuthDataSource,
        tokenDataStoreDataSource: TokenDataStoreDataSource,
    ): AuthRepository = DefaultAuthRepository(authDataSource, tokenDataStoreDataSource)


    @ViewModelScoped
    @Provides
    fun provideCheckerRepository(
        checkerDataSource: CheckerDataSource,
    ): CheckerRepository = DefaultCheckerRepository(checkerDataSource=checkerDataSource)

    @ViewModelScoped
    @Provides
    fun provideUserRepository(
        userDataSource: UserDataSource,
        tokenDataStoreDataSource: TokenDataStoreDataSource,
    ): UserRepository = DefaultUserRepository(
        userDataSource = userDataSource,
        tokenDataStoreDataSource = tokenDataStoreDataSource
    )

}