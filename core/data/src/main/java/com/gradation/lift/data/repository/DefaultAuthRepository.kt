package com.gradation.lift.data.repository

import com.gradation.lift.common.model.DataState
import com.gradation.lift.datastore.datasource.TokenDataStoreDataSource
import com.gradation.lift.network.mapper.toMessage
import com.gradation.lift.domain.repository.AuthRepository
import com.gradation.lift.model.model.auth.DefaultSignInInfo
import com.gradation.lift.model.model.auth.DefaultSignUpInfo
import com.gradation.lift.model.model.auth.KakaoSignInInfo
import com.gradation.lift.model.model.auth.LoginMethod
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.datasource.AuthDataSource
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class DefaultAuthRepository @Inject constructor(
    private val authDataSource: AuthDataSource,
    private val tokenDataStoreDataSource: TokenDataStoreDataSource,
) : AuthRepository {
    override fun signInDefault(signInInfo: DefaultSignInInfo): Flow<DataState<Boolean>> = flow {
        authDataSource.signInDefault(signInInfo).collect { result ->
            when (result) {
                is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                is NetworkResult.Success -> {
                    tokenDataStoreDataSource.setAccessToken(result.data.accessToken)
                    tokenDataStoreDataSource.setRefreshToken(result.data.refreshToken)
                    emit(DataState.Success(true))
                }
            }
        }
    }

    override fun signUpDefault(signUpInfo: DefaultSignUpInfo): Flow<DataState<Boolean>> = flow {
        authDataSource.signUpDefault(signUpInfo).collect { result ->
            when (result) {
                is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                is NetworkResult.Success -> {
                    emit(DataState.Success(true))
                }
            }
        }
    }

    override fun signInKakao(): Flow<DataState<Boolean>> = flow {
        authDataSource.signInFromKakao().collect { result ->
            when (result) {
                is NetworkResult.Fail -> emit(DataState.Fail(result.message))
                is NetworkResult.Success -> authDataSource.signInKakao(KakaoSignInInfo(result.data))
                    .collect {
                        when (it) {
                            is NetworkResult.Fail -> {
                                emit(DataState.Fail(it.message))
                            }
                            is NetworkResult.Success -> {
                                try {
                                    tokenDataStoreDataSource.setAccessToken(it.data.accessToken)
                                    tokenDataStoreDataSource.setRefreshToken(it.data.refreshToken)
                                    tokenDataStoreDataSource.setLoginMethod(LoginMethod.Kakao())
                                } catch (t: Throwable) {
                                    emit(DataState.Fail(t.message ?: "로그인을 실패하였습니다."))
                                } finally {
                                    emit(DataState.Success(true))
                                }
                            }
                        }
                    }
            }
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