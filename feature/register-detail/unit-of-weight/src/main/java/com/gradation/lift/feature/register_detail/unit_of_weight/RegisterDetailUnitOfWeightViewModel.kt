package com.gradation.lift.feature.register_detail.unit_of_weight

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.user.CreateUserDetailUseCase
import com.gradation.lift.model.common.UnitOfWeight
import com.gradation.lift.model.user.Gender
import com.gradation.lift.model.user.UserDetail
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey
import com.gradation.lift.navigation.saved_state.findValueInBackStackEntry
import com.gradation.lift.navigation.saved_state.setValueSavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterDetailUnitOfWeightViewModel @Inject constructor(
    private val createUserDetailUseCase: CreateUserDetailUseCase,
) : ViewModel() {


    var kg by mutableStateOf(true)
    var lb by mutableStateOf(false)

    internal fun updateKg(): (Boolean) -> Unit = {
        if (!kg) {
            kg = it
            lb = !it
        }
    }

    internal fun updateLb(): (Boolean) -> Unit = {
        if (!lb) {
            lb = it
            kg = !it
        }
    }





    fun updateKey(navController: NavController) {
        navController.setValueSavedStateHandle(
            SavedStateHandleKey.RegisterDetailKey.UNIT_OF_WEIGHT_KEY,
            if (kg) "kg" else "lb"

        )
    }

}
