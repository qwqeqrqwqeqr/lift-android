package com.gradation.lift.database.test

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.gradation.lift.database.LiftDatabase
import com.gradation.lift.database.dao.WorkPartDao
import com.gradation.lift.database.data.TestDataGenerator.TEST_DATABASE
import com.gradation.lift.database.data.TestDataGenerator.WorkPart.WORK_PART_ENTITY
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
class WorkPartDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named(TEST_DATABASE)
    lateinit var database: LiftDatabase
    private lateinit var workPartDao: WorkPartDao


    @Before
    fun setup() {
        hiltRule.inject()
        workPartDao = database.workPartDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun testWorkPartInsertAndDelete() = runTest {

        Truth.assertThat(workPartDao.getAllWorkPart().first().size).isEqualTo(0)

        /**
         * insert item
         */
        workPartDao.insertAllWorkPart(workPartEntity = listOf(WORK_PART_ENTITY).toTypedArray())

        with(workPartDao.getAllWorkPart().first()) {
            Truth.assertThat(this.size).isEqualTo(1)
            Truth.assertThat(this.first().id).isEqualTo(WORK_PART_ENTITY.id)
            Truth.assertThat(this.first().name).isEqualTo(WORK_PART_ENTITY.name)
        }
        /**
         * delete item
         */
        workPartDao.deleteAllWorkPart()
        Truth.assertThat(workPartDao.getAllWorkPart().first().size).isEqualTo(0)
    }

}