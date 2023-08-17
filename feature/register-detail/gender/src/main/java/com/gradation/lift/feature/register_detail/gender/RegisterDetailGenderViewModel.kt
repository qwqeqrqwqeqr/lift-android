package com.gradation.lift.feature.register_detail.gender

import androidx.lifecycle.ViewModel
import com.gradation.lift.model.model.user.Gender
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

/**
 * [RegisterDetailGenderViewModel]
 * @property gender 사용자의 성별
 * @since 2023-08-17 23:02:47
 */
@HiltViewModel
class RegisterDetailGenderViewModel @Inject constructor() : ViewModel() {


    var gender: MutableStateFlow<Gender> = MutableStateFlow(Gender.Male())

    internal fun updateMale(): () -> Unit = {
        gender.value = Gender.Male()
    }

    internal fun updateFemale(): () -> Unit = {
        gender.value = Gender.Female()
    }


}
