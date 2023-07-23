package com.gradation.lift.feature.register_detail.gender

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.gradation.lift.model.user.Gender
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey
import com.gradation.lift.navigation.saved_state.setValueSavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class RegisterDetailGenderViewModel @Inject constructor(

) : ViewModel() {


    var gender by mutableStateOf(true)

    internal fun updateMale(): (Boolean) -> Unit = {
        if(!gender){
            gender = true
        }
    }

    internal fun updateFemale(): (Boolean) -> Unit = {
        if(gender) {
            gender = false
        }
    }

    fun updateKey(navController: NavController) {
        navController.setValueSavedStateHandle(
            SavedStateHandleKey.RegisterDetailKey.GENDER_KEY,
            if (gender) Gender.MALE_VALUE else Gender.FEMALE_VALUE
        )
    }

}
