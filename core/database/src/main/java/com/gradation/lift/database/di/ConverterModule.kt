package com.gradation.lift.database.di

import com.gradation.lift.database.util.RoutineListTypeConverter
import com.gradation.lift.database.util.WeekTypeConverter
import com.gradation.lift.database.util.WorkPartTypeConverter
import com.gradation.lift.model.data.Week
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

    @Provides
    @Singleton
    fun provideWeekTypeConverter(moshi: Moshi): WeekTypeConverter =
        WeekTypeConverter(moshi)


    @Provides
    @Singleton
    fun provideWorkPartTypeConverter(moshi: Moshi): WorkPartTypeConverter =
        WorkPartTypeConverter(moshi)


}
