package com.gradation.lift.database.di

import android.content.Context
import androidx.room.Room
import com.gradation.lift.database.data.TestEntityDataGenerator.TEST_DATABASE
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
        listTypeConverter: ListTypeConverter,
        genderTypeConverter: GenderTypeConverter,
        unitOfWeightTypeConverter: UnitOfWeightTypeConverter,
        workSetListTypeConverter: WorkSetListTypeConverter,
        localTimeTypeConverter: LocalTimeTypeConverter,
        localDateTypeConverter: LocalDateTypeConverter,
        localDateTimeTypeConverter: LocalDateTimeTypeConverter
    ) =
        Room.inMemoryDatabaseBuilder(context, LiftDatabase::class.java)
            .addTypeConverter(listTypeConverter)
            .addTypeConverter(genderTypeConverter)
            .addTypeConverter(unitOfWeightTypeConverter)
            .addTypeConverter(workSetListTypeConverter)
            .addTypeConverter(localTimeTypeConverter)
            .addTypeConverter(localDateTypeConverter)
            .addTypeConverter(localDateTimeTypeConverter)
            .allowMainThreadQueries()
            .build()
}