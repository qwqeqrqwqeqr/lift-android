package com.gradation.lift.feature.my_info.update

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.checker.CheckerDuplicateNameUseCase
import com.gradation.lift.domain.usecase.user.GetUserDetailUseCase
import com.gradation.lift.domain.usecase.user.UpdateUserDetailUseCase
import com.gradation.lift.feature.my_info.update.data.state.GenderState
import com.gradation.lift.feature.my_info.update.data.state.HeightWeightState
import com.gradation.lift.feature.my_info.update.data.state.NameState
import com.gradation.lift.feature.my_info.update.data.state.UpdateUserDetailState
import com.gradation.lift.model.model.user.UserDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * [MyInfoUpdateViewModel]
 * @property userDetail 프로필 사진을 업데이트 하기 위한 사용자의 아이디 등 사용자를 판별할 수 있는 정보
 * @property updateUserDetailState 프로필 업데이트 상태 (해당 상태가 [DataState.Success] 일경우 프로필이 업데이트 됨
 * @since 2023-08-26 13:09:38
 */
@HiltViewModel
class MyInfoUpdateViewModel @Inject constructor(
    getUserDetailUseCase: GetUserDetailUseCase,
    private val checkerDuplicateNameUseCase: CheckerDuplicateNameUseCase,
    private val updateUserDetailUseCase: UpdateUserDetailUseCase
) : ViewModel() {


    private val userDetail: StateFlow<UserDetail> = getUserDetailUseCase().map {
        when (it) {
            is DataState.Fail -> {
                UserDetail()
            }

            is DataState.Success -> {
                it.data
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = UserDetail()
    )


    val heightWeightState = HeightWeightState(userDetail.value, viewModelScope)
    val nameState = NameState(userDetail.value, checkerDuplicateNameUseCase, viewModelScope)
    val genderState = GenderState(userDetail.value)


    fun updateUserDetail(): () -> Unit = {
        viewModelScope.launch {
            updateUserDetailUseCase(
                userDetail = userDetail.value.copy(
                    name = nameState.nameText.value,
                    gender = genderState.gender.value,
                    height = heightWeightState.heightText.value.toFloat(),
                    weight = heightWeightState.weightText.value.toFloat(),
                )
            ).collect {
                when (it) {
                    is DataState.Fail -> {
                        updateUserDetailState.value =
                            UpdateUserDetailState.Fail(message = "프로필 사진 업데이트를 실패하였습니다.\n 잠시후 다시 시도해주세요.")
                    }

                    is DataState.Success -> {
                        updateUserDetailState.value = UpdateUserDetailState.Success
                    }
                }
            }
        }

    }

    internal val updateUserDetailState: MutableStateFlow<UpdateUserDetailState> =
        MutableStateFlow(UpdateUserDetailState.None)

    internal fun updateUpdateUserDetailState(): (UpdateUserDetailState) -> Unit = {
        updateUserDetailState.value = it
    }


}