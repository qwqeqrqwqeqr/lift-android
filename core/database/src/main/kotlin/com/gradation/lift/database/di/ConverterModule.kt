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
    fun provideIntListTypeConverter(moshi: Moshi): IntListTypeConverter =
        IntListTypeConverter(moshi)

    @Provides
    @Singleton
    fun provideStringListTypeConverter(moshi: Moshi): StringListTypeConverter =
        StringListTypeConverter(moshi)

    @Provides
    @Singleton
    fun provideCheckedWorkSetInfoListTypeConverter(moshi: Moshi): CheckedWorkSetInfoListTypeConverter =
        CheckedWorkSetInfoListTypeConverter(moshi)

    @Provides
    @Singleton
    fun provideSequenceContentListTypeConverter(moshi: Moshi): SequenceContentListTypeConverter =
        SequenceContentListTypeConverter(moshi)

    @Provides
    @Singleton
    fun provideEffectContentListTypeConverter(moshi: Moshi): EffectContentListTypeConverter =
        EffectContentListTypeConverter(moshi)

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
    fun provideLocalDateTimeTypeConverter(): LocalDateTimeTypeConverter =
        LocalDateTimeTypeConverter()
}
