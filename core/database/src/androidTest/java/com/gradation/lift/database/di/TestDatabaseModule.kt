package com.gradation.lift.database.di

import android.content.Context
import androidx.room.Room
import com.gradation.lift.database.util.RoutineListTypeConverter
import com.gradation.lift.database.util.WeekTypeConverter
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Named

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DatabaseModule::class]
)
object TestDatabaseModule {

    @Named("test_database")
    @Provides
    fun provideInMemoryDatabase(
        @ApplicationContext context: Context,
        routineListTypeConverter: RoutineListTypeConverter,
        weekTypeConverter: WeekTypeConverter
    ) =
        Room.inMemoryDatabaseBuilder(context, LiftDatabase::class.java)
            .addTypeConverter(routineListTypeConverter)
            .addTypeConverter(weekTypeConverter)
            .allowMainThreadQueries()
            .build()
}