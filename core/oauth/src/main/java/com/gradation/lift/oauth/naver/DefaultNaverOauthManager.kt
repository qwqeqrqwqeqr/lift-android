package com.gradation.lift.oauth.naver

import com.gradation.lift.common.model.DataState
import com.navercorp.nid.NaverIdLoginSDK
import com.navercorp.nid.oauth.NidOAuthLogin
import com.navercorp.nid.profile.NidProfileCallback
import com.navercorp.nid.profile.data.NidProfileResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

class DefaultNaverOauthManager @Inject constructor() : NaverOauthManager {
    override fun getUserId(): Flow<DataState<String>> = flow {
        emit(suspendCancellableCoroutine { continuation ->
            NidOAuthLogin().callProfileApi(callback = object :
                NidProfileCallback<NidProfileResponse> {
                override fun onError(errorCode: Int, message: String) {
                    onFailure(errorCode, message)
                }

                override fun onFailure(httpStatus: Int, message: String) {
                    continuation.resume(
                        DataState.Fail(message)
                    )
                }

                override fun onSuccess(result: NidProfileResponse) {
                    continuation.resume(
                        result.profile?.id?.let { DataState.Success(it) }
                            ?: DataState.Fail("사용자 아이디를 불러올 수 없습니다.")
                    )
                }
            })
        })
    }


    override fun signOut() {
        NaverIdLoginSDK.logout()
    }
}
