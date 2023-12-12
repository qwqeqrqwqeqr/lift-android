package com.gradation.lift.oauth.common

import android.content.Context
import com.kakao.sdk.common.KakaoSdk
import com.navercorp.nid.NaverIdLoginSDK


/**
 *  [kakaoInitializer]
 *  최상단의 Application 클래스에서 사용할 것
 *  @param context application context 사용
 *  @param kakaoAppKey 카카오 앱 키 (개발자 사이트 내 정의)
 *  @since 2023-08-16 11:04:00
 */
fun kakaoInitializer(
    context: Context,
    kakaoAppKey: String,
) {
    KakaoSdk.init(context = context, kakaoAppKey)
}

/**
 * [naverInitializer]
 * @param context : activity context 사용
 * @param naverOauthClientId 네이버 클라이언트 키 (개발자 사이트 내 정의)
 * @param naverOauthClientName 클라이언트 이름
 * @param naverOauthClientSecret 네이버 시크릿 키 (개발자 사이트 내 정의)
 * @since 2023-08-16 11:09:55
 */
fun naverInitializer(
    context: Context,
    naverOauthClientId: String,
    naverOauthClientSecret: String,
    naverOauthClientName: String,
) {
    NaverIdLoginSDK.initialize(
        context = context,
        clientId = naverOauthClientId,
        clientSecret = naverOauthClientSecret,
        clientName = naverOauthClientName
    )
}
