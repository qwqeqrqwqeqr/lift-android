package com.gradation.lift.feature.register_detail.profile_picture

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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterDetailProfilePictureViewModel @Inject constructor(
    private val createUserDetailUseCase: CreateUserDetailUseCase,
) : ViewModel() {


    var onVisibleDialog = MutableStateFlow(false)


    fun createUserDetail(navController: NavController) {
        viewModelScope.launch {
            createUserDetailUseCase(
                navController.findValueInBackStackEntry(
                    listOf(
                        SavedStateHandleKey.RegisterDetailKey.NAME_KEY,
                        SavedStateHandleKey.RegisterDetailKey.GENDER_KEY,
                        SavedStateHandleKey.RegisterDetailKey.HEIGHT_KEY,
                        SavedStateHandleKey.RegisterDetailKey.WEIGHT_KEY,
                        SavedStateHandleKey.RegisterDetailKey.UNIT_OF_WEIGHT_KEY,
                    )
                ).let {
                    UserDetail(
                        name = it[SavedStateHandleKey.RegisterDetailKey.NAME_KEY]?: "",
                        gender = when (it[SavedStateHandleKey.RegisterDetailKey.GENDER_KEY] ?: "") {
                            Gender.MALE_VALUE -> Gender.Male()
                            Gender.FEMALE_VALUE -> Gender.Female()
                            else -> Gender.Male()
                        },
                        height = it[SavedStateHandleKey.RegisterDetailKey.HEIGHT_KEY]?.toFloat()
                            ?: 0f,
                        weight = it[SavedStateHandleKey.RegisterDetailKey.WEIGHT_KEY]?.toFloat()
                            ?: 0f,
                        //TODO profilePicture 추가
                        profilePicture = null,
                        unitOfWeight = when (it[SavedStateHandleKey.RegisterDetailKey.UNIT_OF_WEIGHT_KEY] ?: "") {
                            UnitOfWeight.KG_VALUE -> UnitOfWeight.Kg()
                            UnitOfWeight.LB_VALUE -> UnitOfWeight.Lb()
                            else -> UnitOfWeight.Kg()
                        },
                    )
                }
            ).collect {
                when (it) {
                    is DataState.Success -> onVisibleDialog.value = it.data
                    is DataState.Fail -> onVisibleDialog.value = false
                }
            }
        }
    }

}
