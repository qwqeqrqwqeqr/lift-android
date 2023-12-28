package com.gradation.lift.data.di

import com.gradation.lift.data.repository.*
import com.gradation.lift.database.dao.WorkDao
import com.gradation.lift.datastore.datasource.SettingDataStoreDataSource
import com.gradation.lift.datastore.datasource.TokenDataStoreDataSource
import com.gradation.lift.domain.repository.*
import com.gradation.lift.network.datasource.*
import com.gradation.lift.network.datasource.auth.AuthDataSource
import com.gradation.lift.network.datasource.badge.BadgeDataSource
import com.gradation.lift.network.datasource.checker.CheckerDataSource
import com.gradation.lift.network.datasource.history.HistoryDataSource
import com.gradation.lift.network.datasource.notification.NotificationDataSource
import com.gradation.lift.network.datasource.picture.PictureDataSource
import com.gradation.lift.network.datasource.routine.RoutineDataSource
import com.gradation.lift.network.datasource.terms.TermsDataSource
import com.gradation.lift.network.datasource.user.UserDataSource
import com.gradation.lift.network.datasource.work.WorkDataSource
import com.gradation.lift.oauth.google.GoogleOauthManager
import com.gradation.lift.oauth.kakao.KakaoOauthManager
import com.gradation.lift.oauth.naver.NaverOauthManager
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
        workDao: WorkDao,
    ): WorkRepository = DefaultWorkRepository(workDataSource = workDataSource, workDao = workDao)


    @ViewModelScoped
    @Provides
    fun provideRoutineRepository(
        routineDataSource: RoutineDataSource,
    ): RoutineRepository =
        DefaultRoutineRepository(routineDataSource = routineDataSource)


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

    @ViewModelScoped
    @Provides
    fun provideBadgeRepository(
        badgeDataSource: BadgeDataSource,
    ): BadgeRepository = DefaultBadgeRepository(
        badgeDataSource = badgeDataSource,
    )

    @ViewModelScoped
    @Provides
    fun provideNotificationRepository(
        notificationDataSource: NotificationDataSource,
    ): NotificationRepository = DefaultNotificationRepository(
        notificationDataSource = notificationDataSource,
    )

    @ViewModelScoped
    @Provides
    fun provideAuthRepository(
        authDataSource: AuthDataSource,
        tokenDataStoreDataSource: TokenDataStoreDataSource,
        kakaoOauthManager: KakaoOauthManager,
        naverOauthManager: NaverOauthManager,
        googleOauthManager: GoogleOauthManager,
    ): AuthRepository = DefaultAuthRepository(
        authDataSource = authDataSource,
        tokenDataStoreDataSource = tokenDataStoreDataSource,
        kakaoOauthManager = kakaoOauthManager,
        naverOauthManager = naverOauthManager,
        googleOauthManager = googleOauthManager
    )

    @ViewModelScoped
    @Provides
    fun provideTermsRepository(
        termsDataSource: TermsDataSource,
    ): TermsRepository = DefaultTermsRepository(
        termsDataSource = termsDataSource,
    )


}