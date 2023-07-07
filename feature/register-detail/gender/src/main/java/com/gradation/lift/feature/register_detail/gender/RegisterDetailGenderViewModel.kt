package com.gradation.lift.feature.register_detail.gender

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey
import com.gradation.lift.navigation.saved_state.setStringValue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class RegisterDetailGenderViewModel  @Inject constructor(

): ViewModel() {

    var gender = MutableStateFlow(true)



    fun updateKey(navController: NavController) {
        navController.setStringValue(SavedStateHandleKey.RegisterDetailKey.GENDER_KEY, if(gender.value) "male" else "female")
    }

}
