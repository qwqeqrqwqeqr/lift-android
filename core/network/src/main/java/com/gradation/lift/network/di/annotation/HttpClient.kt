package com.gradation.lift.network.di.annotation

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthHttpClient


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DefaultHttpClient