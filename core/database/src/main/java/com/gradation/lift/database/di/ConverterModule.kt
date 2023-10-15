package com.gradation.lift.database.di

import com.gradation.lift.database.util.*
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
    fun provideListTypeConverter(moshi: Moshi): ListTypeConverter =
        ListTypeConverter(moshi)

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
    fun provideWorkSetListTypeConverter(moshi: Moshi): WorkSetListTypeConverter =
        WorkSetListTypeConverter(moshi)


    @Provides
    @Singleton
    fun provideLocalTimeTypeConverter(): LocalTimeTypeConverter =
        LocalTimeTypeConverter()


    @Provides
    @Singleton
    fun provideLocalDateTypeConverter(): LocalDateTypeConverter =
        LocalDateTypeConverter()

    @Provides
    @Singleton
    fun provideLocalDateTimeTypeConverter(): LocalDateTimeTypeConverter =
        LocalDateTimeTypeConverter()
}
