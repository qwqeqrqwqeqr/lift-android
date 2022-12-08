package com.gradation.lift.database.test

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.gradation.lift.database.dao.WorkCategoryDao
import com.gradation.lift.database.di.LiftDatabase
import com.gradation.lift.database.util.TestDataGenerator
import com.gradation.lift.model.data.WorkPart
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.test.runTest
import org.junit.*
import javax.inject.Inject
import javax.inject.Named


@ExperimentalCoroutinesApi
@SmallTest
@HiltAndroidTest
class WorkCategoryDaoTest {

    @Inject
    @Named("test_database")
    lateinit var database: LiftDatabase
    private lateinit var workCategoryDao: WorkCategoryDao

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()
        workCategoryDao = database.workCategoryDao()
    }

    @After
    fun tearDown() {
        database.close()
    }



    @Test
    fun testInsertWorkCategory() = runTest{
        val workCategoryEntity = TestDataGenerator.testWorkCategory1

        workCategoryDao.insertWorkCategory(workCategoryEntity)
        val result =  workCategoryDao.getAllWorkCategory().first()

        Assert.assertEquals(
            listOf(workCategoryEntity.name),
            result.map { it.name }
        )
    }



    @Test
    fun testDeleteWorkCategory() = runTest {
        val workCategoryEntity = TestDataGenerator.testWorkCategory2
        workCategoryDao.insertWorkCategory(workCategoryEntity)
        Assert.assertEquals(1,workCategoryDao.getAllWorkCategory().first().size)
        workCategoryDao.deleteWorkCategory(workCategoryEntity)
        Assert.assertEquals(0,workCategoryDao.getAllWorkCategory().first().size)
    }


    @Test
    fun testDeleteAllWorkCategory() = runTest {
        val workCategoryEntity = TestDataGenerator.testWorkCategory1
        workCategoryDao.insertWorkCategory(workCategoryEntity)
        Assert.assertEquals(1,workCategoryDao.getAllWorkCategory().first().size)
        workCategoryDao.deleteAllWorkCategory()
        Assert.assertEquals(0,workCategoryDao.getAllWorkCategory().first().size)
    }


    @Test
    fun testUpdateRoutineSet() = runTest {
        val workCategoryEntity = TestDataGenerator.testWorkCategory2

        workCategoryDao.insertWorkCategory(workCategoryEntity)
        workCategoryEntity.name = updatedTestWorkCategoryName

        workCategoryDao.updateWorkCategory(workCategoryEntity)

        val result =  workCategoryDao.getAllWorkCategory().first()
        Assert.assertEquals(
            listOf(workCategoryEntity.name),
            result.map { it.name }
        )

    }


    @Test
    fun testGetAllWorkCategoryById() = runTest{
        val workCategoryEntity = TestDataGenerator.testWorkCategory1
        workCategoryDao.insertWorkCategory(workCategoryEntity)
        val result = workCategoryDao.getWorkCategoryById(testId).first()
        Assert.assertEquals(workCategoryEntity.name, result.name)
    }


    @Test
    fun testGetAllWorkCategoryEntriesByWorkPartCustomFlag() = runTest{
        val workCategoryEntity1 = TestDataGenerator.testWorkCategory1
        val workCategoryEntity2 = TestDataGenerator.testWorkCategory2
        workCategoryDao.insertWorkCategory(workCategoryEntity1)
        workCategoryDao.insertWorkCategory(workCategoryEntity2)
        val result = workCategoryDao.getAllWorkCategoryEntriesByWorkPartCustomFlag(WorkPart.arm,true).first()
        Assert.assertEquals(listOf(workCategoryEntity2.name) , result.map { it.name })
    }


    companion object{
        const val updatedTestWorkCategoryName = "데드리프트"
        const val testId = 1L
    }


}