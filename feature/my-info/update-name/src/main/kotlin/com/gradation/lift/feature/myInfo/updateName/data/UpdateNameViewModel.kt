package com.gradation.lift.feature.myInfo.updateName.data

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.common.utils.Validator
import com.gradation.lift.common.utils.nameValidator
import com.gradation.lift.domain.usecase.checker.CheckerDuplicateNameUseCase
import com.gradation.lift.domain.usecase.user.UpdateUserDetailNameUseCase
import com.gradation.lift.feature.myInfo.updateName.data.state.UpdateNameState
import com.gradation.lift.model.model.user.UserDetailName
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateNameViewModel @Inject constructor(
    updateUserDetailNameUseCase: UpdateUserDetailNameUseCase,
    private val checkerDuplicateNameUseCase: CheckerDuplicateNameUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {


    private val defaultName: StateFlow<String> = savedStateHandle.getStateFlow(SavedStateHandleKey.MyInfo.USER_NAME_KEY, "")
    internal val nameText: MutableStateFlow<String> = MutableStateFlow("")


    internal  var nameValidator: StateFlow<Validator> =
        nameText.flatMapLatest { name ->
            if (name.isEmpty()) flowOf(Validator(true, ""))
            else if (!nameValidator(name)) flowOf(Validator(false, "2~8 자리의 한글만 사용 가능해요."))
            else if (defaultName.value == name) flowOf(Validator(false, "기존에 사용하던 닉네임과 동일해요."))
            else checkDuplicateName(name)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = Validator(true, "")
        )


    internal val updateNameState: MutableStateFlow<UpdateNameState> =
        MutableStateFlow(UpdateNameState.None)
    internal val updateUpdateNameState: (UpdateNameState) -> Unit = {
        updateNameState.value = it
    }

    val updateNameText: (String) -> Unit = {nameText.value =it}
    val clearNameText: () -> Unit = {nameText.value =""}

    internal val updateName: () -> Unit = {
        viewModelScope.launch {
            updateUserDetailNameUseCase(
                UserDetailName(nameText.value)
            ).collect {
                when (it) {
                    is DataState.Fail -> {
                        updateNameState.value =
                            UpdateNameState.Fail(message = "사용자의 닉네임 변경을 실패하였습니다.\n잠시후에 다시 시도해주세요.")
                    }

                    is DataState.Success -> {
                        if (it.data) {
                            updateNameState.value = UpdateNameState.Success
                        } else {
                            updateNameState.value =  UpdateNameState.Fail(
                                message = it.message ?: "사용자의 닉네임 변경을 실패하였습니다.\n잠시후에 다시 시도해주세요."
                            )
                        }
                    }
                }
            }
        }
    }

    private fun checkDuplicateName(name: String): Flow<Validator> {
        return checkerDuplicateNameUseCase(name).debounce(1000).distinctUntilChanged().map {
            when (it) {
                is DataState.Fail -> Validator(false, "")
                is DataState.Success ->
                    if (it.data) Validator(false, "이미 사용중인 닉네임이에요.")
                    else Validator(true, "")
            }
        }
    }
}
