package com.gradation.lift.data.di

import com.gradation.lift.data.repository.*
import com.gradation.lift.database.dao.WorkDao
import com.gradation.lift.datastore.datasource.SettingDataStoreDataSource
import com.gradation.lift.datastore.datasource.TokenDataStoreDataSource
import com.gradation.lift.domain.repository.*
import com.gradation.lift.network.datasource.*
import com.gradation.lift.network.datasource.auth.AuthDataSource
import com.gradation.lift.network.datasource.checker.CheckerDataSource
import com.gradation.lift.network.datasource.history.HistoryDataSource
import com.gradation.lift.network.datasource.kakao.KakaoDataSource
import com.gradation.lift.network.datasource.naver.NaverDataSource
import com.gradation.lift.network.datasource.picture.PictureDataSource
import com.gradation.lift.network.datasource.routine.RoutineDataSource
import com.gradation.lift.network.datasource.user.UserDataSource
import com.gradation.lift.network.datasource.work.WorkDataSource
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
        workDao: WorkDao
    ): WorkRepository = DefaultWorkRepository(workDataSource = workDataSource,workDao=workDao)


    @ViewModelScoped
    @Provides
    fun provideRoutineRepository(
        routineDataSource: RoutineDataSource,
    ): RoutineRepository =
        DefaultRoutineRepository(routineDataSource = routineDataSource)


    @ViewModelScoped
    @Provides
    fun provideAuthRepository(
        authDataSource: AuthDataSource,
        kakaoDataSource: KakaoDataSource,
        naverDataSource: NaverDataSource,
        tokenDataStoreDataSource: TokenDataStoreDataSource,
    ): AuthRepository = DefaultAuthRepository(
        authDataSource = authDataSource,
        kakaoDataSource = kakaoDataSource,
        naverDataSource= naverDataSource,
        tokenDataStoreDataSource = tokenDataStoreDataSource
    )


    @ViewModelScoped
    @Provides
    fun provideCheckerRepository(
        checkerDataSource: CheckerDataSource,
    ): CheckerRepository = DefaultCheckerRepository(checkerDataSource = checkerDataSource)


    @ViewModelScoped
    @Provides
    fun provideSettingRepository(
        settingDataStoreDataSource: SettingDataStoreDataSource,
    ): SettingRepository =
        DefaultSettingRepository(settingDataStoreDataSource = settingDataStoreDataSource)

    @ViewModelScoped
    @Provides
    fun provideHistoryRepository(
        historyDataSource: HistoryDataSource,
    ): HistoryRepository = DefaultHistoryRepository(historyDataSource = historyDataSource)


    @ViewModelScoped
    @Provides
    fun providePictureRepository(
        pictureDataSource: PictureDataSource,
    ): PictureRepository = DefaultPictureRepository(pictureDataSource = pictureDataSource)


    @ViewModelScoped
    @Provides
    fun provideUserRepository(
        userDataSource: UserDataSource,
    ): UserRepository = DefaultUserRepository(
        userDataSource = userDataSource,
    )

}