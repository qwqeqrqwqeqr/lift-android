package com.gradation.lift.network.test.datasource

import com.google.common.truth.Truth
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.datasource.auth.AuthDataSource
import com.gradation.lift.network.fake.FakeAuthDataSource
import com.gradation.lift.network.utils.TestReturnState
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_BOOLEAN_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_ERROR_MESSAGE
import com.gradation.lift.model.utils.ModelDataGenerator.Auth.defaultSignInInfoModel
import com.gradation.lift.model.utils.ModelDataGenerator.Auth.defaultSignUpInfoModel
import com.gradation.lift.model.utils.ModelDataGenerator.Auth.kakaoSignInInfoModel
import com.gradation.lift.model.utils.ModelDataGenerator.Auth.naverSignInInfoModel
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
    fun signInDefaultDataSource() = runTest {
        dataSource = FakeAuthDataSource(testReturnState = TestReturnState.Success)
        Truth.assertThat(
            NetworkResult.Success(tokenModel)
        ).isEqualTo(
            dataSource.signInDefault(signInInfo = defaultSignInInfoModel).first()
        )

        dataSource = FakeAuthDataSource(testReturnState = TestReturnState.Fail)
        Truth.assertThat(
            NetworkResult.Fail(FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            dataSource.signInDefault(signInInfo = defaultSignInInfoModel).first()
        )
    }

    @Test
    fun signInKakaoDataSource() = runTest {
        dataSource = FakeAuthDataSource(testReturnState = TestReturnState.Success)
        Truth.assertThat(
            NetworkResult.Success(tokenModel)
        ).isEqualTo(
            dataSource.signInKakao(signInInfo = kakaoSignInInfoModel).first()
        )

        dataSource = FakeAuthDataSource(testReturnState = TestReturnState.Fail)
        Truth.assertThat(
            NetworkResult.Fail(FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            dataSource.signInKakao(signInInfo = kakaoSignInInfoModel).first()
        )
    }

    @Test
    fun signInNaverDataSource() = runTest {
        dataSource = FakeAuthDataSource(testReturnState = TestReturnState.Success)
        Truth.assertThat(
            NetworkResult.Success(tokenModel)
        ).isEqualTo(
            dataSource.signInNaver(signInInfo = naverSignInInfoModel).first()
        )

        dataSource = FakeAuthDataSource(testReturnState = TestReturnState.Fail)
        Truth.assertThat(
            NetworkResult.Fail(FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            dataSource.signInNaver(signInInfo = naverSignInInfoModel).first()
        )
    }

    @Test
    fun signUpDefaultDataSource() = runTest {
        dataSource = FakeAuthDataSource(testReturnState = TestReturnState.Success)
        Truth.assertThat(
            NetworkResult.Success(FAKE_BOOLEAN_DATA)
        ).isEqualTo(
            dataSource.signUpDefault(signUpInfo = defaultSignUpInfoModel).first()
        )

        dataSource = FakeAuthDataSource(testReturnState = TestReturnState.Fail)
        Truth.assertThat(
            NetworkResult.Fail(FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            dataSource.signUpDefault(signUpInfo = defaultSignUpInfoModel).first()
        )
    }

}