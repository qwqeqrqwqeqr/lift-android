package com.gradation.lift.database.test

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.gradation.lift.database.dao.WorkDao
import com.gradation.lift.database.data.TestEntityDataGenerator.TEST_DATABASE
import com.gradation.lift.database.data.TestEntityDataGenerator.Work.workEntity
import com.gradation.lift.database.data.TestEntityDataGenerator.Work.workRoutineEntity
import com.gradation.lift.database.data.TestEntityDataGenerator.Work.workRoutineEntityList
import com.gradation.lift.database.di.LiftDatabase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.LocalTime
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@ExperimentalCoroutinesApi
@SmallTest
@HiltAndroidTest
class WorkDaoTest {
    @Inject
    @Named(TEST_DATABASE)
    lateinit var database: LiftDatabase
    private lateinit var workDao: WorkDao

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()
        workDao = database.workDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun testInsertWork() = runTest {
        workDao.insert(workEntity = workEntity, workRoutineEntity = workRoutineEntityList)
        with(workDao.getAllWork().first()){
            Truth.assertThat(this.size).isEqualTo(1)
            Truth.assertThat(this.keys.map { it.id }.toSet()).isEqualTo(setOf(workEntity.id))
            Truth.assertThat(this.values.first().flatMap { it.workSetList }.toSet()).isEqualTo(
                workRoutineEntity.workSetList.toSet()
            )
        }

    }

    @Test
    fun testUpdateWork() = runTest {
        workDao.insert(workEntity = workEntity, workRoutineEntity = workRoutineEntityList)
        workDao.updateWorkRoutine(
            workRoutineEntity = workRoutineEntity.copy(
                workSetList = workRoutineEntity.workSetList.drop(
                    2
                )
            )
        )
        with(workDao.getAllWork().first()){
            Truth.assertThat(this.values.first().flatMap { it.workSetList }.size).isEqualTo(3)
        }

        workDao.updateWork(workEntity.copy(totalTime = LocalTime(23,29,59)))

        with(workDao.getAllWork().first()){
            Truth.assertThat(this.keys.first().totalTime).isEqualTo(LocalTime(23,29,59))
            Truth.assertThat(this.keys.size).isEqualTo(1)
        }
    }

}