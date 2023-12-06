package com.gradation.lift.create_routine.routine.data.model

import androidx.compose.runtime.MutableState

/**
 * [IndexWorkSet]
 * 운동 횟수와 무게와 해당 운동의 순번을 저장하고 있는 모델
 * @property index 해당 필드를 통해 리스트 내에서 해당 모델에 접근
 * @property focused 해당 필드가 수정 작업에 들어가고 있는지에 대한 여부
 * @since 2023-10-12 11:01:27
 */
data class IndexWorkSet(
    var index: Int,
    var weight: MutableState<String>,
    var repetition: MutableState<String>,
    var focused: Boolean = false
)




