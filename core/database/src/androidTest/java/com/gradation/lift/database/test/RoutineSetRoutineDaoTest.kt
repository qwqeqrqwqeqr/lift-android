package com.gradation.lift.database.test

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.gradation.lift.database.dao.RoutineSetRoutineDao
import com.gradation.lift.database.data.TestDataGenerator.TEST_DATABASE
import com.gradation.lift.database.di.LiftDatabase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import javax.inject.Inject
import javax.inject.Named

@ExperimentalCoroutinesApi
@SmallTest
@HiltAndroidTest
class RoutineSetRoutineDaoTest {
    @Inject
    @Named(TEST_DATABASE)
    lateinit var database: LiftDatabase
    private lateinit var routineSetRoutineDao: RoutineSetRoutineDao

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()
        routineSetRoutineDao = database.routineSetRoutineDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

}