package com.gradation.lift.network.datasource.naver

import android.content.Context
import com.gradation.lift.network.common.NetworkResult
import com.kakao.sdk.user.UserApiClient
import com.navercorp.nid.NaverIdLoginSDK
import com.navercorp.nid.oauth.NidOAuthLogin
import com.navercorp.nid.oauth.OAuthLoginCallback
import com.navercorp.nid.profile.NidProfileCallback
import com.navercorp.nid.profile.data.NidProfileResponse
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

class DefaultNaverDataSource @Inject constructor(
    @ApplicationContext private val context: Context,
) : NaverDataSource {

    override fun signIn(): Flow<NetworkResult<String>> = flow {

        emit(suspendCancellableCoroutine { continuation ->

            NaverIdLoginSDK.authenticate(context, callback = object : OAuthLoginCallback {
                override fun onError(errorCode: Int, message: String) {
                    onFailure(errorCode, message)
                }

                override fun onFailure(httpStatus: Int, message: String) {
                    NetworkResult.Fail(message)
                }

                override fun onSuccess() {
                    NidOAuthLogin().callProfileApi(callback = object :
                        NidProfileCallback<NidProfileResponse> {
                        override fun onError(errorCode: Int, message: String) {
                            onFailure(errorCode, message)
                        }

                        override fun onFailure(httpStatus: Int, message: String) {
                            NetworkResult.Fail(message)
                        }

                        override fun onSuccess(result: NidProfileResponse) {
                            continuation.resume(
                                result.profile?.id?.let { NetworkResult.Success(it) }
                                    ?: NetworkResult.Fail("계정이 존재하지 않습니다.")
                            )
                        }

                    })
                }
            }
            )
        })
    }
}