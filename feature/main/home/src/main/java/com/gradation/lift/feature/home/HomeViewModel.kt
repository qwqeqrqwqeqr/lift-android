package com.gradation.lift.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.auth.SignInUseCase
import com.gradation.lift.model.auth.SignInInfo
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    ) :
    ViewModel() {



    var test =
        signInUseCase(
            SignInInfo(
                "rhdtkdxor",
                "rhdtkdxor12#"
            )
        ).map {
            when(it){
                is DataState.Error -> "에러"
                is DataState.Fail -> "실패"
                is DataState.Success -> "${it.data}"
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = "로딩"
        )



}



sealed interface HomeUiState {
    data class Success(val userData: List<com.gradation.lift.model.work.WorkCategory>) : HomeUiState
    object Loading : HomeUiState
    object Empty : HomeUiState
    object Error : HomeUiState
}