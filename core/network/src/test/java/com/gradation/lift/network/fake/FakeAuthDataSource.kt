package com.gradation.lift.network.fake

import com.gradation.lift.model.auth.SignInInfo
import com.gradation.lift.model.auth.SignUpInfo
import com.gradation.lift.model.auth.Token
import com.gradation.lift.network.common.APIResult
import com.gradation.lift.network.data.TestDtoDataGenerator.Auth.signInDefaultResponseDto
import com.gradation.lift.network.data.TestDtoDataGenerator.Auth.signUpDefaultResponseDto
import com.gradation.lift.network.datasource.AuthDataSource
import com.gradation.lift.network.utils.TestReturnState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeAuthDataSource(private val testReturnState: TestReturnState = TestReturnState.Success) :
    AuthDataSource {

    override fun signInDefault(signInInfo: SignInInfo): Flow<APIResult<Token>> = flow {
        when (testReturnState) {
            TestReturnState.Fail -> emit(APIResult.Fail("오류"))
            TestReturnState.Success -> emit(APIResult.Success(data = signInDefaultResponseDto.toDomain()))
        }
    }

    override fun signUpDefault(signUpInfo: SignUpInfo): Flow<APIResult<Boolean>> = flow {
        when (testReturnState) {
            TestReturnState.Fail -> emit(APIResult.Fail("오류"))
            TestReturnState.Success -> emit(APIResult.Success(data = signUpDefaultResponseDto.result))
        }
    }

}