package com.gradation.lift.network.di

import com.gradation.lift.network.service.AuthService
import com.gradation.lift.network.service.BadgeService
import com.gradation.lift.network.service.CheckerService
import com.gradation.lift.network.service.FavoriteService
import com.gradation.lift.network.service.HistoryService
import com.gradation.lift.network.service.InquiryService
import com.gradation.lift.network.service.NoticeService
import com.gradation.lift.network.service.PictureService
import com.gradation.lift.network.service.RefreshService
import com.gradation.lift.network.service.RoutineService
import com.gradation.lift.network.service.TermsService
import com.gradation.lift.network.service.UserService
import com.gradation.lift.network.service.WorkService


object TestServiceModule {

    fun testWorkService(retrofit: TestRetrofit): WorkService =
        retrofit.build().create(WorkService::class.java)


    fun testRoutineService(retrofit: TestRetrofit): RoutineService =
        retrofit.build().create(RoutineService::class.java)


    fun testAuthService(retrofit: TestRetrofit): AuthService =
        retrofit.build().create(AuthService::class.java)

    fun testBadgeService(retrofit: TestRetrofit): BadgeService =
        retrofit.build().create(BadgeService::class.java)


    fun testNotificationService(retrofit: TestRetrofit): NoticeService =
        retrofit.build().create(NoticeService::class.java)

    fun testTermsService(retrofit: TestRetrofit): TermsService =
        retrofit.build().create(TermsService::class.java)

    fun testFavoriteService(retrofit: TestRetrofit): FavoriteService =
        retrofit.build().create(FavoriteService::class.java)

    fun testInquiryService(retrofit: TestRetrofit): InquiryService =
        retrofit.build().create(InquiryService::class.java)

    fun testRefreshService(retrofit: TestRetrofit): RefreshService =
        retrofit.build().create(RefreshService::class.java)

    fun testCheckerService(retrofit: TestRetrofit): CheckerService =
        retrofit.build().create(CheckerService::class.java)

    fun testUserService(retrofit: TestRetrofit): UserService =
        retrofit.build().create(UserService::class.java)

    fun testHistoryService(retrofit: TestRetrofit): HistoryService =
        retrofit.build().create(HistoryService::class.java)

    fun testPictureService(retrofit: TestRetrofit): PictureService =
        retrofit.build().create(PictureService::class.java)
}


