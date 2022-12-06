package com.gradation.lift.database.test

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.gradation.lift.database.dao.WorkCategoryDao
import com.gradation.lift.database.di.LiftDatabase
import com.gradation.lift.database.util.TestDataGenerator
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



}