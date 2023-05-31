package com.gradation.lift.network.di

import com.gradation.lift.network.common.NetworkResultHandler
import com.gradation.lift.network.datasource.RoutineDataSource
import com.gradation.lift.network.datasource.WorkDataSource
import com.gradation.lift.network.fake.FakeRoutineDataSource
import com.gradation.lift.network.fake.FakeWorkDataSource
import com.gradation.lift.network.service.RoutineService
import com.gradation.lift.network.service.WorkService
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DataSourceModule::class]
)
object TestDataSourceModule {

    @Provides
    fun provideFakeWorkDataSource(
        workService: WorkService,
        networkResultHandler: NetworkResultHandler,
    ): WorkDataSource = FakeWorkDataSource()


    @Provides
    fun provideFakeRoutineDataSource(
        routineService: RoutineService,
        networkResultHandler: NetworkResultHandler,
    ): RoutineDataSource = FakeRoutineDataSource()

}