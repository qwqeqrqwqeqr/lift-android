package com.gradation.lift.database

import androidx.test.filters.SmallTest
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.gradation.lift.database.dao.RoutineSetDao
import com.gradation.lift.database.data.TestDataGenerator
import com.gradation.lift.database.di.LiftDatabase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.*
import javax.inject.Inject
import javax.inject.Named

@ExperimentalCoroutinesApi
@SmallTest
@HiltAndroidTest
class RoutineSetDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("test_database")
    lateinit var database: LiftDatabase
    private lateinit var routineSetDao: RoutineSetDao


    @Before
    fun setup() {
        hiltRule.inject()
        routineSetDao = database.routineSetDao()
    }


    @After
    fun teardown() {
        database.close()
    }



    @Test
    fun testInsertRoutineSet() = runTest {
        val routineSetEntity = TestDataGenerator.testRoutineSetOne
        routineSetDao.insertRoutineSet(routineSetEntity)
        val allEntries = routineSetDao.getAllRoutineSetEntries().toList()
        assertThat(allEntries.size).isEqualTo(1)
    }

}