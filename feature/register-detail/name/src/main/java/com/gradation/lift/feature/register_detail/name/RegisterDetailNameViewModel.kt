package com.gradation.lift.feature.register_detail.name

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.gradation.lift.common.model.DataState
import com.gradation.lift.common.utils.Validator
import com.gradation.lift.common.utils.emailValidator
import com.gradation.lift.domain.usecase.checker.CheckerDuplicateNameUseCase
import com.gradation.lift.model.user.Name
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey
import com.gradation.lift.navigation.saved_state.setStringValue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterDetailNameViewModel @Inject constructor(
    private val checkerDuplicateNameUseCase: CheckerDuplicateNameUseCase,
) : ViewModel() {

    var name by mutableStateOf("")
    var nameValidationSupportText by mutableStateOf(Validator())
    var navigateCondition by mutableStateOf(false)


    internal fun updateName(): (String) -> Unit = {
        name = it
        validateName(it)
        updateNavigateCondition()
    }


    @OptIn(FlowPreview::class)
    private fun validateName(name: String) {
        viewModelScope.launch {
            checkerDuplicateNameUseCase(Name(name)).debounce(1000L).map {
                nameValidationSupportText = when (it) {
                    is DataState.Error -> Validator(false, "")
                    is DataState.Fail -> Validator(true, "")
                    is DataState.Success -> Validator(false, "이미 사용중인 닉네임이에요")
                }
            }
        }
    }


    fun updateKey(navController: NavController) {
        navController.setStringValue(SavedStateHandleKey.RegisterDetailKey.NAME_KEY, name)
    }
}

