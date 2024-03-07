package com.gradation.lift.database.di

import android.content.Context
import androidx.room.Room
import com.gradation.lift.database.LiftDatabase
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
        stringListTypeConverter: StringListTypeConverter,
        intListTypeConverter: IntListTypeConverter,
        checkedWorkSetInfoListTypeConverter: CheckedWorkSetInfoListTypeConverter,
        workSetListTypeConverter: WorkSetListTypeConverter,
        sequenceContentListTypeConverter: SequenceContentListTypeConverter,
        effectContentListTypeConverter: EffectContentListTypeConverter,
        localTimeTypeConverter: LocalTimeTypeConverter,
        localDateTimeTypeConverter: LocalDateTimeTypeConverter,
        localDateTypeConverter: LocalDateTypeConverter,
    ): LiftDatabase =
        Room.databaseBuilder(context, LiftDatabase::class.java, DATABASE_NAME)
            .addTypeConverter(stringListTypeConverter)
            .addTypeConverter(intListTypeConverter)
            .addTypeConverter(checkedWorkSetInfoListTypeConverter)
            .addTypeConverter(workSetListTypeConverter)
            .addTypeConverter(sequenceContentListTypeConverter)
            .addTypeConverter(effectContentListTypeConverter)
            .addTypeConverter(localTimeTypeConverter)
            .addTypeConverter(localDateTimeTypeConverter)
            .addTypeConverter(localDateTypeConverter)
            .fallbackToDestructiveMigration()
            .build()
}



