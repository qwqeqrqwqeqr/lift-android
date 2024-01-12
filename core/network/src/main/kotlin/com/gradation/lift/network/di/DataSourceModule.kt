package com.gradation.lift.network.di

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
    ): WorkDataSource = DefaultWorkDataSource(workService, networkResultHandler)


    @Provides
    fun provideRoutineDataSource(
        routineService: RoutineService,
        networkResultHandler: NetworkResultHandler,
    ): RoutineDataSource = DefaultRoutineDataSource(routineService, networkResultHandler)


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
    fun provideAuthDataSource(
        authService: AuthService,
        networkResultHandler: NetworkResultHandler,
    ): AuthDataSource = DefaultAuthDataSource(authService, networkResultHandler)


    @Provides
    fun provideNoticeDataSource(
        noticeService: NoticeService,
        networkResultHandler: NetworkResultHandler,
    ): NoticeDataSource =
        DefaultNoticeDefaultDataSource(noticeService, networkResultHandler)


    @Provides
    fun provideBadgeDataSource(
        badgeService: BadgeService,
        networkResultHandler: NetworkResultHandler,
    ): BadgeDataSource = DefaultBadgeDataSource(badgeService, networkResultHandler)


    @Provides
    fun provideTermsDataSource(
        termsService: TermsService,
        networkResultHandler: NetworkResultHandler,
    ): TermsDataSource = DefaultTermsDataSource(termsService, networkResultHandler)


}
