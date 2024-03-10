package com.gradation.lift.database.di

import android.content.Context
import androidx.room.Room
import com.gradation.lift.database.LiftDatabase
import com.gradation.lift.database.data.TestDataGenerator.TEST_DATABASE
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
        stringListTypeConverter: StringListTypeConverter,
        intListTypeConverter: IntListTypeConverter,
        checkedWorkSetInfoListTypeConverter: CheckedWorkSetInfoListTypeConverter,
        workSetListTypeConverter: WorkSetListTypeConverter,
        sequenceContentListTypeConverter: SequenceContentListTypeConverter,
        effectContentListTypeConverter: EffectContentListTypeConverter,
        localTimeTypeConverter: LocalTimeTypeConverter,
        localDateTimeTypeConverter: LocalDateTimeTypeConverter,
    ) =
        Room.inMemoryDatabaseBuilder(context, LiftDatabase::class.java)
            .addTypeConverter(stringListTypeConverter)
            .addTypeConverter(intListTypeConverter)
            .addTypeConverter(checkedWorkSetInfoListTypeConverter)
            .addTypeConverter(workSetListTypeConverter)
            .addTypeConverter(sequenceContentListTypeConverter)
            .addTypeConverter(effectContentListTypeConverter)
            .addTypeConverter(localTimeTypeConverter)
            .addTypeConverter(localDateTimeTypeConverter)
            .allowMainThreadQueries()
            .build()
}