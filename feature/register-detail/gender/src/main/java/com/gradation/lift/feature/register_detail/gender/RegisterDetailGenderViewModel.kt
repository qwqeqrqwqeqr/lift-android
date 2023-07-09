package com.gradation.lift.feature.register_detail.gender

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey
import com.gradation.lift.navigation.saved_state.setStringValue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class RegisterDetailGenderViewModel @Inject constructor(

) : ViewModel() {


    var male by mutableStateOf(true)
    var female by mutableStateOf(false)

    internal fun updateMale(): (Boolean) -> Unit = {
        if (!male) {
            male = it
            female = !it
        }
    }

    internal fun updateFemale(): (Boolean) -> Unit = {
        if (!female) {
            female = it
            male = !it
        }
    }

    fun updateKey(navController: NavController, name: String) {
        navController.setStringValue(
            SavedStateHandleKey.RegisterDetailKey.GENDER_KEY,
            if (male) "male" else "female"
        )

        navController.setStringValue(
            SavedStateHandleKey.RegisterDetailKey.NAME_KEY,
            name
        )


    }

}
