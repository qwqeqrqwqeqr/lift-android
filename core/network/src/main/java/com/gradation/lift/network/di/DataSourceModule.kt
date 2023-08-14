package com.gradation.lift.network.di

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.gradation.lift.network.datasource.*
import com.gradation.lift.network.datasource.auth.AuthDataSource
import com.gradation.lift.network.datasource.auth.DefaultAuthDataSource
import com.gradation.lift.network.datasource.checker.CheckerDataSource
import com.gradation.lift.network.datasource.checker.DefaultCheckerDataSource
import com.gradation.lift.network.datasource.firebase.DefaultFirebaseDataSource
import com.gradation.lift.network.datasource.firebase.FirebaseDataSource
import com.gradation.lift.network.datasource.history.DefaultHistoryDataSource
import com.gradation.lift.network.datasource.history.HistoryDataSource
import com.gradation.lift.network.datasource.kakao.DefaultKakaoDataSource
import com.gradation.lift.network.datasource.kakao.KakaoDataSource
import com.gradation.lift.network.datasource.picture.DefaultPictureDataSource
import com.gradation.lift.network.datasource.picture.PictureDataSource
import com.gradation.lift.network.datasource.routine.DefaultRoutineDataSource
import com.gradation.lift.network.datasource.routine.RoutineDataSource
import com.gradation.lift.network.datasource.user.DefaultUserDataSource
import com.gradation.lift.network.datasource.user.UserDataSource
import com.gradation.lift.network.datasource.work.DefaultWorkDataSource
import com.gradation.lift.network.datasource.work.WorkDataSource
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
    ): AuthDataSource = DefaultAuthDataSource(authService, networkResultHandler)


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

    @Provides
    fun provideKakaoDataSource(
        @ApplicationContext context: Context,
    ): KakaoDataSource = DefaultKakaoDataSource(context)

    @Provides
    fun provideFirebaseDataSource(
        firebaseAuth: FirebaseAuth
    ): FirebaseDataSource = DefaultFirebaseDataSource(firebaseAuth)
}