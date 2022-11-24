package com.gradation.lift.database.di

import android.content.Context
import androidx.room.Room
import androidx.room.TypeConverters
import com.gradation.lift.database.util.RoutineListTypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
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
        routineListTypeConverter: RoutineListTypeConverter
    ) =
        Room.databaseBuilder(context, LiftDatabase::class.java, "lift_database")
            .addTypeConverter(routineListTypeConverter)
            .build()
}



