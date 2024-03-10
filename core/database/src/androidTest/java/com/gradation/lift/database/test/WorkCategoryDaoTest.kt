package com.gradation.lift.database.test

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.gradation.lift.database.LiftDatabase
import com.gradation.lift.database.dao.WorkCategoryDao
import com.gradation.lift.database.data.TestDataGenerator.TEST_DATABASE
import com.gradation.lift.database.data.TestDataGenerator.WorkCategory.WORK_CATEGORY_ENTITY
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

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
    fun tearDown() {
        database.close()
    }

    @Test
    fun testWorkCategoryInsertAndDelete() = runTest {

        Truth.assertThat(workCategoryDao.getAllWorkCategory().first().size).isEqualTo(0)

        /**
         * insert item
         */
        workCategoryDao.insertAllWorkCategory(workCategoryEntity = listOf(WORK_CATEGORY_ENTITY).toTypedArray())

        with(workCategoryDao.getAllWorkCategory().first()) {
            Truth.assertThat(this.size).isEqualTo(1)
            Truth.assertThat(this.first().id).isEqualTo(WORK_CATEGORY_ENTITY.id)
            Truth.assertThat(this.first().name).isEqualTo(WORK_CATEGORY_ENTITY.name)
            Truth.assertThat(this.first().equipment).isEqualTo(WORK_CATEGORY_ENTITY.equipment)
            Truth.assertThat(this.first().searchTag).isEqualTo(WORK_CATEGORY_ENTITY.searchTag)
            Truth.assertThat(this.first().workPart).isEqualTo(WORK_CATEGORY_ENTITY.workPart)
            Truth.assertThat(this.first().introduce).isEqualTo(WORK_CATEGORY_ENTITY.introduce)
            Truth.assertThat(this.first().description).isEqualTo(WORK_CATEGORY_ENTITY.description)
            Truth.assertThat(this.first().sequence).isEqualTo(WORK_CATEGORY_ENTITY.sequence)
            Truth.assertThat(this.first().effect).isEqualTo(WORK_CATEGORY_ENTITY.effect)
            Truth.assertThat(this.first().caution).isEqualTo(WORK_CATEGORY_ENTITY.caution)
        }
        /**
         * delete item
         */
        workCategoryDao.deleteAllWorkCategory()
        Truth.assertThat(workCategoryDao.getAllWorkCategory().first().size).isEqualTo(0)
    }

}