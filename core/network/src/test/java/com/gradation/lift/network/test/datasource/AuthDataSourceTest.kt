package com.gradation.lift.network.test.datasource

import com.google.common.truth.Truth
import com.gradation.lift.network.common.APIResult
import com.gradation.lift.network.datasource.AuthDataSource
import com.gradation.lift.network.fake.FakeAuthDataSource
import com.gradation.lift.network.utils.TestReturnState
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_BOOLEAN_DATA
import com.gradation.lift.model.utils.ModelDataGenerator.Auth.signInInfoModel
import com.gradation.lift.model.utils.ModelDataGenerator.Auth.signUpInfoModel
import com.gradation.lift.model.utils.ModelDataGenerator.Auth.tokenModel
import com.gradation.lift.test.rule.CoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class AuthDataSourceTest {


    @get:Rule
    var coroutineRule = CoroutineRule()

    private lateinit var dataSource: AuthDataSource


    @Test
    fun testSignInDefaultDataSource() = runTest {
        dataSource = FakeAuthDataSource(testReturnState = TestReturnState.Success)
        Truth.assertThat(
            APIResult.Success(tokenModel)
        ).isEqualTo(
            dataSource.signInDefault(signInInfo = signInInfoModel).first()
        )
    }

    @Test
    fun testSignUpDefaultDataSource() = runTest {
        dataSource = FakeAuthDataSource(testReturnState = TestReturnState.Success)
        Truth.assertThat(
            APIResult.Success(FAKE_BOOLEAN_DATA)
        ).isEqualTo(
            dataSource.signUpDefault(signUpInfo = signUpInfoModel).first()
        )
    }

}