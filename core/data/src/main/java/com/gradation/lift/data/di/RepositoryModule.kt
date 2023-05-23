package com.gradation.lift.data.di

import com.gradation.lift.data.repository.DefaultWorkRepository
import com.gradation.lift.domain.repository.WorkRepository
import com.gradation.lift.network.common.NetworkResultHandler
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
        networkResultHandler: NetworkResultHandler,
    ): WorkRepository = DefaultWorkRepository(workService,networkResultHandler)


}