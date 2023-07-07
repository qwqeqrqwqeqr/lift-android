package com.gradation.lift.feature.register_detail.unit_of_weight

import android.icu.util.MeasureUnit
import android.icu.util.MeasureUnit.POUND
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
import com.gradation.lift.navigation.saved_state.getFloatValue
import com.gradation.lift.navigation.saved_state.getStringValue
import com.gradation.lift.navigation.saved_state.setStringValue
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

    var onVisibleDialog = MutableStateFlow(false)


    fun createUserDetail(navController: NavController) {
        viewModelScope.launch {
            createUserDetailUseCase(UserDetail(
                name = navController.getStringValue(SavedStateHandleKey.RegisterDetailKey.NAME_KEY),
                gender = when (            navController.getStringValue(SavedStateHandleKey.RegisterDetailKey.GENDER_KEY)) {
                    Gender.MALE_VALUE -> Gender.MALE()
                    Gender.FEMALE_VALUE -> Gender.FEMALE()
                    else -> Gender.MALE()
                },
                height = navController.getFloatValue(SavedStateHandleKey.RegisterDetailKey.HEIGHT_KEY),
                weight = navController.getFloatValue(SavedStateHandleKey.RegisterDetailKey.WEIGHT_KEY),
                unitOfWeight = when (if (kg) UnitOfWeight.KG_VALUE else UnitOfWeight.LB_VALUE) {
                    UnitOfWeight.KG_VALUE -> UnitOfWeight.KG()
                    UnitOfWeight.LB_VALUE -> UnitOfWeight.LB()
                    else -> UnitOfWeight.KG()
                },
            )).collect {
                when(it){
                    is DataState.Success -> onVisibleDialog.value = it.data
                    is DataState.Fail -> onVisibleDialog.value = false
                }
            }
        }
    }


    fun updateKey(navController: NavController) {
        navController.setStringValue(
            SavedStateHandleKey.RegisterDetailKey.UNIT_OF_WEIGHT_KEY,
            if (kg) "kg" else "lb"
        )
    }


}
