package com.gradation.lift.network.datasource.kakao

import android.content.Context
import com.gradation.lift.network.common.NetworkResult
import com.kakao.sdk.user.UserApiClient
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

class DefaultKakaoDataSource @Inject constructor(
    @ApplicationContext private val context: Context,
) : KakaoDataSource {

    override fun signIn(): Flow<NetworkResult<String>> = flow {
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