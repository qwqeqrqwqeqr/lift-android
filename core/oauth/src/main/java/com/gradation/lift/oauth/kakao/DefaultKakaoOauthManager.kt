package com.gradation.lift.oauth.kakao

import com.gradation.lift.common.model.DataState
import com.kakao.sdk.user.UserApiClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

class DefaultKakaoOauthManager @Inject constructor() : KakaoOauthManager {
    override fun getUserId(): Flow<DataState<String>> = flow {
        emit(suspendCancellableCoroutine { continuation ->
            UserApiClient.instance.me() { user, error ->
                user?.let {
                    continuation.resume(DataState.Success(it.id.toString()))
                }
                error?.let {
                    continuation.resume(
                        DataState.Fail(
                            it.message ?: "사용자 아이디를 불러올 수 없습니다."
                        )
                    )
                }
            }
        })
    }


    override fun signOut(): Flow<DataState<Unit>> = flow {
        emit(suspendCancellableCoroutine { continuation ->
            UserApiClient.instance.logout { error ->
                error?.message?.let {
                    continuation.resume(DataState.Fail(it))
                } ?: DataState.Success(Unit)

            }
        })
    }
}