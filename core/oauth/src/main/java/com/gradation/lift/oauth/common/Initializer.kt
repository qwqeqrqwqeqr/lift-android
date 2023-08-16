package com.gradation.lift.oauth.common

import android.content.Context
import com.kakao.sdk.common.KakaoSdk
import com.navercorp.nid.NaverIdLoginSDK


fun kakaoInitializer(
    context: Context,
    kakaoAppKey: String,
) {
    KakaoSdk.init(context = context, kakaoAppKey)
}

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
