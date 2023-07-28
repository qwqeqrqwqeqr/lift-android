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
            WeekdayTypeConverter(
                Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
            )


        @Provides
        @Singleton
        fun provideWorkSetListTypeConverter(): WorkSetListTypeConverter =
            WorkSetListTypeConverter(Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build())





        @Provides
        @Singleton
        fun provideLocalTimeTypeConverter(): LocalTimeTypeConverter =
            LocalTimeTypeConverter(Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build())


        @Provides
        @Singleton
        fun provideLocalDateTimeTypeConverter(): LocalDateTimeTypeConverter =
            LocalDateTimeTypeConverter(Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build())

}