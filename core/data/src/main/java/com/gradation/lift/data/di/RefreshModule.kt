package com.gradation.lift.data.di

import com.gradation.lift.data.utils.DefaultRefreshManager
import com.gradation.lift.data.utils.RefreshManager
import com.gradation.lift.datastore.datasource.DataStoreDataSource
import com.gradation.lift.network.service.RefreshService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object RefreshModule {

    @ViewModelScoped
    @Provides
    fun provideRefresh(
        refreshService: RefreshService,
        dataStoreDataSource: DataStoreDataSource
    ): RefreshManager = DefaultRefreshManager(refreshService,dataStoreDataSource)


}