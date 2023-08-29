package com.gradation.lift.network.di

import com.gradation.lift.network.fake.TestRetrofit
import com.gradation.lift.network.service.*


object TestServiceModule {

    fun testWorkService(retrofit: TestRetrofit): WorkService =
        retrofit.build().create(WorkService::class.java)


    fun testRoutineService(retrofit: TestRetrofit): RoutineService =
        retrofit.build().create(RoutineService::class.java)


    fun testAuthService(retrofit: TestRetrofit): AuthService =
        retrofit.build().create(AuthService::class.java)


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


