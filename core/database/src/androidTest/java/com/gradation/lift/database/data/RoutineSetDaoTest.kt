package com.gradation.lift.database.data

import androidx.test.filters.SmallTest
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.gradation.lift.database.dao.RoutineSetDao
import com.gradation.lift.database.util.TestDataGenerator
import com.gradation.lift.database.di.LiftDatabase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.*
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



    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun testInsertRoutineSet() = runTest{
        val routineSetEntity = TestDataGenerator.testRoutineSetOne
        routineSetDao.insertRoutineSet(routineSetEntity)
        val result =  routineSetDao.getAllRoutineSetEntries().first()

        Assert.assertEquals(
            listOf(routineSetEntity.name),
            result.map { it.name }
        )
        Assert.assertEquals(
            listOf(routineSetEntity.routineList),
            result.map { it.routineList }
        )
    }



}