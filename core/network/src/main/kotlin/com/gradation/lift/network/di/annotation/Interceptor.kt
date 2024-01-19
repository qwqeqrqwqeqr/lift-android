package com.gradation.lift.network.di.annotation

import javax.inject.Qualifier

/**
 * Interceptor DI를 위한 Annotation
 * @since 2024-01-06 14:29:57
 */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthNetworkInterceptor

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RetryNetworkInterceptor


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NetworkConnectivityInterceptor

