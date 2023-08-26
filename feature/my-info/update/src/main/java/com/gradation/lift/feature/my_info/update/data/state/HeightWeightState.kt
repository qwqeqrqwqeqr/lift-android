package com.gradation.lift.feature.my_info.update.data.state

import com.gradation.lift.common.utils.Validator
import com.gradation.lift.common.utils.heightValidator
import com.gradation.lift.common.utils.weightValidator
import com.gradation.lift.model.model.user.UserDetail
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn

/**
 * [HeightWeightState]
 * @property weightText 사용자의 몸무게 Float 타입을 사용할 경우 텍스트 필드에서 소수점을 처리할 때 문제가 있어 String 타입 사용
 * @property heightText 사용자의 키 Float 타입을 사용할 경우 텍스트 필드에서 소수점을 처리할 때 문제가 있어 String 타입 사용
 * @property weightValidator 몸무게 유효성 검증 (몸무게로 허용 되는 범위)
 * @property heightValidator 키 유효성 검증 (키로 허용 되는 범위)
 * @since 2023-08-26 13:29:04
 */
class HeightWeightState(
    userDetail: UserDetail,
    private val viewModelScope: CoroutineScope
) {
    var heightText: MutableStateFlow<String> = MutableStateFlow(userDetail.height.toString())
    var weightText: MutableStateFlow<String> = MutableStateFlow(userDetail.weight.toString())

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


    internal fun updateHeightText(): (String) -> Unit = { it ->
        heightText.value = it
    }

    internal fun updateWeightText(): (String) -> Unit = { it ->
        weightText.value = it
    }

}