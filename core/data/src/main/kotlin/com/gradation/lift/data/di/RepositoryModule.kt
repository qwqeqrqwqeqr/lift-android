package com.gradation.lift.data.di

import com.gradation.lift.common.common.DispatcherProvider
import com.gradation.lift.data.repository.*
import com.gradation.lift.database.dao.WorkDao
import com.gradation.lift.datastore.datasource.SettingDataStoreDataSource
import com.gradation.lift.datastore.datasource.TokenDataStoreDataSource
import com.gradation.lift.domain.repository.*
import com.gradation.lift.network.datasource.*
import com.gradation.lift.network.datasource.auth.AuthDataSource
import com.gradation.lift.network.datasource.badge.BadgeDataSource
import com.gradation.lift.network.datasource.checker.CheckerDataSource
import com.gradation.lift.network.datasource.favorite.FavoriteDataSource
import com.gradation.lift.network.datasource.history.HistoryDataSource
import com.gradation.lift.network.datasource.inquiry.InquiryDataSource
import com.gradation.lift.network.datasource.notice.NoticeDataSource
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
        dispatcherProvider: DispatcherProvider,
    ): WorkRepository = DefaultWorkRepository(
        workDataSource = workDataSource,
        workDao = workDao,
        dispatcherProvider
    )


    @ViewModelScoped
    @Provides
    fun provideRoutineRepository(
        routineDataSource: RoutineDataSource,
        dispatcherProvider: DispatcherProvider,
    ): RoutineRepository =
        DefaultRoutineRepository(routineDataSource = routineDataSource, dispatcherProvider)


    @ViewModelScoped
    @Provides
    fun provideCheckerRepository(
        checkerDataSource: CheckerDataSource,
        dispatcherProvider: DispatcherProvider,
    ): CheckerRepository =
        DefaultCheckerRepository(checkerDataSource = checkerDataSource, dispatcherProvider)


    @ViewModelScoped
    @Provides
    fun provideSettingRepository(
        settingDataStoreDataSource: SettingDataStoreDataSource,
        dispatcherProvider: DispatcherProvider,
    ): SettingRepository =
        DefaultSettingRepository(
            settingDataStoreDataSource = settingDataStoreDataSource,
            dispatcherProvider
        )

    @ViewModelScoped
    @Provides
    fun provideHistoryRepository(
        historyDataSource: HistoryDataSource,
        dispatcherProvider: DispatcherProvider,
    ): HistoryRepository =
        DefaultHistoryRepository(historyDataSource = historyDataSource, dispatcherProvider)


    @ViewModelScoped
    @Provides
    fun providePictureRepository(
        pictureDataSource: PictureDataSource,
        dispatcherProvider: DispatcherProvider,
    ): PictureRepository =
        DefaultPictureRepository(pictureDataSource = pictureDataSource, dispatcherProvider)


    @ViewModelScoped
    @Provides
    fun provideUserRepository(
        userDataSource: UserDataSource,
        dispatcherProvider: DispatcherProvider,
    ): UserRepository = DefaultUserRepository(
        userDataSource = userDataSource, dispatcherProvider
    )

    @ViewModelScoped
    @Provides
    fun provideBadgeRepository(
        badgeDataSource: BadgeDataSource,
        dispatcherProvider: DispatcherProvider,
    ): BadgeRepository = DefaultBadgeRepository(
        badgeDataSource = badgeDataSource, dispatcherProvider
    )

    @ViewModelScoped
    @Provides
    fun provideNoticeRepository(
        noticeDataSource: NoticeDataSource,
        dispatcherProvider: DispatcherProvider,
    ): NoticeRepository = DefaultNoticeRepository(
        noticeDataSource = noticeDataSource, dispatcherProvider
    )

    @ViewModelScoped
    @Provides
    fun provideAuthRepository(
        authDataSource: AuthDataSource,
        tokenDataStoreDataSource: TokenDataStoreDataSource,
        kakaoOauthManager: KakaoOauthManager,
        naverOauthManager: NaverOauthManager,
        googleOauthManager: GoogleOauthManager,
        dispatcherProvider: DispatcherProvider,
    ): AuthRepository = DefaultAuthRepository(
        authDataSource = authDataSource,
        tokenDataStoreDataSource = tokenDataStoreDataSource,
        kakaoOauthManager = kakaoOauthManager,
        naverOauthManager = naverOauthManager,
        googleOauthManager = googleOauthManager,
        dispatcherProvider
    )

    @ViewModelScoped
    @Provides
    fun provideTermsRepository(
        termsDataSource: TermsDataSource,
        dispatcherProvider: DispatcherProvider,
    ): TermsRepository = DefaultTermsRepository(
        termsDataSource = termsDataSource, dispatcherProvider
    )


    @ViewModelScoped
    @Provides
    fun provideFavoriteRepository(
        favoriteDataSource: FavoriteDataSource,
        dispatcherProvider: DispatcherProvider,
    ): FavoriteRepository = DefaultFavoriteRepository(
        favoriteDataSource, dispatcherProvider
    )

    @ViewModelScoped
    @Provides
    fun provideInquiryRepository(
        inquiryDataSource: InquiryDataSource,
        dispatcherProvider: DispatcherProvider,
    ): InquiryRepository = DefaultInquiryRepository(
        inquiryDataSource, dispatcherProvider
    )

}