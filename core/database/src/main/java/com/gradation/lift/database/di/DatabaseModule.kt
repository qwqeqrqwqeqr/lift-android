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
        labelTypeConverter: LabelTypeConverter,
        workSetListTypeConverter: WorkSetListTypeConverter,
        localTimeTypeConverter: LocalTimeTypeConverter,
        unitOfWeightTypeConverter: UnitOfWeightTypeConverter,
        genderTypeConverter: GenderTypeConverter,
        localDateTimeTypeConverter: LocalDateTimeTypeConverter,
        localDateTypeConverter: LocalDateTypeConverter
    ) =
        Room.databaseBuilder(context, LiftDatabase::class.java, DATABASE_NAME)
            .addTypeConverter(weekdayTypeConverter)
            .addTypeConverter(labelTypeConverter)
            .addTypeConverter(workSetListTypeConverter)
            .addTypeConverter(localTimeTypeConverter)
            .addTypeConverter(localDateTimeTypeConverter)
            .addTypeConverter(genderTypeConverter)
            .addTypeConverter(unitOfWeightTypeConverter)
            .addTypeConverter(localDateTypeConverter)
            .build()
}



