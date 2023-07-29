package com.gradation.lift.database.test

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.gradation.lift.database.dao.WorkPartDao
import com.gradation.lift.database.data.TestEntityDataGenerator.TEST_DATABASE
import com.gradation.lift.database.data.TestEntityDataGenerator.WorkPart.workPartEntity1
import com.gradation.lift.database.data.TestEntityDataGenerator.WorkPart.workPartEntityList
import com.gradation.lift.database.di.LiftDatabase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@ExperimentalCoroutinesApi
@SmallTest
@HiltAndroidTest
class WorkPartDaoTest {

    @Inject
    @Named(TEST_DATABASE)
    lateinit var database: LiftDatabase
    private lateinit var workPartDao: WorkPartDao

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

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
    fun testInsertWorkPart() = runTest {
        workPartDao.insertWorkPart(workPartEntity = workPartEntity1)
        workPartDao.insertAllWorkPart(workPartEntity = workPartEntityList.toTypedArray())

        with(workPartDao.getAllWorkPart().first()) {
            Truth.assertThat(this.size).isEqualTo(2)
            Truth.assertThat(this.map { it.name }.toSet()).isEqualTo(
                workPartEntityList.map { it.name }.toSet()
            )

        }

    }

    @Test
    fun testDeleteWorkPart() = runTest {
        workPartDao.insertAllWorkPart(workPartEntity = workPartEntityList.toTypedArray())
        workPartDao.deleteWorkPart(workPartEntity1)
        with(workPartDao.getAllWorkPart().first().size) {
            Truth.assertThat(this).isEqualTo(1)
        }
        workPartDao.deleteAllWorkPart()
        with(workPartDao.getAllWorkPart().first().size) {
            Truth.assertThat(this).isEqualTo(0)
        }


    }

}

