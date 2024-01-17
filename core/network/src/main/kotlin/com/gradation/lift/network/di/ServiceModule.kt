package com.gradation.lift.network.di

import com.gradation.lift.network.di.annotation.AuthRetrofit
import com.gradation.lift.network.di.annotation.DefaultRetrofit
import com.gradation.lift.network.service.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun provideWorkService(@DefaultRetrofit retrofit: Retrofit): WorkService =
        retrofit.create(WorkService::class.java)


    @Provides
    @Singleton
    fun providePictureService(@DefaultRetrofit retrofit: Retrofit): PictureService =
        retrofit.create(PictureService::class.java)

    @Provides
    @Singleton
    fun provideRoutineService(@AuthRetrofit retrofit: Retrofit): RoutineService =
        retrofit.create(RoutineService::class.java)


    @Provides
    @Singleton
    fun provideAuthService(@DefaultRetrofit retrofit: Retrofit): AuthService =
        retrofit.create(AuthService::class.java)


    @Provides
    @Singleton
    fun provideRefreshService(@DefaultRetrofit retrofit: Retrofit): RefreshService =
        retrofit.create(RefreshService::class.java)

    @Provides
    @Singleton
    fun provideCheckerService(@DefaultRetrofit retrofit: Retrofit): CheckerService =
        retrofit.create(CheckerService::class.java)

    @Provides
    @Singleton
    fun provideUserService(@AuthRetrofit retrofit: Retrofit): UserService =
        retrofit.create(UserService::class.java)

    @Provides
    @Singleton
    fun provideHistoryService(@AuthRetrofit retrofit: Retrofit): HistoryService =
        retrofit.create(HistoryService::class.java)


    @Provides
    @Singleton
    fun provideNoticeService(@AuthRetrofit retrofit: Retrofit): NoticeService =
        retrofit.create(NoticeService::class.java)

    @Provides
    @Singleton
    fun provideBadgeService(@AuthRetrofit retrofit: Retrofit): BadgeService =
        retrofit.create(BadgeService::class.java)

    @Provides
    @Singleton
    fun provideTermsService(@AuthRetrofit retrofit: Retrofit): TermsService =
        retrofit.create(TermsService::class.java)
}