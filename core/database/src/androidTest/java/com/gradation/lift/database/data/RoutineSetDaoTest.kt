package com.gradation.lift.database.data

import android.content.Context
import androidx.test.filters.SmallTest
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import com.gradation.lift.database.dao.RoutineSetDao
import com.gradation.lift.database.util.TestDataGenerator
import com.gradation.lift.database.di.LiftDatabase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import com.google.common.truth.Truth.assertThat
import com.gradation.lift.database.di.TestDatabaseModule.provideInMemoryDatabase
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.*
import javax.inject.Inject
import javax.inject.Named

@ExperimentalCoroutinesApi
@SmallTest
@HiltAndroidTest
class RoutineSetDaoTest  {


    @Inject
    @Named("test_database")
    lateinit var database: LiftDatabase
    private lateinit var routineSetDao: RoutineSetDao

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)


    @Before
    fun setup() {
        hiltRule.inject()
        routineSetDao = database.routineSetDao()
    }




    @Test
    fun testInsertRoutineSet() = runTest {
        val routineSetEntity = TestDataGenerator.testRoutineSetOne
        routineSetDao.insertRoutineSet(routineSetEntity)
        val allEntries = routineSetDao.getAllRoutineSetEntries().toList()
        assertThat(allEntries.size).isEqualTo(1)
    }

}