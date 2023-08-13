package com.gradation.lift.network.datasource

import android.annotation.SuppressLint
import android.content.Context
import com.gradation.lift.model.model.auth.DefaultSignInInfo
import com.gradation.lift.model.model.auth.DefaultSignUpInfo
import com.gradation.lift.model.model.auth.KakaoSignInInfo
import com.gradation.lift.model.model.auth.Token
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.handler.NetworkResultHandler
import com.gradation.lift.network.mapper.toDto
import com.gradation.lift.network.service.AuthService
import com.kakao.sdk.user.UserApiClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume


class DefaultAuthDataSource @Inject constructor(
    private val authService: AuthService,
    private val NetworkResultHandler: NetworkResultHandler,
    @ApplicationContext private val context: Context,
) : AuthDataSource {
    override fun signInDefault(signInInfo: DefaultSignInInfo): Flow<NetworkResult<Token>> = flow {
        NetworkResultHandler {
            authService.signInDefault(
                signInInfo.toDto()
            )
        }.transform { result ->
            when (result) {
                is NetworkResult.Fail -> emit(NetworkResult.Fail(message = result.message))
                is NetworkResult.Success -> emit(NetworkResult.Success(result.data.toDomain()))
            }
        }
    }

    override fun signUpDefault(signUpInfo: DefaultSignUpInfo): Flow<NetworkResult<Boolean>> = flow {
        NetworkResultHandler {
            authService.signUpDefault(
                signUpInfo.toDto()
            )
        }.transform { result ->
            when (result) {
                is NetworkResult.Fail -> emit(NetworkResult.Fail(result.message))
                is NetworkResult.Success -> emit(NetworkResult.Success(result.data.result))
            }
        }
    }


    override suspend fun signInKakao(signInInfo: KakaoSignInInfo): Flow<NetworkResult<Token>> =
        flow {
            NetworkResultHandler {
                authService.signInKakao(
                    signInInfo.toDto()
                )
            }.transform { result ->
                when (result) {
                    is NetworkResult.Fail -> emit(NetworkResult.Fail(result.message))
                    is NetworkResult.Success -> emit(NetworkResult.Success(result.data.toDomain()))
                }
            }
        }

    @SuppressLint("CheckResult")
    override fun signInFromKakao(): Flow<NetworkResult<String>> = flow {
        val result = suspendCancellableCoroutine { continuation ->
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
                UserApiClient.instance.loginWithKakaoTalk(
                    context,
                    callback = { token, error ->
                        token?.let { continuation.resume(true) }
                        error?.let { continuation.resume(false) }
                    })
            } else {
                UserApiClient.instance.loginWithKakaoAccount(
                    context,
                    callback = { token, error ->
                        token?.let { continuation.resume(true) }
                        error?.let { continuation.resume(false) }
                    })
            }
        }
        if (result) {
            emit(suspendCancellableCoroutine { continuation ->
                UserApiClient.instance.me() { user, error ->
                    user?.let {
                        continuation.resume(NetworkResult.Success(it.id.toString()))
                    }
                    error?.let {
                        continuation.resume(
                            NetworkResult.Fail(
                                it.message ?: "사용자 아이디를 불러올 수 없습니다."
                            )
                        )
                    }
                }
            })
        } else {
            emit(NetworkResult.Fail("로그인을 실패하였습니다."))
        }
    }
}

