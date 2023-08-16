package com.gradation.lift.oauth

import com.gradation.lift.common.model.DataState
import com.kakao.sdk.user.UserApiClient
import com.navercorp.nid.profile.NidProfileCallback
import com.navercorp.nid.profile.data.NidProfileResponse
import kotlin.coroutines.resume

//class DefaultOAuthRepository(
//    private val authDataSource: AuthDataSource,
//    private val kakaoDataSource: com.gradation.lift.oauth.kakao.KakaoDataSource,
//    private val naverDataSource: com.gradation.lift.oauth.naver.NaverDataSource,
//    private val tokenDataStoreDataSource: TokenDataStoreDataSource,
//) : OAuthRepository {
//
//
//    override fun signInKakao(): Flow<DataState<Boolean>> = flow {
//        kakaoDataSource.signIn().collect { result ->
//            when (result) {
//                is NetworkResult.Fail -> emit(DataState.Fail(result.message))
//                is NetworkResult.Success -> authDataSource.signInKakao(KakaoSignInInfo(result.data))
//                    .collect {
//                        when (it) {
//                            is NetworkResult.Fail -> {
//                                emit(DataState.Fail(it.message))
//                            }
//                            is NetworkResult.Success -> {
//                                try {
//                                    tokenDataStoreDataSource.setAccessToken(it.data.accessToken)
//                                    tokenDataStoreDataSource.setRefreshToken(it.data.refreshToken)
//                                    tokenDataStoreDataSource.setLoginMethod(LoginMethod.Kakao())
//                                } catch (t: Throwable) {
//                                    emit(DataState.Fail(t.message ?: "로그인을 실패하였습니다."))
//                                } finally {
//                                    emit(DataState.Success(true))
//                                }
//                            }
//                        }
//                    }
//            }
//        }
//    }
//
//    override fun signInNaver(): Flow<DataState<Boolean>> = flow {
//        naverDataSource.signIn().collect { result ->
//            when (result) {
//                is NetworkResult.Fail -> emit(DataState.Fail(result.message))
//                is NetworkResult.Success -> authDataSource.signInNaver(NaverSignInInfo(result.data))
//                    .collect {
//                        when (it) {
//                            is NetworkResult.Fail -> {
//                                emit(DataState.Fail(it.message))
//                            }
//                            is NetworkResult.Success -> {
//                                try {
//                                    tokenDataStoreDataSource.setAccessToken(it.data.accessToken)
//                                    tokenDataStoreDataSource.setRefreshToken(it.data.refreshToken)
//                                    tokenDataStoreDataSource.setLoginMethod(LoginMethod.Naver())
//                                } catch (t: Throwable) {
//                                    emit(DataState.Fail(t.message ?: "로그인을 실패하였습니다."))
//                                } finally {
//                                    emit(DataState.Success(true))
//                                }
//                            }
//                        }
//                    }
//            }
//        }
//    }
//
//}



//NidOAuthLogin().callProfileApi(callback = object :
//    NidProfileCallback<NidProfileResponse> {
//    override fun onError(errorCode: Int, message: String) {
//        onFailure(errorCode, message)
//    }
//
//    override fun onFailure(httpStatus: Int, message: String) {
//        DataState.Fail(message)
//    }
//
//    override fun onSuccess(result: NidProfileResponse) {
//        continuation.resume(
//            result.profile?.id?.let { DataState.Success(it) }
//                ?: DataState.Fail("계정이 존재하지 않습니다.")
//        )
//    }
//
//})



//emit(suspendCancellableCoroutine { continuation ->
//    UserApiClient.instance.me() { user, error ->
//        user?.let {
//            continuation.resume(DataState.Success(it.id.toString()))
//        }
//        error?.let {
//            continuation.resume(
//                DataState.Fail(
//                    it.message ?: "사용자 아이디를 불러올 수 없습니다."
//                )
//            )
//        }
//    }
//})