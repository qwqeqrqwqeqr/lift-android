package com.gradation.lift.data.di

import com.gradation.lift.data.repository.*
import com.gradation.lift.datastore.datasource.SettingDataStoreDataSource
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
    ): WorkRepository = DefaultWorkRepository(workDataSource= workDataSource)


    @ViewModelScoped
    @Provides
    fun provideRoutineRepository(
        routineDataSource: RoutineDataSource,
    ): RoutineRepository =
        DefaultRoutineRepository(routineDataSource= routineDataSource)


    @ViewModelScoped
    @Provides
    fun provideAuthRepository(
        authDataSource: AuthDataSource,
        tokenDataStoreDataSource: TokenDataStoreDataSource,
    ): AuthRepository = DefaultAuthRepository(authDataSource= authDataSource,tokenDataStoreDataSource= tokenDataStoreDataSource)




    @ViewModelScoped
    @Provides
    fun provideCheckerRepository(
        checkerDataSource: CheckerDataSource,
    ): CheckerRepository = DefaultCheckerRepository(checkerDataSource=checkerDataSource)


    @ViewModelScoped
    @Provides
    fun provideSettingRepository(
        settingDataStoreDataSource: SettingDataStoreDataSource,
    ): SettingRepository = DefaultSettingRepository(settingDataStoreDataSource=settingDataStoreDataSource)


    @ViewModelScoped
    @Provides
    fun provideUserRepository(
        userDataSource: UserDataSource,
    ): UserRepository = DefaultUserRepository(
        userDataSource = userDataSource,
    )

}