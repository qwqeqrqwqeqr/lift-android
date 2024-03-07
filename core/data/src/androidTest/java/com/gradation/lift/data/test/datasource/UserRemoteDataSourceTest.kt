package com.gradation.lift.data.test.datasource

import com.google.common.truth.Truth
import com.gradation.lift.data.fake.datasource.FakeUserRemoteDataSource
import com.gradation.lift.data.utils.TestReturnState
import com.gradation.lift.model.utils.DefaultDataGenerator
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_BOOLEAN_DATA
import com.gradation.lift.model.utils.ModelDataGenerator.User.USER_DETAIL_MODEL
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.datasource.user.UserRemoteDataSource
import com.gradation.lift.test.rule.CoroutineRule
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi

class UserRemoteDataSourceTest {


    @get:Rule
    var coroutineRule = CoroutineRule()

    private lateinit var dataSource: UserRemoteDataSource

    @Test
    fun getUserDetailDataSource() = runTest {
        dataSource =
            FakeUserRemoteDataSource(testReturnState = TestReturnState.Success)
        TestCase.assertEquals(
            NetworkResult.Success(USER_DETAIL_MODEL),
            dataSource.getUserDetail().first()
        )

        dataSource =
            FakeUserRemoteDataSource(testReturnState = TestReturnState.Fail)
        Truth.assertThat(
            NetworkResult.Fail(DefaultDataGenerator.FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            dataSource.getUserDetail().first()
        )
    }
    @Test
    fun createUserDetailDataSource() = runTest {
        dataSource =
            FakeUserRemoteDataSource(testReturnState = TestReturnState.Success)
        TestCase.assertEquals(
            NetworkResult.Success(Unit),
            dataSource.createUserDetail(USER_DETAIL_MODEL).first()
        )

        dataSource =
            FakeUserRemoteDataSource(testReturnState = TestReturnState.Fail)
        Truth.assertThat(
            NetworkResult.Fail(DefaultDataGenerator.FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            dataSource.createUserDetail(USER_DETAIL_MODEL).first()
        )
    }
    @Test
    fun updateUserDetailDataSource() = runTest {
        dataSource =
            FakeUserRemoteDataSource(testReturnState = TestReturnState.Success)
        TestCase.assertEquals(
            NetworkResult.Success(Unit),
            dataSource.updateUserDetail(USER_DETAIL_MODEL).first()
        )

        dataSource =
            FakeUserRemoteDataSource(testReturnState = TestReturnState.Fail)
        Truth.assertThat(
            NetworkResult.Fail(DefaultDataGenerator.FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            dataSource.updateUserDetail(USER_DETAIL_MODEL).first()
        )
    }
    @Test
    fun existUserDetailDataSource() = runTest {
        dataSource =
            FakeUserRemoteDataSource(testReturnState = TestReturnState.Success)
        TestCase.assertEquals(
            NetworkResult.Success(FAKE_BOOLEAN_DATA),
            dataSource.existUserDetail().first()
        )

        dataSource =
            FakeUserRemoteDataSource(testReturnState = TestReturnState.Fail)
        Truth.assertThat(
            NetworkResult.Fail(DefaultDataGenerator.FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            dataSource.existUserDetail().first()
        )
    }

}