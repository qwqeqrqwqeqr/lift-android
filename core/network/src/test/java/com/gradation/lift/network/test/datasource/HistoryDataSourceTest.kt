package com.gradation.lift.network.test.datasource

import com.google.common.truth.Truth
import com.gradation.lift.model.history.CreateHistory
import com.gradation.lift.model.history.History
import com.gradation.lift.network.common.APIResult
import com.gradation.lift.network.datasource.HistoryDataSource
import com.gradation.lift.network.datasource.RoutineDataSource
import com.gradation.lift.network.fake.FakeCheckerDataSource
import com.gradation.lift.network.fake.FakeHistoryDataSource
import com.gradation.lift.network.utils.TestReturnState
import com.gradation.lift.test.data.TestDefaultDataGenerator
import com.gradation.lift.test.data.TestModelDataGenerator
import com.gradation.lift.test.data.TestModelDataGenerator.History.createHistoryModel
import com.gradation.lift.test.data.TestModelDataGenerator.History.historyModelList
import com.gradation.lift.test.rule.CoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
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
    fun testGetHistoryDataSource() = runTest {
        dataSource = FakeHistoryDataSource(testReturnState = TestReturnState.Success)
        Truth.assertThat(
            APIResult.Success(historyModelList)
        ).isEqualTo(
            dataSource.getHistory().first()
        )
    }

    @Test
    fun testGetHistoryByHistoryIdDataSource() = runTest {
        dataSource = FakeHistoryDataSource(testReturnState = TestReturnState.Success)
        Truth.assertThat(
            APIResult.Success(historyModelList)
        ).isEqualTo(
            dataSource.getHistoryByHistoryId(setOf(12,13,14)).first()
        )
    }


    @Test
    fun testCreateHistoryDataSource() = runTest {
        dataSource = FakeHistoryDataSource(testReturnState = TestReturnState.Success)
        Truth.assertThat(
            APIResult.Success(TestDefaultDataGenerator.FAKE_BOOLEAN_DATA)
        ).isEqualTo(
            dataSource.createHistory(createHistoryModel).first()
        )
    }
    @Test
    fun testDeleteHistoryDataSource() = runTest {
        dataSource = FakeHistoryDataSource(testReturnState = TestReturnState.Success)
        Truth.assertThat(
            APIResult.Success(TestDefaultDataGenerator.FAKE_BOOLEAN_DATA)
        ).isEqualTo(
            dataSource.deleteHistory(1).first()
        )
    }



}