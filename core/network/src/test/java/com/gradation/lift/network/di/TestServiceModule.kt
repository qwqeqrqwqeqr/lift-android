package com.gradation.lift.network.di

import com.gradation.lift.network.service.*
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton


@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [ServiceModule::class]
)
object TestServiceModule {

    @Provides
    @Singleton
    fun provideWorkService(retrofit: TestRetrofit): WorkService =
        retrofit.build().create(WorkService::class.java)

    @Provides
    @Singleton
    fun provideRoutineService(retrofit: TestRetrofit): RoutineService =
        retrofit.build().create(RoutineService::class.java)


    @Provides
    @Singleton
    fun provideAuthService(retrofit: TestRetrofit): AuthService =
        retrofit.build().create(AuthService::class.java)


    @Provides
    @Singleton
    fun provideRefreshService(retrofit: TestRetrofit): RefreshService =
        retrofit.build().create(RefreshService::class.java)

    @Provides
    @Singleton
    fun provideCheckerService(retrofit: TestRetrofit): CheckerService =
        retrofit.build().create(CheckerService::class.java)

    @Provides
    @Singleton
    fun provideUserService(retrofit: TestRetrofit): UserService =
        retrofit.build().create(UserService::class.java)
}