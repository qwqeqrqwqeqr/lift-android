package com.gradation.lift.database.test

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.gradation.lift.database.dao.WorkCategoryDao
import com.gradation.lift.database.data.TestEntityDataGenerator.TEST_DATABASE
import com.gradation.lift.database.data.TestEntityDataGenerator.WorkCategory.workCategoryEntity1
import com.gradation.lift.database.data.TestEntityDataGenerator.WorkCategory.workCategoryEntityList
import com.gradation.lift.database.data.TestEntityDataGenerator.WorkPart.workPartEntityList
import com.gradation.lift.database.di.LiftDatabase
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
        with(workCategoryDao.getAllWorkCategory().first()){
            Truth.assertThat(this.size).isEqualTo(2)
            Truth.assertThat(this.map { it.id }.toSet())
                .isEqualTo(workCategoryEntityList.map { it.id }.toSet())
            Truth.assertThat(this.map { it.workPart }.toSet())
                .isEqualTo(workPartEntityList.map { it.name }.toSet())
        }

    }


    @Test
    fun testDeleteWorkCategory() = runTest {
        workCategoryDao.insertWorkCategory(workCategoryEntity1)
        workCategoryDao.deleteWorkCategory(workCategoryEntity1)
        with(workCategoryDao.getAllWorkCategory().first()){
            Truth.assertThat(this.size).isEqualTo(0)
        }
    }

    @Test
    fun testDeleteAllWorkCategory() = runTest {
        workCategoryDao.insertWorkCategory(workCategoryEntity1)
        workCategoryDao.deleteAllWorkCategory()
        with(workCategoryDao.getAllWorkCategory().first()) {
            Truth.assertThat(this.size).isEqualTo(0)
        }

    }


}