package com.gradation.lift.feature.register_detail.height_weight

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.gradation.lift.common.utils.Validator
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey
import com.gradation.lift.navigation.saved_state.setFloatValue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class RegisterDetailHeightWeightViewModel @Inject constructor(

) : ViewModel() {

    var weight = MutableStateFlow("")
    var height = MutableStateFlow("")

    var weightValidationSupportText: StateFlow<Validator> =
        weight.mapLatest {
            it.toFloatOrNull()?.let { value ->
                if (value in 1f..300f) {
                    Validator(true, "")
                } else {
                    Validator(false, "정확한 정보를 입력해주세요.")
                }
            } ?: Validator(false, "")
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = Validator()
        )

    var heightValidationSupportText: StateFlow<Validator> =
        height.mapLatest {
            it.toFloatOrNull()?.let { value ->
                if (value in 1f ..300f) {
                    Validator(true, "")
                } else {
                    Validator(false, "정확한 정보를 입력해주세요.")
                }
            } ?: Validator(false, "")
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = Validator()
        )

    internal fun updateWeight(): (String) -> Unit = { it ->
        weight.value = it.toFloatOrNull().let { value ->
            value?.toString() ?: ""
        }
    }

    internal fun updateHeight(): (String) -> Unit = { it ->
        height.value = it.toFloatOrNull().let { value ->
            value?.toString() ?: ""
        }
    }

    var navigateCondition: StateFlow<Boolean> =
        weightValidationSupportText.combine(heightValidationSupportText) { weight, height ->
            weight.status && height.status
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false
        )

    fun updateKey(navController: NavController) {
        navController.setFloatValue(
            SavedStateHandleKey.RegisterDetailKey.WEIGHT_KEY,
            weight.value.toFloat()
        )
        navController.setFloatValue(
            SavedStateHandleKey.RegisterDetailKey.HEIGHT_KEY,
            height.value.toFloat()
        )
    }

}
