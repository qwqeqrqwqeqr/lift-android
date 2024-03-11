package com.gradation.lift.data.test.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.gradation.lift.common.common.DispatcherProvider
import com.gradation.lift.common.model.DataState
import com.gradation.lift.data.di.TestDispatcher
import com.gradation.lift.data.fake.datasource.FakeHistoryRemoteDataSource
import com.gradation.lift.data.utils.TestReturnState
import com.gradation.lift.domain.repository.HistoryRepository
import com.gradation.lift.model.utils.DefaultDataGenerator
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_INT_DATA
import com.gradation.lift.model.utils.ModelDataGenerator.History.CREATE_HISTORY_MODEL
import com.gradation.lift.model.utils.ModelDataGenerator.History.HISTORY_MODEL
import com.gradation.lift.network.datasource.history.HistoryRemoteDataSource
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

    private lateinit var failDataSource: HistoryRemoteDataSource
    private lateinit var successDataSource: HistoryRemoteDataSource
    private lateinit var successRepository: HistoryRepository
    private lateinit var failRepository: HistoryRepository

    private lateinit var testDispatcher: DispatcherProvider


    @get:Rule
    var coroutineRule = CoroutineRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        testDispatcher = TestDispatcher.testDispatchers()


        failDataSource = FakeHistoryRemoteDataSource(TestReturnState.Fail)
        successDataSource = FakeHistoryRemoteDataSource(TestReturnState.Success)


    }



    @Test
    fun getHistoryDataSource() = runTest {
        Truth.assertThat(
            DataState.Success(listOf(HISTORY_MODEL))
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
            DataState.Success(listOf(HISTORY_MODEL))
        ).isEqualTo(
            successRepository.getHistoryByHistoryId(setOf(FAKE_INT_DATA)).first()
        )

        Truth.assertThat(
            DataState.Fail(DefaultDataGenerator.FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            failRepository.getHistoryByHistoryId(setOf(FAKE_INT_DATA)).first()
        )
    }

    @Test
    fun createHistory() = runTest {
        Truth.assertThat(
            DataState.Success(Unit)
        ).isEqualTo(
            successRepository.createHistory(CREATE_HISTORY_MODEL).first()
        )

        Truth.assertThat(
            DataState.Fail(DefaultDataGenerator.FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            failRepository.createHistory(CREATE_HISTORY_MODEL).first()
        )
    }


    @Test
    fun deleteHistory() = runTest {
        Truth.assertThat(
            DataState.Success(Unit)
        ).isEqualTo(
            successRepository.deleteHistory(FAKE_INT_DATA).first()
        )

        Truth.assertThat(
            DataState.Fail(DefaultDataGenerator.FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            failRepository.deleteHistory(FAKE_INT_DATA).first()
        )
    }
}