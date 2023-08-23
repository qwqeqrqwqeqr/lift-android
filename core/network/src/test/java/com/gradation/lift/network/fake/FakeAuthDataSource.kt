package com.gradation.lift.network.fake

import com.gradation.lift.model.model.auth.*
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.data.TestDtoDataGenerator.Auth.signInDefaultResponseDto
import com.gradation.lift.network.data.TestDtoDataGenerator.Auth.signInKakaoResponseDto
import com.gradation.lift.network.data.TestDtoDataGenerator.Auth.signInNaverResponseDto
import com.gradation.lift.network.data.TestDtoDataGenerator.Auth.signUpDefaultResponseDto
import com.gradation.lift.network.datasource.auth.AuthDataSource
import com.gradation.lift.network.utils.TestReturnState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeAuthDataSource(private val testReturnState: TestReturnState = TestReturnState.Success) :
    AuthDataSource {

    override fun signInDefault(signInInfo: DefaultSignInInfo): Flow<NetworkResult<Token>> = flow {
        when (testReturnState) {
            TestReturnState.Fail -> emit(NetworkResult.Fail("오류"))
            TestReturnState.Success -> emit(NetworkResult.Success(data = signInDefaultResponseDto.toDomain()))
        }
    }

    override fun signUpDefault(signUpInfo: DefaultSignUpInfo): Flow<NetworkResult<Boolean>> = flow {
        when (testReturnState) {
            TestReturnState.Fail -> emit(NetworkResult.Fail("오류"))
            TestReturnState.Success -> emit(NetworkResult.Success(data = signUpDefaultResponseDto.result))
        }
    }

    override fun signInKakao(signInInfo: KakaoSignInInfo): Flow<NetworkResult<Token>> = flow {
        when (testReturnState) {
            TestReturnState.Fail -> emit(NetworkResult.Fail("오류"))
            TestReturnState.Success -> emit(NetworkResult.Success(data = signInKakaoResponseDto.toDomain()))
        }
    }

    override fun signInNaver(signInInfo: NaverSignInInfo): Flow<NetworkResult<Token>> = flow {
        when (testReturnState) {
            TestReturnState.Fail -> emit(NetworkResult.Fail("오류"))
            TestReturnState.Success -> emit(NetworkResult.Success(data = signInNaverResponseDto.toDomain()))
        }
    }

}