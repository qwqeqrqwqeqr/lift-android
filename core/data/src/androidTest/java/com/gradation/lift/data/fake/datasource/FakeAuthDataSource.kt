package com.gradation.lift.data.fake.datasource

import com.gradation.lift.data.data.TestDtoDataGenerator.Auth.CheckExistUser.CHECK_EXIST_USER_RESPONSE_DTO
import com.gradation.lift.data.data.TestDtoDataGenerator.Auth.CreateEmailAuthenticationCode.CREATE_EMAIL_AUTHENTICATION_CODE_RESPONSE_DTO
import com.gradation.lift.data.data.TestDtoDataGenerator.Auth.SignInDefault.SIGN_IN_DEFAULT_RESPONSE_DTO
import com.gradation.lift.data.data.TestDtoDataGenerator.Auth.SignInGoogle.SIGN_IN_GOOGLE_RESPONSE_DTO
import com.gradation.lift.data.data.TestDtoDataGenerator.Auth.SignInKakao.SIGN_IN_KAKAO_RESPONSE_DTO
import com.gradation.lift.data.data.TestDtoDataGenerator.Auth.SignInNaver.SIGN_IN_NAVER_RESPONSE_DTO
import com.gradation.lift.data.data.TestDtoDataGenerator.Auth.SignUpDefault.SIGN_UP_DEFAULT_RESPONSE_DTO
import com.gradation.lift.data.data.TestDtoDataGenerator.Auth.SignUpGoogle.SIGN_UP_GOOGLE_RESPONSE_DTO
import com.gradation.lift.data.data.TestDtoDataGenerator.Auth.SignUpKakao.SIGN_UP_KAKAO_RESPONSE_DTO
import com.gradation.lift.data.data.TestDtoDataGenerator.Auth.SignUpNaver.SIGN_UP_NAVER_RESPONSE_DTO
import com.gradation.lift.data.data.TestDtoDataGenerator.Auth.UpdateUserPassword.UPDATE_PASSWORD_RESPONSE_DTO
import com.gradation.lift.data.data.TestDtoDataGenerator.Auth.ValidateEmailAuthentication.VALIDATE_EMAIL_AUTHENTICATION_RESPONSE_DTO
import com.gradation.lift.data.utils.TestReturnState
import com.gradation.lift.model.model.auth.DefaultSignInInfo
import com.gradation.lift.model.model.auth.DefaultSignUpInfo
import com.gradation.lift.model.model.auth.EmailAuthenticationInfo
import com.gradation.lift.model.model.auth.EmailAuthenticationValidationInfo
import com.gradation.lift.model.model.auth.GoogleSignInInfo
import com.gradation.lift.model.model.auth.GoogleSignUpInfo
import com.gradation.lift.model.model.auth.KakaoSignInInfo
import com.gradation.lift.model.model.auth.KakaoSignUpInfo
import com.gradation.lift.model.model.auth.NaverSignInInfo
import com.gradation.lift.model.model.auth.NaverSignUpInfo
import com.gradation.lift.model.model.auth.Token
import com.gradation.lift.model.model.auth.UpdatePasswordInfo
import com.gradation.lift.model.utils.DefaultDataGenerator.FAKE_ERROR_MESSAGE
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.datasource.auth.AuthDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeAuthDataSource(private val testReturnState: TestReturnState = TestReturnState.Success) :
    AuthDataSource {

    override suspend fun signInDefault(signInInfo: DefaultSignInInfo): Flow<NetworkResult<Token>> =
        flow {
            when (testReturnState) {
                TestReturnState.Fail -> emit(NetworkResult.Fail(FAKE_ERROR_MESSAGE))
                TestReturnState.Success -> emit(NetworkResult.Success(data = SIGN_IN_DEFAULT_RESPONSE_DTO.toDomain()))
            }
        }

    override suspend fun signUpDefault(signUpInfo: DefaultSignUpInfo): Flow<NetworkResult<Boolean>> =
        flow {
            when (testReturnState) {
                TestReturnState.Fail -> emit(NetworkResult.Fail(FAKE_ERROR_MESSAGE))
                TestReturnState.Success -> emit(NetworkResult.Success(data = SIGN_UP_DEFAULT_RESPONSE_DTO.result))
            }
        }

    override suspend fun signInKakao(signInInfo: KakaoSignInInfo): Flow<NetworkResult<Token>> =
        flow {
            when (testReturnState) {
                TestReturnState.Fail -> emit(NetworkResult.Fail(FAKE_ERROR_MESSAGE))
                TestReturnState.Success -> emit(NetworkResult.Success(data = SIGN_IN_KAKAO_RESPONSE_DTO.toDomain()))
            }
        }

    override suspend fun signInNaver(signInInfo: NaverSignInInfo): Flow<NetworkResult<Token>> =
        flow {
            when (testReturnState) {
                TestReturnState.Fail -> emit(NetworkResult.Fail(FAKE_ERROR_MESSAGE))
                TestReturnState.Success -> emit(NetworkResult.Success(data = SIGN_IN_NAVER_RESPONSE_DTO.toDomain()))
            }
        }

    override suspend fun signUpKakao(signUpInfo: KakaoSignUpInfo): Flow<NetworkResult<Boolean>> =
        flow {
            when (testReturnState) {
                TestReturnState.Fail -> emit(NetworkResult.Fail(FAKE_ERROR_MESSAGE))
                TestReturnState.Success -> emit(NetworkResult.Success(data = SIGN_UP_KAKAO_RESPONSE_DTO.result))
            }
        }

    override suspend fun signUpNaver(signUpInfo: NaverSignUpInfo): Flow<NetworkResult<Boolean>> =
        flow {
            when (testReturnState) {
                TestReturnState.Fail -> emit(NetworkResult.Fail(FAKE_ERROR_MESSAGE))
                TestReturnState.Success -> emit(NetworkResult.Success(data = SIGN_UP_NAVER_RESPONSE_DTO.result))
            }
        }

    override suspend fun signInGoogle(signInInfo: GoogleSignInInfo): Flow<NetworkResult<Token>> =
        flow {
            when (testReturnState) {
                TestReturnState.Fail -> emit(NetworkResult.Fail(FAKE_ERROR_MESSAGE))
                TestReturnState.Success -> emit(NetworkResult.Success(data = SIGN_IN_GOOGLE_RESPONSE_DTO.toDomain()))
            }
        }

    override suspend fun signUpGoogle(signUpInfo: GoogleSignUpInfo): Flow<NetworkResult<Boolean>> =
        flow {
            when (testReturnState) {
                TestReturnState.Fail -> emit(NetworkResult.Fail(FAKE_ERROR_MESSAGE))
                TestReturnState.Success -> emit(NetworkResult.Success(data = SIGN_UP_GOOGLE_RESPONSE_DTO.result))
            }
        }

    override suspend fun checkUserExist(
        userId: String,
        email: String,
    ): Flow<NetworkResult<Boolean>> = flow {
        when (testReturnState) {
            TestReturnState.Fail -> emit(NetworkResult.Fail(FAKE_ERROR_MESSAGE))
            TestReturnState.Success -> emit(NetworkResult.Success(data = CHECK_EXIST_USER_RESPONSE_DTO.result))
        }
    }

    override suspend fun updateUserPassword(updatePasswordInfo: UpdatePasswordInfo): Flow<NetworkResult<Boolean>> =
        flow {
            when (testReturnState) {
                TestReturnState.Fail -> emit(NetworkResult.Fail(FAKE_ERROR_MESSAGE))
                TestReturnState.Success -> emit(NetworkResult.Success(data = UPDATE_PASSWORD_RESPONSE_DTO.result))
            }
        }

    override suspend fun createEmailAuthenticationCode(emailAuthenticationInfo: EmailAuthenticationInfo): Flow<NetworkResult<Boolean>> =
        flow {
            when (testReturnState) {
                TestReturnState.Fail -> emit(NetworkResult.Fail(FAKE_ERROR_MESSAGE))
                TestReturnState.Success -> emit(NetworkResult.Success(data = CREATE_EMAIL_AUTHENTICATION_CODE_RESPONSE_DTO.result))
            }
        }

    override suspend fun validateEmailAuthentication(emailAuthenticationValidationInfo: EmailAuthenticationValidationInfo): Flow<NetworkResult<Boolean>> =
        flow {
            when (testReturnState) {
                TestReturnState.Fail -> emit(NetworkResult.Fail(FAKE_ERROR_MESSAGE))
                TestReturnState.Success -> emit(NetworkResult.Success(data = VALIDATE_EMAIL_AUTHENTICATION_RESPONSE_DTO.result))
            }
        }

}