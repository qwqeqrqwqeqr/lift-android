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
    fun provideWeekdayTypeConverter(moshi: Moshi): WeekdayTypeConverter =
        WeekdayTypeConverter(moshi)


    @Provides
    @Singleton
    fun provideWorkSetListTypeConverter(moshi: Moshi): WorkSetListTypeConverter =
        WorkSetListTypeConverter(moshi)





    @Provides
    @Singleton
    fun provideLocalTimeTypeConverter(moshi: Moshi): LocalTimeTypeConverter =
        LocalTimeTypeConverter(moshi)


    @Provides
    @Singleton
    fun provideLocalDateTimeTypeConverter(moshi: Moshi): LocalDateTimeTypeConverter =
        LocalDateTimeTypeConverter(moshi)
}
