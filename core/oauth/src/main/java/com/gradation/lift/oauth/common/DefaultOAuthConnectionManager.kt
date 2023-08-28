package com.gradation.lift.oauth.common

import android.content.Context
import com.gradation.lift.common.model.DataState
import com.kakao.sdk.user.UserApiClient
import com.navercorp.nid.NaverIdLoginSDK
import com.navercorp.nid.oauth.OAuthLoginCallback
import dagger.hilt.android.qualifiers.ActivityContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

class DefaultOAuthConnectionManager @Inject constructor(
    @ActivityContext private val context: Context,
) :OAuthConnectionManager{


    override fun connectKakao(): Flow<DataState<Unit>> = flow {

        emit(suspendCancellableCoroutine { continuation ->
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
                UserApiClient.instance.loginWithKakaoTalk(
                    context,
                    callback = { token, error ->
                        token?.let { continuation.resume(DataState.Success(Unit)) }
                        error?.let { continuation.resume(DataState.Fail("로그인을 실패하였습니다.")) }
                    })
            } else {
                UserApiClient.instance.loginWithKakaoAccount(
                    context,
                    callback = { token, error ->
                        token?.let { continuation.resume(DataState.Success(Unit)) }
                        error?.let { continuation.resume(DataState.Fail("로그인을 실패하였습니다.")) }
                    })
            }
        })
    }

    override fun connectNaver(): Flow<DataState<Unit>> = flow {

        emit(
            suspendCancellableCoroutine { continuation ->
                NaverIdLoginSDK.authenticate(context, callback = object : OAuthLoginCallback {
                    override fun onError(errorCode: Int, message: String) {
                        onFailure(errorCode, message)
                    }

                    override fun onFailure(httpStatus: Int, message: String) {
                        continuation.resume(DataState.Fail(message))
                    }

                    override fun onSuccess() {
                        continuation.resume(DataState.Success(Unit))
                    }
                }
                )
            })
    }

}