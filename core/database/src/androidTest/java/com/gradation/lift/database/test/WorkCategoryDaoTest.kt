package com.gradation.lift.database.test

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.gradation.lift.database.dao.WorkCategoryDao
import com.gradation.lift.database.data.TestEntityDataGenerator.TEST_DATABASE
import com.gradation.lift.database.data.TestEntityDataGenerator.WorkCategory.workCategoryEntity1
import com.gradation.lift.database.data.TestEntityDataGenerator.WorkCategory.workCategoryEntity2
import com.gradation.lift.database.data.TestEntityDataGenerator.WorkCategory.workCategoryEntityList
import com.gradation.lift.database.di.LiftDatabase
import com.gradation.lift.database.model.work_category.WorkCategoryEntity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.*
import javax.inject.Inject
import javax.inject.Named

@ExperimentalCoroutinesApi
@SmallTest
@HiltAndroidTest
class WorkCategoryDaoTest {


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named(TEST_DATABASE)
    lateinit var database: LiftDatabase
    private lateinit var workCategoryDao: WorkCategoryDao

    @Before
    fun setup() {
        hiltRule.inject()
        workCategoryDao = database.workCategoryDao()
    }

    @After
    fun teardown() {
        database.close()
    }


    @Test
    fun testInsertWorkCategory() = runTest {
        workCategoryDao.insertWorkCategory(workCategoryEntity1)
        workCategoryDao.insertAllWorkCategory(*workCategoryEntityList.toTypedArray())
        val result: List<WorkCategoryEntity> = workCategoryDao.getAllWorkCategory().first()
        Truth.assertThat(result.size).isEqualTo(2)
        Truth.assertThat(result.map { it.id }.toSet()).isEqualTo(setOf(1, 2))
    }

    @Test
    fun testUpdateWorkCategory() = runTest {
        workCategoryDao.insertWorkCategory(workCategoryEntity1)
        workCategoryDao.updateWorkCategory(workCategoryEntity1.copy(name = workCategoryEntity2.name))
        val result: List<WorkCategoryEntity> = workCategoryDao.getAllWorkCategory().first()
        Truth.assertThat(result.size).isEqualTo(1)
        Truth.assertThat(result.first().name).isEqualTo(workCategoryEntity2.name)
    }


    @Test
    fun testDeleteWorkCategory() = runTest {
        workCategoryDao.insertWorkCategory(workCategoryEntity1)
        workCategoryDao.deleteWorkCategory(workCategoryEntity1)
        val result: List<WorkCategoryEntity> = workCategoryDao.getAllWorkCategory().first()
        Truth.assertThat(result.size).isEqualTo(0)
    }

    @Test
    fun testDeleteAllWorkCategory() = runTest {
        workCategoryDao.insertWorkCategory(workCategoryEntity1)
        workCategoryDao.insertWorkCategory(workCategoryEntity2)
        workCategoryDao.deleteAllWorkCategory()
        val result: List<WorkCategoryEntity> = workCategoryDao.getAllWorkCategory().first()
        Truth.assertThat(result.size).isEqualTo(0)
    }


    @Test
    fun testGetWorkCategoryByWorkPart() = runTest {
        workCategoryDao.insertAllWorkCategory(workCategoryEntity1, workCategoryEntity2)
        val result: WorkCategoryEntity =
            workCategoryDao.getWorkCategoryByWorkPart(workCategoryEntity1.workPart.name).first()
        Truth.assertThat(result.workPart.name).isEqualTo(workCategoryEntity1.workPart.name)
    }

}