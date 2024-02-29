package com.gradation.lift.feature.myInfo.cancelMembershipConfirm.data

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.auth.SignOutUseCase
import com.gradation.lift.domain.usecase.user.DeleteUserUseCase
import com.gradation.lift.domain.usecase.user.GetUserDetailUseCase
import com.gradation.lift.model.model.user.DeleteUserInfo
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey.MyInfo.MY_INFO_CANCEL_MEMBERSHIP_REASON_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * [CancelMembershipConfirmViewModel]
 */
@HiltViewModel
class CancelMembershipConfirmViewModel @Inject constructor(
    private val deleteUserUseCase: DeleteUserUseCase,
    getUserDetailUseCase: GetUserDetailUseCase,
    signOutUseCase: SignOutUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val reason: StateFlow<String> =
        savedStateHandle.getStateFlow(MY_INFO_CANCEL_MEMBERSHIP_REASON_KEY, "")

    val name: StateFlow<String> = getUserDetailUseCase().map {
        when (it) {
            is DataState.Fail -> ""
            is DataState.Success -> it.data.name
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = ""
    )

    val deleteUserState: MutableStateFlow<DeleteUserState> = MutableStateFlow(DeleteUserState.None)
    val updateDeleteUserState: (DeleteUserState) -> Unit = { deleteUserState.value = it }

    val deleteUser: () -> Unit = {

        viewModelScope.launch {
            deleteUserUseCase(DeleteUserInfo(reason = reason.value)).collect {
                when (it) {
                    is DataState.Fail -> updateDeleteUserState(DeleteUserState.Fail(it.message))
                    is DataState.Success -> {
                        signOutUseCase().collect()
                        updateDeleteUserState(DeleteUserState.Success)
                    }
                }
            }
        }
    }

}

