package com.gradation.lift.data.repository

import com.gradation.lift.common.common.DispatcherProvider
import com.gradation.lift.common.model.DataState
import com.gradation.lift.database.datasource.database.DatabaseSettingDataSource
import com.gradation.lift.datastore.datasource.TokenDataStoreDataSource
import com.gradation.lift.domain.repository.AuthRepository
import com.gradation.lift.model.model.auth.DefaultSignInInfo
import com.gradation.lift.model.model.auth.DefaultSignUpInfo
import com.gradation.lift.model.model.auth.EmailAuthenticationInfo
import com.gradation.lift.model.model.auth.EmailAuthenticationValidationInfo
import com.gradation.lift.model.model.auth.GoogleSignInInfo
import com.gradation.lift.model.model.auth.GoogleSignUpInfo
import com.gradation.lift.model.model.auth.KakaoSignInInfo
import com.gradation.lift.model.model.auth.KakaoSignUpInfo
import com.gradation.lift.model.model.auth.LoginMethod
import com.gradation.lift.model.model.auth.NaverSignInInfo
import com.gradation.lift.model.model.auth.NaverSignUpInfo
import com.gradation.lift.model.model.auth.UpdatePasswordInfo
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.datasource.auth.AuthRemoteDataSource
import com.gradation.lift.oauth.google.GoogleOauthManager
import com.gradation.lift.oauth.kakao.KakaoOauthManager
import com.gradation.lift.oauth.naver.NaverOauthManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DefaultAuthRepository @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource,
    private val tokenDataStoreDataSource: TokenDataStoreDataSource,
    private val kakaoOauthManager: KakaoOauthManager,
    private val naverOauthManager: NaverOauthManager,
    private val googleOauthManager: GoogleOauthManager,
    private val dispatcherProvider: DispatcherProvider,
    private val databaseSettingDataSource: DatabaseSettingDataSource,
) : AuthRepository {
    override fun signInDefault(signInInfo: DefaultSignInInfo): Flow<DataState<Unit>> = flow {
        authRemoteDataSource.signInDefault(signInInfo).collect { result ->
            when (result) {
                is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                is NetworkResult.Success -> {
                    tokenDataStoreDataSource.setAccessToken(result.data.accessToken)
                    tokenDataStoreDataSource.setRefreshToken(result.data.refreshToken)
                    tokenDataStoreDataSource.setLoginMethod(LoginMethod.Common())
                    emit(DataState.Success(Unit))
                }
            }
        }
    }.flowOn(dispatcherProvider.default)

    override fun signUpDefault(signUpInfo: DefaultSignUpInfo): Flow<DataState<Boolean>> = flow {
        authRemoteDataSource.signUpDefault(signUpInfo).collect { result ->
            when (result) {
                is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                is NetworkResult.Success -> {
                    emit(DataState.Success(result.data))
                }
            }
        }
    }.flowOn(dispatcherProvider.default)

    override fun signInNaver(): Flow<DataState<Unit>> = flow {
        combine(naverOauthManager.getUserId(), naverOauthManager.getUserEmail()) { id, email ->
            when (id) {
                is DataState.Fail -> DataState.Fail(id.message)
                is DataState.Success -> when (email) {
                    is DataState.Fail -> DataState.Fail(email.message)
                    is DataState.Success -> DataState.Success(
                        NaverSignInInfo(
                            id = id.data,
                            email = email.data
                        )
                    )
                }
            }
        }.collect { naverSignInInfo ->
            when (naverSignInInfo) {
                is DataState.Fail -> emit(DataState.Fail(naverSignInInfo.message))
                is DataState.Success -> {
                    authRemoteDataSource.signInNaver(naverSignInInfo.data)
                        .collect { signInNaverResult ->
                            when (signInNaverResult) {
                                is NetworkResult.Fail -> emit(DataState.Fail(signInNaverResult.message))
                                is NetworkResult.Success -> {
                                    tokenDataStoreDataSource.setAccessToken(signInNaverResult.data.accessToken)
                                    tokenDataStoreDataSource.setRefreshToken(signInNaverResult.data.refreshToken)
                                    tokenDataStoreDataSource.setLoginMethod(LoginMethod.Naver())
                                    emit(DataState.Success(Unit))
                                }
                            }
                        }
                }
            }
        }
    }.flowOn(dispatcherProvider.default)

    override fun signUpNaver(): Flow<DataState<Boolean>> = flow {
        combine(naverOauthManager.getUserId(), naverOauthManager.getUserEmail()) { id, email ->
            when (id) {
                is DataState.Fail -> DataState.Fail(id.message)
                is DataState.Success -> when (email) {
                    is DataState.Fail -> DataState.Fail(email.message)
                    is DataState.Success -> DataState.Success(
                        NaverSignUpInfo(
                            id = id.data,
                            email = email.data
                        )
                    )
                }
            }
        }.collect { naverSignUpInfo ->
            when (naverSignUpInfo) {
                is DataState.Fail -> emit(DataState.Fail(naverSignUpInfo.message))
                is DataState.Success -> {
                    authRemoteDataSource.signUpNaver(naverSignUpInfo.data)
                        .collect { signUpNaverResult ->
                            when (signUpNaverResult) {
                                is NetworkResult.Fail -> emit(DataState.Fail(signUpNaverResult.message))
                                is NetworkResult.Success -> {
                                    emit(DataState.Success(signUpNaverResult.data))
                                }
                            }
                        }
                }
            }
        }
    }.flowOn(dispatcherProvider.default)

    override fun signInKakao(): Flow<DataState<Unit>> = flow {
        combine(kakaoOauthManager.getUserId(), kakaoOauthManager.getUserEmail()) { id, email ->
            when (id) {
                is DataState.Fail -> DataState.Fail(id.message)
                is DataState.Success -> when (email) {
                    is DataState.Fail -> DataState.Fail(email.message)
                    is DataState.Success -> DataState.Success(
                        KakaoSignInInfo(
                            id = id.data,
                            email = email.data
                        )
                    )
                }
            }
        }.collect { kakaoSignInInfo ->
            when (kakaoSignInInfo) {
                is DataState.Fail -> emit(DataState.Fail(kakaoSignInInfo.message))
                is DataState.Success -> {
                    authRemoteDataSource.signInKakao(kakaoSignInInfo.data)
                        .collect { signInKakaoResult ->
                            when (signInKakaoResult) {
                                is NetworkResult.Fail -> emit(DataState.Fail(signInKakaoResult.message))
                                is NetworkResult.Success -> {
                                    tokenDataStoreDataSource.setAccessToken(signInKakaoResult.data.accessToken)
                                    tokenDataStoreDataSource.setRefreshToken(signInKakaoResult.data.refreshToken)
                                    tokenDataStoreDataSource.setLoginMethod(LoginMethod.Kakao())
                                    emit(DataState.Success(Unit))
                                }
                            }
                        }
                }
            }
        }
    }.flowOn(dispatcherProvider.default)

    override fun signUpKakao(): Flow<DataState<Boolean>> = flow {
        combine(kakaoOauthManager.getUserId(), kakaoOauthManager.getUserEmail()) { id, email ->
            when (id) {
                is DataState.Fail -> DataState.Fail(id.message)
                is DataState.Success -> when (email) {
                    is DataState.Fail -> DataState.Fail(email.message)
                    is DataState.Success -> DataState.Success(
                        KakaoSignUpInfo(
                            id = id.data,
                            email = email.data
                        )
                    )
                }
            }
        }.collect { kakaoSignUpInfo ->
            when (kakaoSignUpInfo) {
                is DataState.Fail -> emit(DataState.Fail(kakaoSignUpInfo.message))
                is DataState.Success -> {
                    authRemoteDataSource.signUpKakao(kakaoSignUpInfo.data)
                        .collect { signUpKakaoResult ->
                            when (signUpKakaoResult) {
                                is NetworkResult.Fail -> emit(DataState.Fail(signUpKakaoResult.message))
                                is NetworkResult.Success -> {
                                    emit(DataState.Success(signUpKakaoResult.data))
                                }
                            }
                        }
                }
            }
        }
    }.flowOn(dispatcherProvider.default)

    override fun signInGoogle(): Flow<DataState<Unit>> = flow {
        combine(googleOauthManager.getUserId(), googleOauthManager.getUserEmail()) { id, email ->
            when (id) {
                is DataState.Fail -> DataState.Fail(id.message)
                is DataState.Success -> when (email) {
                    is DataState.Fail -> DataState.Fail(email.message)
                    is DataState.Success -> DataState.Success(
                        GoogleSignInInfo(
                            id = id.data,
                            email = email.data
                        )
                    )
                }
            }
        }.collect { googleSignInInfo ->
            when (googleSignInInfo) {
                is DataState.Fail -> emit(DataState.Fail(googleSignInInfo.message))
                is DataState.Success -> {
                    authRemoteDataSource.signInGoogle(googleSignInInfo.data)
                        .collect { signInGoogleResult ->
                            when (signInGoogleResult) {
                                is NetworkResult.Fail -> emit(DataState.Fail(signInGoogleResult.message))
                                is NetworkResult.Success -> {
                                    tokenDataStoreDataSource.setAccessToken(signInGoogleResult.data.accessToken)
                                    tokenDataStoreDataSource.setRefreshToken(signInGoogleResult.data.refreshToken)
                                    tokenDataStoreDataSource.setLoginMethod(LoginMethod.Google())
                                    emit(DataState.Success(Unit))
                                }
                            }
                        }
                }
            }
        }
    }.flowOn(dispatcherProvider.default)

    override fun signUpGoogle(): Flow<DataState<Boolean>> = flow {
        combine(googleOauthManager.getUserId(), googleOauthManager.getUserEmail()) { id, email ->
            when (id) {
                is DataState.Fail -> DataState.Fail(id.message)
                is DataState.Success -> when (email) {
                    is DataState.Fail -> DataState.Fail(email.message)
                    is DataState.Success -> DataState.Success(
                        GoogleSignUpInfo(
                            id = id.data,
                            email = email.data
                        )
                    )
                }
            }
        }.collect { googleSignUpInfo ->
            when (googleSignUpInfo) {
                is DataState.Fail -> emit(DataState.Fail(googleSignUpInfo.message))
                is DataState.Success -> {
                    authRemoteDataSource.signUpGoogle(googleSignUpInfo.data)
                        .collect { signUpGoogleResult ->
                            when (signUpGoogleResult) {
                                is NetworkResult.Fail -> emit(DataState.Fail(signUpGoogleResult.message))
                                is NetworkResult.Success ->
                                    emit(DataState.Success(signUpGoogleResult.data))

                            }
                        }
                }
            }
        }
    }.flowOn(dispatcherProvider.default)

    override fun checkExistUser(userId: String, email: String): Flow<DataState<Boolean>> = flow {
        authRemoteDataSource.checkUserExist(userId, email).collect {
            when (it) {
                is NetworkResult.Fail -> emit(DataState.Fail(it.message))
                is NetworkResult.Success -> emit(DataState.Success(it.data))
            }
        }
    }.flowOn(dispatcherProvider.default)

    override fun getLoginMethod(): Flow<DataState<LoginMethod>> = flow {
        tokenDataStoreDataSource.loginMethod
            .catch { error ->
                DataState.Fail(error.message.toString())
            }
            .collect { loginMethod ->
                emit(DataState.Success(loginMethod))
            }
    }.flowOn(dispatcherProvider.default)

    override fun isSigned(): Flow<DataState<Boolean>> = flow {
        val condition = tokenDataStoreDataSource.accessToken.first().isNotBlank() &&
                tokenDataStoreDataSource.refreshToken.first().isNotBlank()
        if (condition) emit(DataState.Success(true)) else emit(DataState.Success(false))
    }

    override fun signOut(): Flow<DataState<Unit>> = flow {
        try {
            tokenDataStoreDataSource.clearAll()
            databaseSettingDataSource.clearDatabase()
            emit(DataState.Success(Unit))
        } catch (error: Exception) {
            emit(DataState.Fail(error.message.toString()))
        }
    }.flowOn(dispatcherProvider.default)

    override fun updateUserPassword(updatePasswordInfo: UpdatePasswordInfo): Flow<DataState<Boolean>> =
        flow {
            authRemoteDataSource.updateUserPassword(updatePasswordInfo).collect { result ->
                when (result) {
                    is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                    is NetworkResult.Success -> emit(
                        DataState.Success(
                            data = result.data,
                            message = result.message
                        )
                    )
                }
            }
        }.flowOn(dispatcherProvider.default)

    override fun createEmailAuthenticationCode(emailAuthenticationInfo: EmailAuthenticationInfo): Flow<DataState<Boolean>> =
        flow {
            authRemoteDataSource.createEmailAuthenticationCode(emailAuthenticationInfo)
                .collect { result ->
                    when (result) {
                        is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                        is NetworkResult.Success -> emit(DataState.Success(result.data))
                    }
                }
        }.flowOn(dispatcherProvider.default)

    override fun validateEmailAuthentication(emailAuthenticationValidationInfo: EmailAuthenticationValidationInfo): Flow<DataState<Boolean>> =
        flow {
            authRemoteDataSource.validateEmailAuthentication(emailAuthenticationValidationInfo)
                .collect { result ->
                    when (result) {
                        is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                        is NetworkResult.Success -> emit(DataState.Success(result.data))
                    }
                }
        }.flowOn(dispatcherProvider.default)
}