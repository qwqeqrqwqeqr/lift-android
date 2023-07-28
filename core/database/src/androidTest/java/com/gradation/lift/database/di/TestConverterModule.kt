package com.gradation.lift.database.di

import com.gradation.lift.database.util.*
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton


@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [ConverterModule::class]
)
object TestConverterModule {

    @Provides
    @Singleton
    fun provideWeekdayTypeConverter(): WeekdayTypeConverter =
        WeekdayTypeConverter()
    @Provides
    @Singleton
    fun provideUnitOfWeightTypeConverter(): UnitOfWeightTypeConverter =
        UnitOfWeightTypeConverter()


    @Provides
    @Singleton
    fun provideGenderTypeConverter(): GenderTypeConverter =
        GenderTypeConverter()


    @Provides
    @Singleton
    fun provideWorkSetListTypeConverter(): WorkSetListTypeConverter =
        WorkSetListTypeConverter(Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build())


    @Provides
    @Singleton
    fun provideLocalTimeTypeConverter(): LocalTimeTypeConverter =
        LocalTimeTypeConverter()


    @Provides
    @Singleton
    fun provideLocalDateTimeTypeConverter(): LocalDateTimeTypeConverter =
        LocalDateTimeTypeConverter()

}