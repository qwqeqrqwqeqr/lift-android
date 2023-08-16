package com.gradation.lift.oauth.common

import android.content.Context
import com.gradation.lift.oauth.state.OAuthConnectState
import com.kakao.sdk.user.UserApiClient
import com.navercorp.nid.NaverIdLoginSDK
import com.navercorp.nid.oauth.OAuthLoginCallback
import dagger.hilt.android.qualifiers.ActivityContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

class DefaultOAuthConnectManager @Inject constructor(
    @ActivityContext private val context: Context,
) :OAuthConnectManager{


    override fun connectKakao(): Flow<OAuthConnectState> = flow {

        emit(suspendCancellableCoroutine { continuation ->
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
                UserApiClient.instance.loginWithKakaoTalk(
                    context,
                    callback = { token, error ->
                        token?.let { continuation.resume(OAuthConnectState.Success) }
                        error?.let { continuation.resume(OAuthConnectState.Fail("로그인을 실패하였습니다.")) }
                    })
            } else {
                UserApiClient.instance.loginWithKakaoAccount(
                    context,
                    callback = { token, error ->
                        token?.let { continuation.resume(OAuthConnectState.Success) }
                        error?.let { continuation.resume(OAuthConnectState.Fail("로그인을 실패하였습니다.")) }
                    })
            }
        })
    }

    override fun connectNaver(): Flow<OAuthConnectState> = flow {

        emit(
            suspendCancellableCoroutine { continuation ->
                NaverIdLoginSDK.authenticate(context, callback = object : OAuthLoginCallback {
                    override fun onError(errorCode: Int, message: String) {
                        onFailure(errorCode, message)
                    }

                    override fun onFailure(httpStatus: Int, message: String) {
                        continuation.resume(OAuthConnectState.Fail(message))
                    }

                    override fun onSuccess() {
                        continuation.resume(OAuthConnectState.Success)
                    }
                }
                )
            })
    }

}