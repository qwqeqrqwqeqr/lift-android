package com.gradation.lift.feature.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.auth.IsSignedUseCase
import com.gradation.lift.domain.usecase.auth.SignOutUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val signOutUseCase: SignOutUseCase,
    private val isSignedUseCase: IsSignedUseCase,
) :
    ViewModel() {


        fun signOut(){
            viewModelScope.launch {
                signOutUseCase()
                isSignedUseCase().map {
                    when(it){
                        is DataState.Fail ->  Log.d("test","실패")
                        is DataState.Success -> Log.d("test",it.data.toString())
                    }

                }
            }

        }
}


sealed interface HomeUiState {
    data class Success(val userData: List<com.gradation.lift.model.work.WorkCategory>) : HomeUiState
    object Loading : HomeUiState
    object Empty : HomeUiState
    object Error : HomeUiState
}