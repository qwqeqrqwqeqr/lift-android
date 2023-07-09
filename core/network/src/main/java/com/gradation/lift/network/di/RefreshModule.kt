package com.gradation.lift.network.di


import com.gradation.lift.datastore.datasource.UserDataStoreDataSource
import com.gradation.lift.network.handler.DefaultRefreshHandler
import com.gradation.lift.network.handler.RefreshHandler
import com.gradation.lift.network.service.RefreshService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RefreshModule {
    @Provides
    @Singleton
    fun provideRefreshHandler(
        refreshService: RefreshService,
        userDataStoreDataSource: UserDataStoreDataSource
    ): RefreshHandler = DefaultRefreshHandler(refreshService,userDataStoreDataSource)


}