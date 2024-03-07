package com.gradation.lift.network.di

import com.gradation.lift.common.common.DispatcherProvider
import com.gradation.lift.network.datasource.*
import com.gradation.lift.network.datasource.auth.AuthRemoteDataSource
import com.gradation.lift.network.datasource.auth.DefaultAuthRemoteDataSource
import com.gradation.lift.network.datasource.badge.BadgeRemoteDataSource
import com.gradation.lift.network.datasource.badge.DefaultBadgeRemoteDataSource
import com.gradation.lift.network.datasource.checker.CheckerRemoteDataSource
import com.gradation.lift.network.datasource.checker.DefaultCheckerRemoteDataSource
import com.gradation.lift.network.datasource.favorite.DefaultFavoriteRemoteDataSource
import com.gradation.lift.network.datasource.favorite.FavoriteRemoteDataSource
import com.gradation.lift.network.datasource.history.DefaultHistoryRemoteDataSource
import com.gradation.lift.network.datasource.history.HistoryRemoteDataSource
import com.gradation.lift.network.datasource.inquiry.DefaultInquiryRemoteDataSource
import com.gradation.lift.network.datasource.inquiry.InquiryRemoteDataSource
import com.gradation.lift.network.datasource.notice.DefaultNoticeDefaultRemoteDataSource
import com.gradation.lift.network.datasource.notice.NoticeRemoteDataSource
import com.gradation.lift.network.datasource.picture.DefaultPictureRemoteDataSource
import com.gradation.lift.network.datasource.picture.PictureRemoteDataSource
import com.gradation.lift.network.datasource.routine.DefaultRoutineRemoteDataSource
import com.gradation.lift.network.datasource.routine.RoutineRemoteDataSource
import com.gradation.lift.network.datasource.terms.DefaultTermsRemoteDataSource
import com.gradation.lift.network.datasource.terms.TermsRemoteDataSource
import com.gradation.lift.network.datasource.user.DefaultUserRemoteDataSource
import com.gradation.lift.network.datasource.user.UserRemoteDataSource
import com.gradation.lift.network.datasource.work.DefaultWorkRemoteDataSource
import com.gradation.lift.network.datasource.work.WorkRemoteDataSource
import com.gradation.lift.network.handler.NetworkResultHandler
import com.gradation.lift.network.service.*
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
        dispatcherProvider: DispatcherProvider,
    ): WorkRemoteDataSource =
        DefaultWorkRemoteDataSource(workService, networkResultHandler, dispatcherProvider)


    @Provides
    fun provideRoutineDataSource(
        routineService: RoutineService,
        networkResultHandler: NetworkResultHandler,
        dispatcherProvider: DispatcherProvider,
    ): RoutineRemoteDataSource =
        DefaultRoutineRemoteDataSource(routineService, networkResultHandler, dispatcherProvider)


    @Provides
    fun provideCheckerDataSource(
        checkerService: CheckerService,
        networkResultHandler: NetworkResultHandler,
        dispatcherProvider: DispatcherProvider,
    ): CheckerRemoteDataSource =
        DefaultCheckerRemoteDataSource(checkerService, networkResultHandler, dispatcherProvider)


    @Provides
    fun providePictureDataSource(
        pictureService: PictureService,
        networkResultHandler: NetworkResultHandler,
        dispatcherProvider: DispatcherProvider,
    ): PictureRemoteDataSource =
        DefaultPictureRemoteDataSource(pictureService, networkResultHandler, dispatcherProvider)

    @Provides
    fun provideUserDataSource(
        userService: UserService,
        networkResultHandler: NetworkResultHandler,
        dispatcherProvider: DispatcherProvider,
    ): UserRemoteDataSource =
        DefaultUserRemoteDataSource(userService, networkResultHandler, dispatcherProvider)

    @Provides
    fun provideHistoryDataSource(
        historyService: HistoryService,
        networkResultHandler: NetworkResultHandler,
        dispatcherProvider: DispatcherProvider,
    ): HistoryRemoteDataSource =
        DefaultHistoryRemoteDataSource(historyService, networkResultHandler, dispatcherProvider)


    @Provides
    fun provideAuthDataSource(
        authService: AuthService,
        networkResultHandler: NetworkResultHandler,
        dispatcherProvider: DispatcherProvider,
    ): AuthRemoteDataSource =
        DefaultAuthRemoteDataSource(authService, networkResultHandler, dispatcherProvider)


    @Provides
    fun provideNoticeDataSource(
        noticeService: NoticeService,
        networkResultHandler: NetworkResultHandler,
        dispatcherProvider: DispatcherProvider,
    ): NoticeRemoteDataSource =
        DefaultNoticeDefaultRemoteDataSource(
            noticeService,
            networkResultHandler,
            dispatcherProvider
        )


    @Provides
    fun provideBadgeDataSource(
        badgeService: BadgeService,
        networkResultHandler: NetworkResultHandler,
        dispatcherProvider: DispatcherProvider,
    ): BadgeRemoteDataSource =
        DefaultBadgeRemoteDataSource(badgeService, networkResultHandler, dispatcherProvider)


    @Provides
    fun provideTermsDataSource(
        termsService: TermsService,
        networkResultHandler: NetworkResultHandler,
        dispatcherProvider: DispatcherProvider,
    ): TermsRemoteDataSource =
        DefaultTermsRemoteDataSource(termsService, networkResultHandler, dispatcherProvider)


    @Provides
    fun provideFavoriteDataSource(
        favoriteService: FavoriteService,
        networkResultHandler: NetworkResultHandler,
        dispatcherProvider: DispatcherProvider,
    ): FavoriteRemoteDataSource =
        DefaultFavoriteRemoteDataSource(favoriteService, networkResultHandler, dispatcherProvider)

    @Provides
    fun provideInquiryDataSource(
        inquiryService: InquiryService,
        networkResultHandler: NetworkResultHandler,
        dispatcherProvider: DispatcherProvider,
    ): InquiryRemoteDataSource =
        DefaultInquiryRemoteDataSource(inquiryService, networkResultHandler, dispatcherProvider)


}
