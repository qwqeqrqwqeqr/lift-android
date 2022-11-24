package com.gradation.lift.database.di

import com.gradation.lift.database.util.RoutineListTypeConverter
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ConverterModule {

    @Provides
    @Singleton
    fun provideRoutineListTypeConverter(moshi: Moshi): RoutineListTypeConverter =
        RoutineListTypeConverter(moshi)
}