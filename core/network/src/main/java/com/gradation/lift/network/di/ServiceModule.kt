package com.gradation.lift.network.di

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
    fun provideWorkService(@AuthRetrofit retrofit: Retrofit): WorkService =
        retrofit.create(WorkService::class.java)

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
}