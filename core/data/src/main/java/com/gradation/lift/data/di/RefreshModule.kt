package com.gradation.lift.data.di

import com.gradation.lift.data.utils.DefaultRefreshManager
import com.gradation.lift.data.utils.RefreshManager
import com.gradation.lift.datastore.datasource.UserDataStoreDataSource
import com.gradation.lift.network.service.RefreshService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RefreshModule {
    @Provides
    @Singleton
    fun provideRefreshManager(
        refreshService: RefreshService,
        userDataStoreDataSource: UserDataStoreDataSource
    ): RefreshManager = DefaultRefreshManager(refreshService,userDataStoreDataSource)


}