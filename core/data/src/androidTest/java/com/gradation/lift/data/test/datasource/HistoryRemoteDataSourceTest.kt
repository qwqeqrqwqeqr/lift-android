package com.gradation.lift.data.test.datasource

import com.google.common.truth.Truth
import com.gradation.lift.data.fake.datasource.FakeHistoryRemoteDataSource
import com.gradation.lift.data.utils.TestReturnState
import com.gradation.lift.model.utils.DefaultDataGenerator
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_INT_DATA
import com.gradation.lift.model.utils.ModelDataGenerator.History.CREATE_HISTORY_MODEL
import com.gradation.lift.model.utils.ModelDataGenerator.History.HISTORY_MODEL
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.datasource.history.HistoryRemoteDataSource
import com.gradation.lift.test.rule.CoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi

class HistoryRemoteDataSourceTest {


    @get:Rule
    var coroutineRule = CoroutineRule()

    private lateinit var dataSource: HistoryRemoteDataSource


    @Test
    fun getHistoryDataSource() = runTest {
        dataSource =
            FakeHistoryRemoteDataSource(testReturnState = TestReturnState.Success)
        Truth.assertThat(
            NetworkResult.Success(listOf(HISTORY_MODEL))
        ).isEqualTo(
            dataSource.getHistory().first()
        )

        dataSource =
            FakeHistoryRemoteDataSource(testReturnState = TestReturnState.Fail)
        Truth.assertThat(
            NetworkResult.Fail(DefaultDataGenerator.FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            dataSource.getHistory().first()
        )
    }

    @Test
    fun getHistoryByHistoryIdDataSource() = runTest {
        dataSource =
            FakeHistoryRemoteDataSource(testReturnState = TestReturnState.Success)
        Truth.assertThat(
            NetworkResult.Success(listOf(HISTORY_MODEL))
        ).isEqualTo(
            dataSource.getHistoryByHistoryId(setOf(FAKE_INT_DATA)).first()
        )

        dataSource =
            FakeHistoryRemoteDataSource(testReturnState = TestReturnState.Fail)
        Truth.assertThat(
            NetworkResult.Fail(DefaultDataGenerator.FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            dataSource.getHistoryByHistoryId(setOf(FAKE_INT_DATA)).first()
        )
    }


    @Test
    fun createHistoryDataSource() = runTest {
        dataSource =
            FakeHistoryRemoteDataSource(testReturnState = TestReturnState.Success)
        Truth.assertThat(
            NetworkResult.Success(Unit)
        ).isEqualTo(
            dataSource.createHistory(CREATE_HISTORY_MODEL).first()
        )
        dataSource =
            FakeHistoryRemoteDataSource(testReturnState = TestReturnState.Fail)
        Truth.assertThat(
            NetworkResult.Fail(DefaultDataGenerator.FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            dataSource.createHistory(CREATE_HISTORY_MODEL).first()
        )
    }

    @Test
    fun deleteHistoryDataSource() = runTest {
        dataSource =
            FakeHistoryRemoteDataSource(testReturnState = TestReturnState.Success)
        Truth.assertThat(
            NetworkResult.Success(Unit)
        ).isEqualTo(
            dataSource.deleteHistory(FAKE_INT_DATA).first()
        )

        dataSource =
            FakeHistoryRemoteDataSource(testReturnState = TestReturnState.Fail)
        Truth.assertThat(
            NetworkResult.Fail(DefaultDataGenerator.FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            dataSource.deleteHistory(FAKE_INT_DATA).first()
        )
    }


}