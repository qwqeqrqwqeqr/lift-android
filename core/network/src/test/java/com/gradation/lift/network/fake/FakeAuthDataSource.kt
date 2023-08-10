package com.gradation.lift.network.fake

import com.gradation.lift.model.model.auth.DefaultSignInInfo
import com.gradation.lift.model.model.auth.DefaultSignUpInfo
import com.gradation.lift.model.model.auth.Token
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.data.TestDtoDataGenerator.Auth.signInDefaultResponseDto
import com.gradation.lift.network.data.TestDtoDataGenerator.Auth.signUpDefaultResponseDto
import com.gradation.lift.network.datasource.AuthDataSource
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

}