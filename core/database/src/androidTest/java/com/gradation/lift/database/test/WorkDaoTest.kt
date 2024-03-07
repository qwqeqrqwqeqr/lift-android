package com.gradation.lift.database.test

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.gradation.lift.database.LiftDatabase
import com.gradation.lift.database.dao.WorkDao
import com.gradation.lift.database.data.TestDataGenerator.TEST_DATABASE
import com.gradation.lift.database.data.TestDataGenerator.Work.WORK_ENTITY
import com.gradation.lift.database.data.TestDataGenerator.Work.WORK_ROUTINE_ENTITY
import com.gradation.lift.model.model.work.CheckedWorkSetInfo
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
class WorkDaoTest {


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named(TEST_DATABASE)
    lateinit var database: LiftDatabase
    private lateinit var workDao: WorkDao

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
    fun testWorkInsertAndDelete() = runTest {

        with(workDao.getAllWork().first()) {
            Truth.assertThat(this.size).isEqualTo(0)
        }
        with(workDao.existWork().first()) {
            Truth.assertThat(this).isEqualTo(false)
        }

        /**
         * insert item
         */
        workDao.insert(
            workEntity = WORK_ENTITY,
            workRoutineEntity = listOf(WORK_ROUTINE_ENTITY)
        )

        with(workDao.getAllWork().first()) {
            Truth.assertThat(this.keys.size).isEqualTo(1)
            Truth.assertThat(this.values.size).isEqualTo(1)

            with(this.entries.first().key) {
                Truth.assertThat(this.id).isEqualTo(WORK_ENTITY.id)
                Truth.assertThat(this.restTime).isEqualTo(WORK_ENTITY.restTime)
                Truth.assertThat(this.totalTime).isEqualTo(WORK_ENTITY.totalTime)
                Truth.assertThat(this.workTime).isEqualTo(WORK_ENTITY.workTime)
                Truth.assertThat(this.checkedWorkSetInfoList)
                    .isEqualTo(WORK_ENTITY.checkedWorkSetInfoList)
                Truth.assertThat(this.usedRoutineSetIdList)
                    .isEqualTo(WORK_ENTITY.usedRoutineSetIdList)
            }

            with(this.entries.first().value.first()) {
                Truth.assertThat(this.workId).isEqualTo(WORK_ROUTINE_ENTITY.workId)
                Truth.assertThat(this.workCategoryId).isEqualTo(WORK_ROUTINE_ENTITY.workCategoryId)
                Truth.assertThat(this.workCategoryName)
                    .isEqualTo(WORK_ROUTINE_ENTITY.workCategoryName)
                Truth.assertThat(this.workPart).isEqualTo(WORK_ROUTINE_ENTITY.workPart)
                Truth.assertThat(this.workCategoryName)
                    .isEqualTo(WORK_ROUTINE_ENTITY.workCategoryName)
                Truth.assertThat(this.workSetList).isEqualTo(WORK_ROUTINE_ENTITY.workSetList)
            }
        }

        with(workDao.existWork().first()) {
            Truth.assertThat(this).isEqualTo(true)
        }
        /**
         * delete item
         */
        workDao.deleteAllWork()


        with(workDao.getAllWork().first()) {
            Truth.assertThat(this.size).isEqualTo(0)
        }
        with(workDao.existWork().first()) {
            Truth.assertThat(this).isEqualTo(false)
        }
    }

    @Test
    fun testUpdateWork() = runTest {

        with(workDao.getAllWork().first()) {
            Truth.assertThat(this.size).isEqualTo(0)
        }

        /**
         * insert item
         */
        workDao.insert(
            workEntity = WORK_ENTITY,
            workRoutineEntity = listOf(WORK_ROUTINE_ENTITY)
        )

        workDao.updateWork(
            WORK_ENTITY.copy(
                usedRoutineSetIdList = emptyList(),
                checkedWorkSetInfoList = emptyList()
            )
        )

        with(workDao.getAllWork().first()) {
            Truth.assertThat(this.keys.size).isEqualTo(1)
            Truth.assertThat(this.values.size).isEqualTo(1)

            with(this.entries.first().key) {
                Truth.assertThat(this.id).isEqualTo(WORK_ENTITY.id)
                Truth.assertThat(this.restTime).isEqualTo(WORK_ENTITY.restTime)
                Truth.assertThat(this.totalTime).isEqualTo(WORK_ENTITY.totalTime)
                Truth.assertThat(this.workTime).isEqualTo(WORK_ENTITY.workTime)
                Truth.assertThat(this.checkedWorkSetInfoList)
                    .isEqualTo(emptyList<CheckedWorkSetInfo>())
                Truth.assertThat(this.usedRoutineSetIdList)
                    .isEqualTo(emptyList<Int>())
            }
        }
    }

}