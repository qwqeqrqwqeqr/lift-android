package com.gradation.lift.feature.register_detail.unit_of_weight

import android.icu.util.MeasureUnit
import android.icu.util.MeasureUnit.POUND
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey
import com.gradation.lift.navigation.saved_state.setStringValue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class RegisterDetailUnitOfWeightViewModel  @Inject constructor(

): ViewModel() {


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

    var onVisibleDialog  =  MutableStateFlow(false)




    fun updateKey(navController: NavController) {
        navController.setStringValue(
            SavedStateHandleKey.RegisterDetailKey.UNIT_OF_WEIGHT_KEY,
            if (kg) "kg" else "lb"
        )
    }


}
