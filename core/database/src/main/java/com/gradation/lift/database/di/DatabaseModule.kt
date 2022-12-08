package com.gradation.lift.database.di

import android.content.Context
import androidx.room.Room
import com.gradation.lift.database.util.RoutineListTypeConverter
import com.gradation.lift.database.util.WeekTypeConverter
import com.gradation.lift.database.util.WorkPartTypeConverter
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
        routineListTypeConverter: RoutineListTypeConverter,
        weekTypeConverter: WeekTypeConverter,
        workPartTypeConverter: WorkPartTypeConverter,
    ) =
        Room.databaseBuilder(context, LiftDatabase::class.java, "lift_database")
            .addTypeConverter(routineListTypeConverter)
            .addTypeConverter(weekTypeConverter)
            .addTypeConverter(workPartTypeConverter)
            .build()
}



