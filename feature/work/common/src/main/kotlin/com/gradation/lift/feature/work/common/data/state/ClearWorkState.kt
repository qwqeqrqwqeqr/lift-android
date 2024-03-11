package com.gradation.lift.feature.work.common.data.state


/**
 * [ClearWorkState]
 * 운동 초기화에 사용하는 상태
 * @since 2024-03-11 20:44:32
 */
sealed interface ClearWorkState {

    data object Success : ClearWorkState

    data object None : ClearWorkState

}