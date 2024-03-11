package com.gradation.lift.database.test

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.gradation.lift.database.LiftDatabase
import com.gradation.lift.database.dao.HistoryDao
import com.gradation.lift.database.data.TestDataGenerator.History.HISTORY_ENTITY
import com.gradation.lift.database.data.TestDataGenerator.History.HISTORY_ROUTINE_ENTITY
import com.gradation.lift.database.data.TestDataGenerator.TEST_DATABASE
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
class HistoryDaoTest {


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named(TEST_DATABASE)
    lateinit var database: LiftDatabase
    private lateinit var historyDao: HistoryDao

    @Before
    fun setup() {
        hiltRule.inject()
        historyDao = database.historyDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun testHistoryInsertAndDelete() = runTest {

        with(historyDao.getAllHistory().first()) {
            Truth.assertThat(this.size).isEqualTo(0)
        }

        /**
         * insert item
         */
        historyDao.insertAll(
            historyEntity = listOf(HISTORY_ENTITY),
            historyRoutineEntity = listOf(HISTORY_ROUTINE_ENTITY)
        )

        with(historyDao.getAllHistory().first()) {
            Truth.assertThat(this.keys.size).isEqualTo(1)
            Truth.assertThat(this.values.size).isEqualTo(1)

            with(this.entries.first().key) {
                Truth.assertThat(this.id).isEqualTo(HISTORY_ENTITY.id)
                Truth.assertThat(this.comment).isEqualTo(HISTORY_ENTITY.comment)
                Truth.assertThat(this.historyTimeStamp).isEqualTo(HISTORY_ENTITY.historyTimeStamp)
                Truth.assertThat(this.score).isEqualTo(HISTORY_ENTITY.score)
                Truth.assertThat(this.restTime).isEqualTo(HISTORY_ENTITY.restTime)
                Truth.assertThat(this.progress).isEqualTo(HISTORY_ENTITY.progress)
                Truth.assertThat(this.totalTime).isEqualTo(HISTORY_ENTITY.totalTime)
                Truth.assertThat(this.workTime).isEqualTo(HISTORY_ENTITY.workTime)
            }

            with(this.entries.first().value.first()) {
                Truth.assertThat(this.historyId).isEqualTo(HISTORY_ROUTINE_ENTITY.historyId)
                Truth.assertThat(this.id).isEqualTo(HISTORY_ROUTINE_ENTITY.id)
                Truth.assertThat(this.workCategoryId)
                    .isEqualTo(HISTORY_ROUTINE_ENTITY.workCategoryId)
                Truth.assertThat(this.workPart).isEqualTo(HISTORY_ROUTINE_ENTITY.workPart)
                Truth.assertThat(this.workCategoryName)
                    .isEqualTo(HISTORY_ROUTINE_ENTITY.workCategoryName)
                Truth.assertThat(this.workSetList).isEqualTo(HISTORY_ROUTINE_ENTITY.workSetList)
            }
        }

        /**
         * delete item
         */
        historyDao.deleteAllHistory()


        with(historyDao.getAllHistory().first()) {
            Truth.assertThat(this.size).isEqualTo(0)
        }
    }

    @Test
    fun testUpdateHistoryInfo() = runTest {

        with(historyDao.getAllHistory().first()) {
            Truth.assertThat(this.size).isEqualTo(0)
        }

        /**
         * insert item
         */
        historyDao.insertAll(
            historyEntity = listOf(HISTORY_ENTITY),
            historyRoutineEntity = listOf(HISTORY_ROUTINE_ENTITY)
        )

        historyDao.updateHistoryInfo(
            historyId = HISTORY_ENTITY.id,
            comment = HISTORY_ENTITY.comment + HISTORY_ENTITY.comment,
            score = HISTORY_ENTITY.score?.minus(1)
        )

        with(historyDao.getAllHistory().first()) {
            Truth.assertThat(this.entries.first().key.comment)
                .isEqualTo(HISTORY_ENTITY.comment + HISTORY_ENTITY.comment)
            Truth.assertThat(this.entries.first().key.score)
                .isEqualTo(HISTORY_ENTITY.score?.minus(1))
        }
    }

}