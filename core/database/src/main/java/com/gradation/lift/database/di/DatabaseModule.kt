package com.gradation.lift.database.di

import android.content.Context
import androidx.room.Room
import com.gradation.lift.database.util.*
import com.gradation.lift.database.util.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesLiftDatabase(
        @ApplicationContext context: Context,
        weekdayTypeConverter: WeekdayTypeConverter,
        stringListTypeConverter: StringListTypeConverter,
        intListTypeConverter: IntListTypeConverter,
        floatListTypeConverter: FloatListTypeConverter,
        workSetListTypeConverter: WorkSetListTypeConverter,
        localTimeTypeConverter: LocalTimeTypeConverter,
        localDateTimeTypeConverter: LocalDateTimeTypeConverter
    ) =
        Room.databaseBuilder(context, LiftDatabase::class.java, DATABASE_NAME)
            .addTypeConverter(weekdayTypeConverter)
            .addTypeConverter(stringListTypeConverter)
            .addTypeConverter(intListTypeConverter)
            .addTypeConverter(floatListTypeConverter)
            .addTypeConverter(workSetListTypeConverter)
            .addTypeConverter(localTimeTypeConverter)
            .addTypeConverter(localDateTimeTypeConverter)
            .build()
}



