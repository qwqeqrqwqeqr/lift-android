package com.gradation.lift.network.di

import com.gradation.lift.common.common.DispatcherProvider
import com.gradation.lift.network.datasource.*
import com.gradation.lift.network.datasource.auth.AuthDataSource
import com.gradation.lift.network.datasource.auth.DefaultAuthDataSource
import com.gradation.lift.network.datasource.badge.BadgeDataSource
import com.gradation.lift.network.datasource.badge.DefaultBadgeDataSource
import com.gradation.lift.network.datasource.checker.CheckerDataSource
import com.gradation.lift.network.datasource.checker.DefaultCheckerDataSource
import com.gradation.lift.network.datasource.history.DefaultHistoryDataSource
import com.gradation.lift.network.datasource.history.HistoryDataSource
import com.gradation.lift.network.datasource.notice.DefaultNoticeDefaultDataSource
import com.gradation.lift.network.datasource.notice.NoticeDataSource
import com.gradation.lift.network.datasource.picture.DefaultPictureDataSource
import com.gradation.lift.network.datasource.picture.PictureDataSource
import com.gradation.lift.network.datasource.routine.DefaultRoutineDataSource
import com.gradation.lift.network.datasource.routine.RoutineDataSource
import com.gradation.lift.network.datasource.terms.DefaultTermsDataSource
import com.gradation.lift.network.datasource.terms.TermsDataSource
import com.gradation.lift.network.datasource.user.DefaultUserDataSource
import com.gradation.lift.network.datasource.user.UserDataSource
import com.gradation.lift.network.datasource.work.DefaultWorkDataSource
import com.gradation.lift.network.datasource.work.WorkDataSource
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
    ): WorkDataSource = DefaultWorkDataSource(workService, networkResultHandler, dispatcherProvider)


    @Provides
    fun provideRoutineDataSource(
        routineService: RoutineService,
        networkResultHandler: NetworkResultHandler,
        dispatcherProvider: DispatcherProvider,
    ): RoutineDataSource =
        DefaultRoutineDataSource(routineService, networkResultHandler, dispatcherProvider)


    @Provides
    fun provideCheckerDataSource(
        checkerService: CheckerService,
        networkResultHandler: NetworkResultHandler,
        dispatcherProvider: DispatcherProvider,
    ): CheckerDataSource =
        DefaultCheckerDataSource(checkerService, networkResultHandler, dispatcherProvider)


    @Provides
    fun providePictureDataSource(
        pictureService: PictureService,
        networkResultHandler: NetworkResultHandler,
        dispatcherProvider: DispatcherProvider,
    ): PictureDataSource =
        DefaultPictureDataSource(pictureService, networkResultHandler, dispatcherProvider)

    @Provides
    fun provideUserDataSource(
        userService: UserService,
        networkResultHandler: NetworkResultHandler,
        dispatcherProvider: DispatcherProvider,
    ): UserDataSource = DefaultUserDataSource(userService, networkResultHandler, dispatcherProvider)

    @Provides
    fun provideHistoryDataSource(
        historyService: HistoryService,
        networkResultHandler: NetworkResultHandler,
        dispatcherProvider: DispatcherProvider,
    ): HistoryDataSource =
        DefaultHistoryDataSource(historyService, networkResultHandler, dispatcherProvider)


    @Provides
    fun provideAuthDataSource(
        authService: AuthService,
        networkResultHandler: NetworkResultHandler,
        dispatcherProvider: DispatcherProvider,
    ): AuthDataSource = DefaultAuthDataSource(authService, networkResultHandler, dispatcherProvider)


    @Provides
    fun provideNoticeDataSource(
        noticeService: NoticeService,
        networkResultHandler: NetworkResultHandler,
        dispatcherProvider: DispatcherProvider,
    ): NoticeDataSource =
        DefaultNoticeDefaultDataSource(noticeService, networkResultHandler, dispatcherProvider)


    @Provides
    fun provideBadgeDataSource(
        badgeService: BadgeService,
        networkResultHandler: NetworkResultHandler,
        dispatcherProvider: DispatcherProvider,
    ): BadgeDataSource =
        DefaultBadgeDataSource(badgeService, networkResultHandler, dispatcherProvider)


    @Provides
    fun provideTermsDataSource(
        termsService: TermsService,
        networkResultHandler: NetworkResultHandler,
        dispatcherProvider: DispatcherProvider,
    ): TermsDataSource =
        DefaultTermsDataSource(termsService, networkResultHandler, dispatcherProvider)


}
