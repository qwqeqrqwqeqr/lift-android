package com.gradation.lift.database.di

import android.content.Context
import androidx.room.Room
import com.gradation.lift.database.Constants.TEST_DATABASE
import com.gradation.lift.database.util.*
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

    @Named(TEST_DATABASE)
    @Provides
    fun provideInMemoryDatabase(
        @ApplicationContext context: Context,
        weekdayTypeConverter: WeekdayTypeConverter,
        stringListTypeConverter: StringListTypeConverter,
        intListTypeConverter: IntListTypeConverter,
        floatListTypeConverter: FloatListTypeConverter,
        workSetListTypeConverter: WorkSetListTypeConverter,
        localTimeTypeConverter: LocalTimeTypeConverter,
        localDateTypeConverter: LocalDateTimeTypeConverter
    ) =
        Room.inMemoryDatabaseBuilder(context, LiftDatabase::class.java)
            .addTypeConverter(weekdayTypeConverter)
            .addTypeConverter(stringListTypeConverter)
            .addTypeConverter(intListTypeConverter)
            .addTypeConverter(floatListTypeConverter)
            .addTypeConverter(workSetListTypeConverter)
            .addTypeConverter(localTimeTypeConverter)
            .addTypeConverter(localDateTypeConverter)
            .allowMainThreadQueries()
            .build()
}