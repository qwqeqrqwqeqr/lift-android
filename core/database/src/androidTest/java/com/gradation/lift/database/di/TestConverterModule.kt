package com.gradation.lift.database.di

import com.gradation.lift.database.util.GenderTypeConverter
import com.gradation.lift.database.util.IntListTypeConverter
import com.gradation.lift.database.util.LocalDateTimeTypeConverter
import com.gradation.lift.database.util.LocalDateTypeConverter
import com.gradation.lift.database.util.LocalTimeTypeConverter
import com.gradation.lift.database.util.StringListTypeConverter
import com.gradation.lift.database.util.UnitOfWeightTypeConverter
import com.gradation.lift.database.util.WorkSetListTypeConverter
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
    fun provideIntListTypeConverter(): IntListTypeConverter =
        IntListTypeConverter(Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build())

    @Provides
    @Singleton
    fun provideStringListTypeConverter(): StringListTypeConverter =
        StringListTypeConverter(Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build())

    @Provides
    @Singleton
    fun provideGenderTypeConverter(): GenderTypeConverter =
        GenderTypeConverter()

    @Provides
    @Singleton
    fun provideUnitOfWeightTypeConverter(): UnitOfWeightTypeConverter =
        UnitOfWeightTypeConverter()


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
    fun provideLocalDateTypeConverter(): LocalDateTypeConverter =
        LocalDateTypeConverter()

    @Provides
    @Singleton
    fun provideLocalDateTimeTypeConverter(): LocalDateTimeTypeConverter =
        LocalDateTimeTypeConverter()

}