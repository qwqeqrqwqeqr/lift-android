package com.gradation.lift

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.auth.IsSignedUseCase
import com.gradation.lift.domain.usecase.setting.GetAutoLoginSettingUseCase
import com.gradation.lift.domain.usecase.user.ExistUserDetailUseCase
import com.gradation.lift.state.SplashState
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
            is DataState.Fail -> SplashState.Login
            is DataState.Success -> {
                if (!getAutoLoginSettingUseCase().first()) {
                    SplashState.Login
                } else {
                    when (existUserDetail) {
                        is DataState.Fail -> SplashState.Login
                        is DataState.Success -> {
                            if (!isSigned.data) {
                                SplashState.Login
                            } else {
                                if (existUserDetail.data) SplashState.Main else SplashState.RegisterDetail
                            }
                        }
                    }
                }
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = SplashState.Loading
    )



}


