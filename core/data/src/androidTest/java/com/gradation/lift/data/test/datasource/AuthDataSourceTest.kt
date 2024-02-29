package com.gradation.lift.data.test.datasource

import com.google.common.truth.Truth
import com.gradation.lift.data.fake.datasource.FakeAuthDataSource
import com.gradation.lift.data.utils.TestReturnState
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_BOOLEAN_DATA
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_ERROR_MESSAGE
import com.gradation.lift.model.utils.ModelDataGenerator.Auth.DEFAULT_SIGN_IN_INFO_MODEL
import com.gradation.lift.model.utils.ModelDataGenerator.Auth.DEFAULT_SIGN_UP_INFO_MODEL
import com.gradation.lift.model.utils.ModelDataGenerator.Auth.KAKAO_SIGN_IN_INFO_MODEL
import com.gradation.lift.model.utils.ModelDataGenerator.Auth.NAVER_SIGN_IN_INFO_MODEL
import com.gradation.lift.model.utils.ModelDataGenerator.Auth.TOKEN_MODEL
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.datasource.auth.AuthDataSource
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
        dataSource =
            FakeAuthDataSource(testReturnState = TestReturnState.Success)
        Truth.assertThat(
            NetworkResult.Success(TOKEN_MODEL)
        ).isEqualTo(
            dataSource.signInDefault(signInInfo = DEFAULT_SIGN_IN_INFO_MODEL).first()
        )

        dataSource =
            FakeAuthDataSource(testReturnState = TestReturnState.Fail)
        Truth.assertThat(
            NetworkResult.Fail(FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            dataSource.signInDefault(signInInfo = DEFAULT_SIGN_IN_INFO_MODEL).first()
        )
    }

    @Test
    fun signInKakaoDataSource() = runTest {
        dataSource =
            FakeAuthDataSource(testReturnState = TestReturnState.Success)
        Truth.assertThat(
            NetworkResult.Success(TOKEN_MODEL)
        ).isEqualTo(
            dataSource.signInKakao(signInInfo = KAKAO_SIGN_IN_INFO_MODEL).first()
        )

        dataSource =
            FakeAuthDataSource(testReturnState = TestReturnState.Fail)
        Truth.assertThat(
            NetworkResult.Fail(FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            dataSource.signInKakao(signInInfo = KAKAO_SIGN_IN_INFO_MODEL).first()
        )
    }

    @Test
    fun signInNaverDataSource() = runTest {
        dataSource =
            FakeAuthDataSource(testReturnState = TestReturnState.Success)
        Truth.assertThat(
            NetworkResult.Success(TOKEN_MODEL)
        ).isEqualTo(
            dataSource.signInNaver(signInInfo = NAVER_SIGN_IN_INFO_MODEL).first()
        )

        dataSource =
            FakeAuthDataSource(testReturnState = TestReturnState.Fail)
        Truth.assertThat(
            NetworkResult.Fail(FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            dataSource.signInNaver(signInInfo = NAVER_SIGN_IN_INFO_MODEL).first()
        )
    }

    @Test
    fun signUpDefaultDataSource() = runTest {
        dataSource =
            FakeAuthDataSource(testReturnState = TestReturnState.Success)
        Truth.assertThat(
            NetworkResult.Success(FAKE_BOOLEAN_DATA)
        ).isEqualTo(
            dataSource.signUpDefault(signUpInfo = DEFAULT_SIGN_UP_INFO_MODEL).first()
        )

        dataSource =
            FakeAuthDataSource(testReturnState = TestReturnState.Fail)
        Truth.assertThat(
            NetworkResult.Fail(FAKE_ERROR_MESSAGE)
        ).isEqualTo(
            dataSource.signUpDefault(signUpInfo = DEFAULT_SIGN_UP_INFO_MODEL).first()
        )
    }

}