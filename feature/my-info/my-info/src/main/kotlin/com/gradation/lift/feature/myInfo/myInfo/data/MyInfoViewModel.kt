package com.gradation.lift.feature.myInfo.myInfo.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.user.GetUserDetailUseCase
import com.gradation.lift.feature.myInfo.myInfo.data.state.MyInfoState
import com.gradation.lift.feature.myInfo.myInfo.data.state.MyInfoUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject


/**
 * [MyInfoViewModel]
 * @property myInfoUiState 사용자 상세정보 상태
 * @since 2024-01-12 13:57:57
 */
@HiltViewModel
class MyInfoViewModel @Inject constructor(
    getUserDetailUseCase: GetUserDetailUseCase,
) : ViewModel() {


    internal val myInfoUiState: StateFlow<MyInfoUiState> = getUserDetailUseCase().map {
        when (it) {
            is DataState.Fail -> MyInfoUiState.Fail(it.message)
            is DataState.Success -> MyInfoUiState.Success(
                it.data.let {
                    MyInfoState(
                        name = it.name,
                        profilePicture = it.profilePicture
                    )
                }
            )
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = MyInfoUiState.Loading
    )




}