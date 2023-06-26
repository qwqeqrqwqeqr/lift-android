package com.gradation.lift.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.auth.IsSignedUseCase
import com.gradation.lift.feature.routine.viewmodel.WeekDateRoutineUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject


@HiltViewModel
class SplashViewModel @Inject constructor(
    private val isSignedUseCase: IsSignedUseCase,
) : ViewModel() {

    val splashUiState:StateFlow<SplashUiState> = splashUiState(isSignedUseCase()).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = SplashUiState.Loading
    )


}




