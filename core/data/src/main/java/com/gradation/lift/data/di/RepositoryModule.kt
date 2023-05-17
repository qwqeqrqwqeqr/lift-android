package com.gradation.lift.data.di

import com.gradation.lift.common.di.DispatcherProvider
import com.gradation.lift.data.repository.DefaultWorkPartRepository
import com.gradation.lift.data.repository.WorkRepository
import com.gradation.lift.network.service.WorkService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideWorkRepository(
        workService: WorkService,
        dispatcherProvider: DispatcherProvider
    ): WorkRepository = DefaultWorkPartRepository(workService,dispatcherProvider)


}