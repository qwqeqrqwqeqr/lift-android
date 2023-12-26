package com.gradation.lift.data.repository

import com.gradation.lift.common.model.DataState
import com.gradation.lift.datastore.datasource.TokenDataStoreDataSource
import com.gradation.lift.network.mapper.toMessage
import com.gradation.lift.domain.repository.AuthRepository
import com.gradation.lift.model.model.auth.*
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.datasource.auth.AuthDataSource
import com.gradation.lift.oauth.google.GoogleOauthManager
import com.gradation.lift.oauth.kakao.KakaoOauthManager
import com.gradation.lift.oauth.naver.NaverOauthManager
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class DefaultAuthRepository @Inject constructor(
    private val authDataSource: AuthDataSource,
    private val tokenDataStoreDataSource: TokenDataStoreDataSource,
    private val kakaoOauthManager: KakaoOauthManager,
    private val naverOauthManager: NaverOauthManager,
    private val googleOauthManager: GoogleOauthManager,
) : AuthRepository {
    override fun signInDefault(signInInfo: DefaultSignInInfo): Flow<DataState<Unit>> = flow {
        authDataSource.signInDefault(signInInfo).collect { result ->
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
    }

    override fun signUpDefault(signUpInfo: DefaultSignUpInfo): Flow<DataState<Unit>> = flow {
        authDataSource.signUpDefault(signUpInfo).collect { result ->
            when (result) {
                is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                is NetworkResult.Success -> {
                    emit(DataState.Success(Unit))
                }
            }
        }
    }

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
                    authDataSource.signInNaver(naverSignInInfo.data)
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
    }

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
                    authDataSource.signInKakao(kakaoSignInInfo.data)
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
    }

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
                    authDataSource.signInGoogle(googleSignInInfo.data)
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
    }

    override fun checkExistUser(userId: String): Flow<DataState<Boolean>> = flow {
        authDataSource.checkUserExist(userId).collect {
            when (it) {
                is NetworkResult.Fail -> emit(DataState.Fail(it.message))
                is NetworkResult.Success -> emit(DataState.Success(it.data))
            }
        }

    }

    override fun getLoginMethod(): Flow<DataState<LoginMethod>> = flow {
        tokenDataStoreDataSource.loginMethod
            .catch { it ->
                DataState.Fail(it.toMessage())
            }
            .collect { loginMethod ->
                emit(DataState.Success(loginMethod))
            }
    }

    override fun isSigned(): Flow<DataState<Boolean>> = flow {
        val condition = tokenDataStoreDataSource.accessToken.first().isNotBlank() &&
                tokenDataStoreDataSource.refreshToken.first().isNotBlank()
        if (condition) emit(DataState.Success(true)) else emit(DataState.Success(false))
    }

    override fun signOut(): Flow<DataState<Unit>> = flow {
        try {
            tokenDataStoreDataSource.clearAll()
            emit(DataState.Success(Unit))
        } catch (error: Exception) {
            emit(DataState.Fail(error.toMessage()))
        }
    }
}