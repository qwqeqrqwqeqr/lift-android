package com.gradation.lift.data.test.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.gradation.lift.common.model.DataState
import com.gradation.lift.data.fake.datasource.FakeHistoryDataSource
import com.gradation.lift.data.repository.DefaultHistoryRepository
import com.gradation.lift.data.utils.TestReturnState
import com.gradation.lift.domain.repository.HistoryRepository
import com.gradation.lift.model.utils.DefaultDataGenerator
import com.gradation.lift.model.utils.ModelDataGenerator
import com.gradation.lift.network.datasource.history.HistoryDataSource
import com.gradation.lift.test.rule.CoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
@SmallTest

class HistoryRepositoryTest {

    private lateinit var failDataSource: HistoryDataSource
    private lateinit var successDataSource: HistoryDataSource
    private lateinit var successRepository: HistoryRepository
    private lateinit var failRepository: HistoryRepository


    @get:Rule
    var coroutineRule = CoroutineRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        failDataSource = FakeHistoryDataSource(TestReturnState.Fail)
        successDataSource = FakeHistoryDataSource(TestReturnState.Success)

        successRepository = DefaultHistoryRepository(successDataSource)
        failRepository = DefaultHistoryRepository(failDataSource)
    }



    @Test
    fun getHistoryDataSource() = runTest {
        Truth.assertThat(
            DataState.Success(ModelDataGenerator.History.historyModelList)
        ).isEqualTo(
            successRepository.getHistory().first()
        )

        Truth.assertThat(
            DataState.Fail(DefaultDataGenerator.FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            failRepository.getHistory().first()
        )
    }

    @Test
    fun getHistoryByHistoryIdDataSource() = runTest {
        Truth.assertThat(
            DataState.Success(ModelDataGenerator.History.historyModelList)
        ).isEqualTo(
            successRepository.getHistoryByHistoryId(setOf(12, 13, 14)).first()
        )

        Truth.assertThat(
            DataState.Fail(DefaultDataGenerator.FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            failRepository.getHistoryByHistoryId(setOf(12, 13, 14)).first()
        )
    }

    @Test
    fun createHistory() = runTest {
        Truth.assertThat(
            DataState.Success(Unit)
        ).isEqualTo(
            successRepository.createHistory(ModelDataGenerator.History.createHistoryModel).first()
        )

        Truth.assertThat(
            DataState.Fail(DefaultDataGenerator.FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            failRepository.createHistory(ModelDataGenerator.History.createHistoryModel).first()
        )
    }


    @Test
    fun deleteHistory() = runTest {
        Truth.assertThat(
            DataState.Success(Unit)
        ).isEqualTo(
            successRepository.deleteHistory(1).first()
        )

        Truth.assertThat(
            DataState.Fail(DefaultDataGenerator.FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            failRepository.deleteHistory(1).first()
        )
    }
}