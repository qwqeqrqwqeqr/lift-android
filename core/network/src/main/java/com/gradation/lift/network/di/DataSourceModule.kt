package com.gradation.lift.network.di

import android.content.Context
import com.gradation.lift.network.datasource.*
import com.gradation.lift.network.handler.NetworkResultHandler
import com.gradation.lift.network.service.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Provides
    fun provideWorkDataSource(
        workService: WorkService,
        networkResultHandler: NetworkResultHandler,
    ): WorkDataSource = DefaultWorkDataSource(workService, networkResultHandler)


    @Provides
    fun provideRoutineDataSource(
        routineService: RoutineService,
        networkResultHandler: NetworkResultHandler,
    ): RoutineDataSource = DefaultRoutineDataSource(routineService, networkResultHandler)


    @Provides
    fun provideAuthDataSource(
        authService: AuthService,
        networkResultHandler: NetworkResultHandler,
        @ApplicationContext context: Context,
    ): AuthDataSource = DefaultAuthDataSource(authService, networkResultHandler, context)


    @Provides
    fun provideCheckerDataSource(
        checkerService: CheckerService,
        networkResultHandler: NetworkResultHandler,
    ): CheckerDataSource = DefaultCheckerDataSource(checkerService, networkResultHandler)


    @Provides
    fun providePictureDataSource(
        pictureService: PictureService,
        networkResultHandler: NetworkResultHandler,
    ): PictureDataSource = DefaultPictureDataSource(pictureService, networkResultHandler)

    @Provides
    fun provideUserDataSource(
        userService: UserService,
        networkResultHandler: NetworkResultHandler,
    ): UserDataSource = DefaultUserDataSource(userService, networkResultHandler)

    @Provides
    fun provideHistoryDataSource(
        historyService: HistoryService,
        networkResultHandler: NetworkResultHandler,
    ): HistoryDataSource = DefaultHistoryDataSource(historyService, networkResultHandler)
}