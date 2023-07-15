package com.gradation.lift.feature.register_detail.name

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.gradation.lift.common.model.DataState
import com.gradation.lift.common.utils.Validator
import com.gradation.lift.common.utils.nameValidator
import com.gradation.lift.domain.usecase.checker.CheckerDuplicateNameUseCase
import com.gradation.lift.model.user.Name
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey
import com.gradation.lift.navigation.saved_state.setStringValue
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@FlowPreview
@HiltViewModel
class RegisterDetailNameViewModel @Inject constructor(
    private val checkerDuplicateNameUseCase: CheckerDuplicateNameUseCase,
) : ViewModel() {

    var name = MutableStateFlow("")

    var nameValidationSupportText: StateFlow<Validator> =
        name.debounce(1000).distinctUntilChanged().flatMapLatest { name ->
            checkDuplicateName(name)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = Validator()
        )

    var navigateCondition : StateFlow<Boolean> =  nameValidationSupportText.map { it.status }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = false
    )

    internal fun updateName(): (String) -> Unit = { it ->
        viewModelScope.launch {
            name.value = it
        }
    }


    fun updateKey(navController: NavController) {
        navController.setStringValue(SavedStateHandleKey.RegisterDetailKey.NAME_KEY, name.value)
    }


    private fun checkDuplicateName(name: String): Flow<Validator> {
        return checkerDuplicateNameUseCase(Name(name)).map {
            when (it) {
                is DataState.Fail -> Validator(false, "")
                is DataState.Success -> if(it.data){
                    Validator(false, "이미 사용중인 닉네임이에요")
                }else{
                    if(nameValidator(name)){
                        Validator(true, "")
                    }else{
                        if(name.isBlank()){
                            Validator(false, "")
                        }else{
                            Validator(false, "2~5 자리의 한글만 사용 가능해요")
                        }
                    }
                }
            }
        }
    }

}



