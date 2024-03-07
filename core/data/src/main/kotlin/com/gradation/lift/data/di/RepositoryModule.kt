package com.gradation.lift.data.di

import com.gradation.lift.common.common.DispatcherProvider
import com.gradation.lift.data.repository.*
import com.gradation.lift.database.datasource.badge.BadgeLocalDataSource
import com.gradation.lift.database.datasource.history.HistoryLocalDataSource
import com.gradation.lift.database.datasource.routine.RoutineLocalDataSource
import com.gradation.lift.database.datasource.userBadge.DefaultUserBadgeLocalDataSource
import com.gradation.lift.database.datasource.work.WorkLocalDataSource
import com.gradation.lift.database.datasource.workCategory.WorkCategoryLocalDataSource
import com.gradation.lift.database.datasource.workPart.WorkPartLocalDataSource
import com.gradation.lift.datastore.datasource.SettingDataStoreDataSource
import com.gradation.lift.datastore.datasource.TokenDataStoreDataSource
import com.gradation.lift.domain.repository.*
import com.gradation.lift.network.datasource.*
import com.gradation.lift.network.datasource.auth.AuthRemoteDataSource
import com.gradation.lift.network.datasource.badge.BadgeRemoteDataSource
import com.gradation.lift.network.datasource.checker.CheckerRemoteDataSource
import com.gradation.lift.network.datasource.favorite.FavoriteRemoteDataSource
import com.gradation.lift.network.datasource.history.HistoryRemoteDataSource
import com.gradation.lift.network.datasource.inquiry.InquiryRemoteDataSource
import com.gradation.lift.network.datasource.notice.NoticeRemoteDataSource
import com.gradation.lift.network.datasource.picture.PictureRemoteDataSource
import com.gradation.lift.network.datasource.routine.RoutineRemoteDataSource
import com.gradation.lift.network.datasource.terms.TermsRemoteDataSource
import com.gradation.lift.network.datasource.user.UserRemoteDataSource
import com.gradation.lift.network.datasource.work.WorkRemoteDataSource
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
        workLocalDataSource: WorkLocalDataSource,
        dispatcherProvider: DispatcherProvider,
    ): WorkRepository = DefaultWorkRepository(
        workLocalDataSource, dispatcherProvider
    )

    @ViewModelScoped
    @Provides
    fun provideWorkPartRepository(
        workRemoteDataSource: WorkRemoteDataSource,
        workPartLocalDataSource: WorkPartLocalDataSource,
        dispatcherProvider: DispatcherProvider,
    ): WorkPartRepository = DefaultWorkPartRepository(
        workRemoteDataSource, workPartLocalDataSource, dispatcherProvider
    )

    @ViewModelScoped
    @Provides
    fun provideWorkCategoryRepository(
        workRemoteDataSource: WorkRemoteDataSource,
        workCategoryLocalDataSource: WorkCategoryLocalDataSource,
        dispatcherProvider: DispatcherProvider,
    ): WorkCategoryRepository = DefaultWorkCategoryRepository(
        workRemoteDataSource, workCategoryLocalDataSource, dispatcherProvider
    )

    @ViewModelScoped
    @Provides
    fun provideRoutineRepository(
        routineRemoteDataSource: RoutineRemoteDataSource,
        routineLocalDataSource: RoutineLocalDataSource,
        dispatcherProvider: DispatcherProvider,
    ): RoutineRepository =
        DefaultRoutineRepository(
            routineRemoteDataSource,
            routineLocalDataSource,
            dispatcherProvider
        )


    @ViewModelScoped
    @Provides
    fun provideCheckerRepository(
        checkerRemoteDataSource: CheckerRemoteDataSource,
        dispatcherProvider: DispatcherProvider,
    ): CheckerRepository =
        DefaultCheckerRepository(
            checkerRemoteDataSource = checkerRemoteDataSource,
            dispatcherProvider
        )


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
        historyRemoteDataSource: HistoryRemoteDataSource,
        historyLocalDataSource: HistoryLocalDataSource,
        dispatcherProvider: DispatcherProvider,
    ): HistoryRepository =
        DefaultHistoryRepository(
            historyRemoteDataSource,
            historyLocalDataSource,
            dispatcherProvider
        )


    @ViewModelScoped
    @Provides
    fun providePictureRepository(
        pictureRemoteDataSource: PictureRemoteDataSource,
        dispatcherProvider: DispatcherProvider,
    ): PictureRepository =
        DefaultPictureRepository(
            pictureRemoteDataSource = pictureRemoteDataSource,
            dispatcherProvider
        )


    @ViewModelScoped
    @Provides
    fun provideUserRepository(
        userRemoteDataSource: UserRemoteDataSource,
        dispatcherProvider: DispatcherProvider,
    ): UserRepository = DefaultUserRepository(
        userRemoteDataSource = userRemoteDataSource, dispatcherProvider
    )

    @ViewModelScoped
    @Provides
    fun provideBadgeRepository(
        badgeRemoteDataSource: BadgeRemoteDataSource,
        badgeLocalDataSource: BadgeLocalDataSource,
        userBadgeLocalDataSource: DefaultUserBadgeLocalDataSource,
        dispatcherProvider: DispatcherProvider,
    ): BadgeRepository = DefaultBadgeRepository(
        badgeRemoteDataSource, badgeLocalDataSource, userBadgeLocalDataSource, dispatcherProvider
    )


    @ViewModelScoped
    @Provides
    fun provideNoticeRepository(
        noticeRemoteDataSource: NoticeRemoteDataSource,
        dispatcherProvider: DispatcherProvider,
    ): NoticeRepository = DefaultNoticeRepository(
        noticeRemoteDataSource = noticeRemoteDataSource, dispatcherProvider
    )

    @ViewModelScoped
    @Provides
    fun provideAuthRepository(
        authRemoteDataSource: AuthRemoteDataSource,
        tokenDataStoreDataSource: TokenDataStoreDataSource,
        kakaoOauthManager: KakaoOauthManager,
        naverOauthManager: NaverOauthManager,
        googleOauthManager: GoogleOauthManager,
        dispatcherProvider: DispatcherProvider,
    ): AuthRepository = DefaultAuthRepository(
        authRemoteDataSource = authRemoteDataSource,
        tokenDataStoreDataSource = tokenDataStoreDataSource,
        kakaoOauthManager = kakaoOauthManager,
        naverOauthManager = naverOauthManager,
        googleOauthManager = googleOauthManager,
        dispatcherProvider
    )

    @ViewModelScoped
    @Provides
    fun provideTermsRepository(
        termsRemoteDataSource: TermsRemoteDataSource,
        dispatcherProvider: DispatcherProvider,
    ): TermsRepository = DefaultTermsRepository(
        termsRemoteDataSource = termsRemoteDataSource, dispatcherProvider
    )


    @ViewModelScoped
    @Provides
    fun provideFavoriteRepository(
        favoriteRemoteDataSource: FavoriteRemoteDataSource,
        dispatcherProvider: DispatcherProvider,
    ): FavoriteRepository = DefaultFavoriteRepository(
        favoriteRemoteDataSource, dispatcherProvider
    )

    @ViewModelScoped
    @Provides
    fun provideInquiryRepository(
        inquiryRemoteDataSource: InquiryRemoteDataSource,
        dispatcherProvider: DispatcherProvider,
    ): InquiryRepository = DefaultInquiryRepository(
        inquiryRemoteDataSource, dispatcherProvider
    )

}