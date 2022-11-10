package com.gradation.lift.database.di

import android.content.Context
import androidx.room.Room
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
object DatabaseModule{
    @Provides
    @Singleton
    fun providesListDatabase(
        @ApplicationContext context: Context,
    ): LiftDatabase = Room.databaseBuilder(
        context,
        LiftDatabase::class.java,
        "lift-database"
    ).build()
}


