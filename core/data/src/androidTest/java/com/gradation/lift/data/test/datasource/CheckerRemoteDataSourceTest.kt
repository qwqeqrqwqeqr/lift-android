package com.gradation.lift.data.test.datasource

import com.google.common.truth.Truth
import com.gradation.lift.data.fake.datasource.FakeCheckerRemoteDataSource
import com.gradation.lift.data.utils.TestReturnState
import com.gradation.lift.model.utils.DefaultDataGenerator
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_BOOLEAN_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_STRING_DATA
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.datasource.checker.CheckerRemoteDataSource
import com.gradation.lift.test.rule.CoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi

class CheckerRemoteDataSourceTest {


    @get:Rule
    var coroutineRule = CoroutineRule()

    private lateinit var dataSource: CheckerRemoteDataSource


    @Test
    fun checkDuplicateEmailDataSource() = runTest {
        dataSource =
            FakeCheckerRemoteDataSource(testReturnState = TestReturnState.Success)
        Truth.assertThat(
            NetworkResult.Success(FAKE_BOOLEAN_DATA, message = null)
        ).isEqualTo(
            dataSource.checkDuplicateEmail(FAKE_STRING_DATA).first()
        )

        dataSource =
            FakeCheckerRemoteDataSource(testReturnState = TestReturnState.Fail)
        Truth.assertThat(
            NetworkResult.Fail(DefaultDataGenerator.FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            dataSource.checkDuplicateEmail(FAKE_STRING_DATA).first()
        )
    }

    @Test
    fun checkDuplicateNameDataSource() = runTest {
        dataSource =
            FakeCheckerRemoteDataSource(testReturnState = TestReturnState.Success)
        Truth.assertThat(
            NetworkResult.Success(FAKE_BOOLEAN_DATA, message = null)
        ).isEqualTo(
            dataSource.checkDuplicateName(FAKE_STRING_DATA).first()
        )
        dataSource =
            FakeCheckerRemoteDataSource(testReturnState = TestReturnState.Fail)
        Truth.assertThat(
            NetworkResult.Fail(DefaultDataGenerator.FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            dataSource.checkDuplicateName(FAKE_STRING_DATA).first()
        )
    }
}