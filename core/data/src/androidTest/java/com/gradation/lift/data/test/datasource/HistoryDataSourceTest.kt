package com.gradation.lift.data.test.datasource

import com.google.common.truth.Truth
import com.gradation.lift.data.fake.datasource.FakeHistoryDataSource
import com.gradation.lift.model.utils.DefaultDataGenerator
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.datasource.history.HistoryDataSource
import com.gradation.lift.data.utils.TestReturnState
import com.gradation.lift.model.utils.ModelDataGenerator.History.createHistoryModel
import com.gradation.lift.model.utils.ModelDataGenerator.History.historyModelList
import com.gradation.lift.test.rule.CoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi

class HistoryDataSourceTest {


    @get:Rule
    var coroutineRule = CoroutineRule()

    private lateinit var dataSource: HistoryDataSource


    @Test
    fun getHistoryDataSource() = runTest {
        dataSource =
            FakeHistoryDataSource(testReturnState = TestReturnState.Success)
        Truth.assertThat(
            NetworkResult.Success(historyModelList)
        ).isEqualTo(
            dataSource.getHistory().first()
        )

        dataSource =
            FakeHistoryDataSource(testReturnState = TestReturnState.Fail)
        Truth.assertThat(
            NetworkResult.Fail(DefaultDataGenerator.FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            dataSource.getHistory().first()
        )
    }

    @Test
    fun getHistoryByHistoryIdDataSource() = runTest {
        dataSource =
            FakeHistoryDataSource(testReturnState = TestReturnState.Success)
        Truth.assertThat(
            NetworkResult.Success(historyModelList)
        ).isEqualTo(
            dataSource.getHistoryByHistoryId(setOf(12, 13, 14)).first()
        )

        dataSource =
            FakeHistoryDataSource(testReturnState = TestReturnState.Fail)
        Truth.assertThat(
            NetworkResult.Fail(DefaultDataGenerator.FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            dataSource.getHistoryByHistoryId(setOf(12, 13, 14)).first()
        )
    }


    @Test
    fun createHistoryDataSource() = runTest {
        dataSource =
            FakeHistoryDataSource(testReturnState = TestReturnState.Success)
        Truth.assertThat(
            NetworkResult.Success(Unit)
        ).isEqualTo(
            dataSource.createHistory(createHistoryModel).first()
        )
        dataSource =
            FakeHistoryDataSource(testReturnState = TestReturnState.Fail)
        Truth.assertThat(
            NetworkResult.Fail(DefaultDataGenerator.FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            dataSource.createHistory(createHistoryModel).first()
        )
    }

    @Test
    fun deleteHistoryDataSource() = runTest {
        dataSource =
            FakeHistoryDataSource(testReturnState = TestReturnState.Success)
        Truth.assertThat(
            NetworkResult.Success(Unit)
        ).isEqualTo(
            dataSource.deleteHistory(1).first()
        )

        dataSource =
            FakeHistoryDataSource(testReturnState = TestReturnState.Fail)
        Truth.assertThat(
            NetworkResult.Fail(DefaultDataGenerator.FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            dataSource.deleteHistory(1).first()
        )
    }


}