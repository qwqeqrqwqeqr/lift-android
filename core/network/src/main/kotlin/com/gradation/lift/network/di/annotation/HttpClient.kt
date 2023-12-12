package com.gradation.lift.network.di.annotation

import javax.inject.Qualifier

/**
 * 인증 인가를 통해 접근해야 하는 API와
 * 인증인가 없이도 접근할 수 있는 API가 있어
 * 케이스에 따라 주입받을 수 있도록 해당 클래스를 생성함
 * @since 2023-08-28 22:27:06
 */

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthHttpClient


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DefaultHttpClient