package com.gradation.lift.feature.myInfo.updateInfo.data

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.common.utils.Validator
import com.gradation.lift.common.utils.decimalNumberValidator
import com.gradation.lift.common.utils.heightValidator
import com.gradation.lift.common.utils.weightValidator
import com.gradation.lift.domain.usecase.user.UpdateUserDetailInfoUseCase
import com.gradation.lift.feature.myInfo.updateInfo.data.state.UpdateUserDetailInfoState
import com.gradation.lift.model.model.user.Gender
import com.gradation.lift.model.model.user.UserDetailInfo
import com.gradation.lift.model.model.user.toGender
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * [UpdateInfoViewModel]
 * @property isChangedInfo 수정사항이 존재하는지 판단하는 필드
 * @since 2024-01-15 16:05:50
 */
@HiltViewModel
class UpdateInfoViewModel @Inject constructor(
    private val updateUserDetailInfoUseCase: UpdateUserDetailInfoUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val defaultGender: Gender =
        savedStateHandle.get<String>(SavedStateHandleKey.MyInfo.USER_GENDER_KEY).toGender()
    private val defaultHeight: String =
        savedStateHandle.get<Float>(SavedStateHandleKey.MyInfo.USER_HEIGHT_KEY)?.toString() ?: ""
    private val defaultWeight: String =
        savedStateHandle.get<Float>(SavedStateHandleKey.MyInfo.USER_WEIGHT_KEY)?.toString() ?: ""


    internal var heightText: MutableStateFlow<String> = MutableStateFlow(defaultHeight)
    internal var weightText: MutableStateFlow<String> = MutableStateFlow(defaultWeight)
    internal var gender: MutableStateFlow<Gender> = MutableStateFlow(defaultGender)


    var heightValidator: StateFlow<Validator> =
        heightText.mapLatest { height ->
            if (height.isEmpty()) {
                Validator(true, "")
            } else {
                if (decimalNumberValidator(height)) {
                    if (heightValidator(height.toFloat()))
                        Validator(true, "")
                    else Validator(false, "정확한 정보를 입력해주세요.")
                } else {
                    Validator(false, "정확한 정보를 입력해주세요.")
                }
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = Validator(true, "")
        )
    var weightValidator: StateFlow<Validator> =
        weightText.mapLatest { weight ->
            if (weight.isEmpty()) {
                Validator(true, "")
            } else {
                if (decimalNumberValidator(weight)) {
                    if (weightValidator(weight.toFloat()))
                        Validator(true, "")
                    else Validator(false, "정확한 정보를 입력해주세요.")
                } else {
                    Validator(false, "정확한 정보를 입력해주세요.")
                }
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = Validator(true, "")
        )

    val isChangedInfo: StateFlow<Boolean> =
        combine(gender, heightText, weightText) { gender, heightText, weightText ->
            gender != defaultGender || heightText != defaultHeight || weightText != defaultWeight
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false
        )


    internal val updateUserDetailInfoState: MutableStateFlow<UpdateUserDetailInfoState> =
        MutableStateFlow(UpdateUserDetailInfoState.None)

    internal fun updateUpdateUserDetailInfoState(): (UpdateUserDetailInfoState) -> Unit =
        { updateUserDetailInfoState.value = it }


    fun updateUserDetailInfo(): () -> Unit = {
        viewModelScope.launch {
            updateUserDetailInfoUseCase(
                UserDetailInfo(
                    gender = gender.value,
                    height = heightText.value.toFloat(),
                    weight = weightText.value.toFloat(),
                )
            ).collect {
                when (it) {
                    is DataState.Fail -> {
                        updateUserDetailInfoState.value =
                            UpdateUserDetailInfoState.Fail(message = "프로필 업데이트를 실패하였습니다.\n잠시후 다시 시도해주세요.")
                    }

                    is DataState.Success -> {
                        updateUserDetailInfoState.value = UpdateUserDetailInfoState.Success
                    }
                }
            }
        }
    }


    internal fun updateHeightText(): (String) -> Unit = { it -> heightText.value = it }
    internal fun updateWeightText(): (String) -> Unit = { it -> weightText.value = it }
    internal fun updateGender(): (Gender) -> Unit = { gender.value = it }
}