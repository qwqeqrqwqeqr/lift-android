package com.gradation.lift.data.datasource

import com.google.common.truth.Truth
import com.gradation.lift.model.utils.DefaultDataGenerator
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.datasource.user.UserDataSource
import com.gradation.lift.data.utils.TestReturnState
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_BOOLEAN_DATA
import com.gradation.lift.model.utils.ModelDataGenerator.User.createUserDetailModel
import com.gradation.lift.model.utils.ModelDataGenerator.User.userDetailModel
import com.gradation.lift.test.rule.CoroutineRule
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi

class UserDataSourceTest {


    @get:Rule
    var coroutineRule = CoroutineRule()

    private lateinit var dataSource: UserDataSource

    @Test
    fun getUserDetailDataSource() = runTest {
        dataSource =
            com.gradation.lift.data.fake.FakeUserDataSource(testReturnState = TestReturnState.Success)
        TestCase.assertEquals(
            NetworkResult.Success(userDetailModel),
            dataSource.getUserDetail().first()
        )

        dataSource =
            com.gradation.lift.data.fake.FakeUserDataSource(testReturnState = TestReturnState.Fail)
        Truth.assertThat(
            NetworkResult.Fail(DefaultDataGenerator.FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            dataSource.getUserDetail().first()
        )
    }
    @Test
    fun createUserDetailDataSource() = runTest {
        dataSource =
            com.gradation.lift.data.fake.FakeUserDataSource(testReturnState = TestReturnState.Success)
        TestCase.assertEquals(
            NetworkResult.Success(Unit),
            dataSource.createUserDetail(createUserDetailModel).first()
        )

        dataSource =
            com.gradation.lift.data.fake.FakeUserDataSource(testReturnState = TestReturnState.Fail)
        Truth.assertThat(
            NetworkResult.Fail(DefaultDataGenerator.FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            dataSource.createUserDetail(createUserDetailModel).first()
        )
    }
    @Test
    fun updateUserDetailDataSource() = runTest {
        dataSource =
            com.gradation.lift.data.fake.FakeUserDataSource(testReturnState = TestReturnState.Success)
        TestCase.assertEquals(
            NetworkResult.Success(Unit),
            dataSource.updateUserDetail(userDetailModel).first()
        )

        dataSource =
            com.gradation.lift.data.fake.FakeUserDataSource(testReturnState = TestReturnState.Fail)
        Truth.assertThat(
            NetworkResult.Fail(DefaultDataGenerator.FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            dataSource.updateUserDetail(userDetailModel).first()
        )
    }
    @Test
    fun existUserDetailDataSource() = runTest {
        dataSource =
            com.gradation.lift.data.fake.FakeUserDataSource(testReturnState = TestReturnState.Success)
        TestCase.assertEquals(
            NetworkResult.Success(FAKE_BOOLEAN_DATA),
            dataSource.existUserDetail().first()
        )

        dataSource =
            com.gradation.lift.data.fake.FakeUserDataSource(testReturnState = TestReturnState.Fail)
        Truth.assertThat(
            NetworkResult.Fail(DefaultDataGenerator.FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            dataSource.existUserDetail().first()
        )
    }

}