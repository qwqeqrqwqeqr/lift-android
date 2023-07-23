package com.gradation.lift

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.accompanist.systemuicontroller.SystemUiController
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.auth.IsSignedUseCase
import com.gradation.lift.domain.usecase.setting.GetAutoLoginSettingUseCase
import com.gradation.lift.domain.usecase.user.ExistUserDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject


@HiltViewModel
class MainActivityViewModel @Inject constructor(
    isSignedUseCase: IsSignedUseCase,
    existUserDetailUseCase: ExistUserDetailUseCase,
    private val getAutoLoginSettingUseCase: GetAutoLoginSettingUseCase,
) : ViewModel() {

    val splashUiState = isSignedUseCase().combine(
        existUserDetailUseCase()
    ) { isSigned, existUserDetail ->
        when (isSigned) {
            is DataState.Fail -> SplashUiState.Login
            is DataState.Success -> {
                if (!getAutoLoginSettingUseCase().first()) {
                    SplashUiState.Login
                } else {
                    when (existUserDetail) {
                        is DataState.Fail -> SplashUiState.Login
                        is DataState.Success -> {
                            if (!isSigned.data) {
                                SplashUiState.Login
                            } else {
                                if (existUserDetail.data) SplashUiState.Main else SplashUiState.RegisterDetail
                            }
                        }
                    }
                }
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = SplashUiState.Loading
    )

    fun setDefaultSystemUiController(systemUiController: SystemUiController) {
        systemUiController.setStatusBarColor(Color.White)
        systemUiController.setNavigationBarColor(Color.White)
        systemUiController.setSystemBarsColor(Color.White)
    }

}


sealed interface SplashUiState {
    object Loading : SplashUiState
    object Main : SplashUiState
    object Login : SplashUiState

    object RegisterDetail : SplashUiState
}