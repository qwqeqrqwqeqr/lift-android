package com.gradation.lift.di

import android.content.Context
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.tasks.Task
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideAppUpdateManager(
        @ApplicationContext context: Context,
    ): AppUpdateManager = AppUpdateManagerFactory.create(context)


    @Provides
    @Singleton
    fun provideAppUpdateInfoTask(
        appUpdateManager: AppUpdateManager,
    ): Task<AppUpdateInfo> = appUpdateManager.appUpdateInfo

}