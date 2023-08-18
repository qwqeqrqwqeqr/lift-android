package com.gradation.lift.feature.register_detail.height_weight.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.utils.Validator
import com.gradation.lift.common.utils.heightValidator
import com.gradation.lift.common.utils.weightValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

/**
 * [RegisterDetailHeightWeightViewModel]
 * @property weightText 사용자의 몸무게 Float 타입을 사용할 경우 텍스트 필드에서 소수점을 처리할 때 문제가 있어 String 타입 사용
 * @property heightText 사용자의 키
 * 키,몸무게에서 Float 타입을 사용할 경우 텍스트 필드에서 소수점을 처리할 때 문제가 있어 String 타입 사용
 *
 * @property weightValidator 몸무게 유효성 검증 (몸무게로 허용 되는 범위)
 * @property heightValidator 키 유효성 검증 (키로 허용 되는 범위)
 * @property navigateCondition 다음 화면으로 넘어가는게 적절한지 판단하는 조건
 * @since 2023-08-18 12:41:00
 */
@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class RegisterDetailHeightWeightViewModel @Inject constructor(

) : ViewModel() {

    var weightText: MutableStateFlow<String> = MutableStateFlow("")
    var heightText: MutableStateFlow<String> = MutableStateFlow("")

    var weightValidator: StateFlow<Validator> =
        weightText.mapLatest {
            it.toFloatOrNull()?.let { weight ->
                if (weightValidator(weight)) {
                    Validator(true, "")
                } else {
                    Validator(false, "정확한 정보를 입력해주세요.")
                }
            } ?: Validator(false, "")
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = Validator()
        )

    var heightValidator: StateFlow<Validator> =
        heightText.mapLatest {
            it.toFloatOrNull()?.let { height ->
                if (heightValidator(height)) {
                    Validator(true, "")
                } else {
                    Validator(false, "정확한 정보를 입력해주세요.")
                }
            } ?: Validator(false, "")
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = Validator()
        )

    internal fun updateWeightText(): (String) -> Unit = { it ->
        weightText.value = it
    }

    internal fun updateHeightText(): (String) -> Unit = { it ->
        heightText.value = it
    }

    var navigateCondition: StateFlow<Boolean> =
        combine(weightValidator, heightValidator) { weight, height ->
            weight.status && height.status
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false
        )


}
