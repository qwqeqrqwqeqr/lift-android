package com.gradation.lift.data.fake

import com.gradation.lift.data.data.TestDtoDataGenerator.Auth.signInDefaultResponseDto
import com.gradation.lift.data.data.TestDtoDataGenerator.Auth.signInKakaoResponseDto
import com.gradation.lift.data.data.TestDtoDataGenerator.Auth.signInNaverResponseDto
import com.gradation.lift.data.data.TestDtoDataGenerator.Auth.signUpDefaultResponseDto
import com.gradation.lift.model.model.auth.DefaultSignInInfo
import com.gradation.lift.model.model.auth.DefaultSignUpInfo
import com.gradation.lift.model.model.auth.KakaoSignInInfo
import com.gradation.lift.model.model.auth.NaverSignInInfo
import com.gradation.lift.model.model.auth.Token
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_ERROR_MESSAGE
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.datasource.auth.AuthDataSource
import com.gradation.lift.data.utils.TestReturnState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeAuthDataSource(private val testReturnState: TestReturnState = TestReturnState.Success) :
    AuthDataSource {

    override fun signInDefault(signInInfo: DefaultSignInInfo): Flow<NetworkResult<Token>> = flow {
        when (testReturnState) {
            TestReturnState.Fail -> emit(NetworkResult.Fail(FAKE_ERROR_MESSAGE))
            TestReturnState.Success -> emit(NetworkResult.Success(data = signInDefaultResponseDto.toDomain()))
        }
    }

    override fun signUpDefault(signUpInfo: DefaultSignUpInfo): Flow<NetworkResult<Boolean>> = flow {
        when (testReturnState) {
            TestReturnState.Fail -> emit(NetworkResult.Fail(FAKE_ERROR_MESSAGE))
            TestReturnState.Success -> emit(NetworkResult.Success(data = signUpDefaultResponseDto.result))
        }
    }

    override fun signInKakao(signInInfo: KakaoSignInInfo): Flow<NetworkResult<Token>> = flow {
        when (testReturnState) {
            TestReturnState.Fail -> emit(NetworkResult.Fail(FAKE_ERROR_MESSAGE))
            TestReturnState.Success -> emit(NetworkResult.Success(data = signInKakaoResponseDto.toDomain()))
        }
    }

    override fun signInNaver(signInInfo: NaverSignInInfo): Flow<NetworkResult<Token>> = flow {
        when (testReturnState) {
            TestReturnState.Fail -> emit(NetworkResult.Fail(FAKE_ERROR_MESSAGE))
            TestReturnState.Success -> emit(NetworkResult.Success(data = signInNaverResponseDto.toDomain()))
        }
    }

}