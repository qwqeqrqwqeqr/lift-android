package com.gradation.lift.database.test

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.gradation.lift.database.dao.RoutineSetDao
import com.gradation.lift.database.di.LiftDatabase
import com.gradation.lift.database.util.TestDataGenerator
import com.gradation.lift.model.data.Week
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
        val routineSetEntity = TestDataGenerator.testRoutineSet1


        routineSetDao.insertRoutineSet(routineSetEntity)
        val result =  routineSetDao.getAllRoutineSet().first()


        Assert.assertEquals(
            listOf(routineSetEntity.name),
            result.map { it.name }
        )
        Assert.assertEquals(
            listOf(routineSetEntity.routineList),
            result.map { it.routineList }
        )
    }

    @Test
    fun testUpdateRoutineSet() = runTest {
        val routineSetEntity = TestDataGenerator.testRoutineSet2


        routineSetDao.insertRoutineSet(routineSetEntity)
        routineSetEntity.name = updatedTestRoutineSetName
        routineSetDao.updateRoutineSet(routineSetEntity)

        val result =  routineSetDao.getAllRoutineSet().first()
        Assert.assertEquals(
            listOf(routineSetEntity.name),
            result.map { it.name }
        )

    }

    @Test
    fun testDeleteAllRoutineSet() = runTest {
        routineSetDao.deleteAllRoutineSet()
        Assert.assertEquals(0,routineSetDao.getAllRoutineSet().first().size)
    }


    @Test
    fun testGetAllRoutineSetByWeek() = runTest{
        routineSetDao.insertRoutineSet( TestDataGenerator.testRoutineSet1)
        Assert.assertEquals(1,routineSetDao.getAllRoutineSetByWeek(Week.Monday).first().size)
        routineSetDao.deleteRoutineSet(routineSetEntity = TestDataGenerator.testRoutineSet1.apply { this.id = 1})
        Assert.assertEquals(0,routineSetDao.getAllRoutineSetByWeek(Week.Monday).first().size)
    }

    @Test
    fun testGetAllRoutineSetById() = runTest{
        val routineSetEntity = TestDataGenerator.testRoutineSet1
        routineSetDao.insertRoutineSet(routineSetEntity)
        val result = routineSetDao.getRoutineSetById(testId)
        Assert.assertEquals(routineSetEntity.name, result.first().name)
    }



    companion object{
        const val updatedTestRoutineSetName = "테스트 루틴 3"
        const val testId = 1L
    }

}

