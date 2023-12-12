package com.gradation.lift.database.test

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.gradation.lift.database.dao.HistoryDao
import com.gradation.lift.database.data.TestEntityDataGenerator.History.historyEntity1
import com.gradation.lift.database.data.TestEntityDataGenerator.History.historyEntity2
import com.gradation.lift.database.data.TestEntityDataGenerator.History.historyEntityList
import com.gradation.lift.database.data.TestEntityDataGenerator.History.historyRoutineEntity1
import com.gradation.lift.database.data.TestEntityDataGenerator.History.historyRoutineEntityList
import com.gradation.lift.database.data.TestEntityDataGenerator.TEST_DATABASE
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
class HistoryDaoTest {
    @Inject
    @Named(TEST_DATABASE)
    lateinit var database: LiftDatabase
    private lateinit var historyDao: HistoryDao

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

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
    fun testInsertAllHistory() = runTest {
        historyDao.insertAll(
            historyEntity = historyEntityList,
            historyRoutineEntity = historyRoutineEntityList
        )

        with(historyDao.getAllHistory().first()) {
            Truth.assertThat(this.size).isEqualTo(2)
            Truth.assertThat(this.keys.map { it.id }.toSet()).isEqualTo(setOf(1, 2))
        }
    }

    @Test
    fun testDeleteAllHistory() = runTest {
        historyDao.insertAll(
            historyEntity = historyEntityList,
            historyRoutineEntity = historyRoutineEntityList
        )
        historyDao.deleteAllHistory()
        Truth.assertThat(historyDao.getAllHistory().first().size).isEqualTo(0)
    }

    @Test
    fun testGetHistoryByHistoryId() = runTest {
        historyDao.insertAll(
            historyEntity = historyEntityList,
            historyRoutineEntity = historyRoutineEntityList
        )
        with(
            historyDao.getHistoryByHistoryId(
                setOf(
                    historyEntity1.id,
                    historyEntity2.id,
                    Int.MAX_VALUE
                )
            ).first()
        ) {
            Truth.assertThat(this.size).isEqualTo(2)
        }
    }

}