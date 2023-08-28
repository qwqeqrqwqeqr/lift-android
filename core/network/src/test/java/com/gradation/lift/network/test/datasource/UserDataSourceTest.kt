package com.gradation.lift.network.test.datasource

import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.datasource.user.UserDataSource
import com.gradation.lift.network.fake.FakeUserDataSource
import com.gradation.lift.network.utils.TestReturnState
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
    fun testGetUserDetailDataSource() = runTest {
        dataSource = FakeUserDataSource(testReturnState = TestReturnState.Success)
        TestCase.assertEquals(
            NetworkResult.Success(userDetailModel),
            dataSource.getUserDetail().first()
        )
    }
    @Test
    fun testCreateUserDetailDataSource() = runTest {
        dataSource = FakeUserDataSource(testReturnState = TestReturnState.Success)
        TestCase.assertEquals(
            NetworkResult.Success(Unit),
            dataSource.createUserDetail(createUserDetailModel).first()
        )
    }
    @Test
    fun testUpdateUserDetailDataSource() = runTest {
        dataSource = FakeUserDataSource(testReturnState = TestReturnState.Success)
        TestCase.assertEquals(
            NetworkResult.Success(Unit),
            dataSource.updateUserDetail(userDetailModel).first()
        )
    }
    @Test
    fun testExistUserDetailDataSource() = runTest {
        dataSource = FakeUserDataSource(testReturnState = TestReturnState.Success)
        TestCase.assertEquals(
            NetworkResult.Success(FAKE_BOOLEAN_DATA),
            dataSource.existUserDetail().first()
        )
    }

}