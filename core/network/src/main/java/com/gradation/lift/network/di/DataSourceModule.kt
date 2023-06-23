package com.gradation.lift.network.di

import com.gradation.lift.network.handler.NetworkResultHandler
import com.gradation.lift.network.datasource.*
import com.gradation.lift.network.service.AuthService
import com.gradation.lift.network.service.RoutineService
import com.gradation.lift.network.service.WorkService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent



@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Provides
    fun provideWorkDataSource(
        workService: WorkService,
        networkResultHandler: NetworkResultHandler,
    ): WorkDataSource = DefaultWorkDataSource(workService,networkResultHandler)


    @Provides
    fun provideRoutineDataSource(
        routineService: RoutineService,
        networkResultHandler: NetworkResultHandler,
    ): RoutineDataSource = DefaultRoutineDataSource(routineService,networkResultHandler)


    @Provides
    fun provideAuthDataSource(
        authService: AuthService,
        networkResultHandler: NetworkResultHandler,
    ): AuthDataSource = DefaultAuthDataSource(authService,networkResultHandler)



}