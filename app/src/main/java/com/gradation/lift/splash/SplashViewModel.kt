package com.gradation.lift.splash

import androidx.lifecycle.ViewModel
import com.gradation.lift.domain.usecase.auth.IsSignedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SplashViewModel @Inject constructor(
   private val isSignedUseCase: IsSignedUseCase
): ViewModel() {

}