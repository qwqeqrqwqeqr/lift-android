package com.gradation.lift.network.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DefaultHttpClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthHttpClient


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DefaultRetrofit

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthRetrofit

