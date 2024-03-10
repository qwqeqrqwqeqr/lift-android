package com.gradation.lift.feature.work.common.data.state

/**
 * [LoadWorkState]
 * 운동 불러오기 관련 상태
 * @since 2024-03-07 16:35:19
 */
sealed interface LoadWorkState {

    data object Success : LoadWorkState
    data object None : LoadWorkState
    data object Fail : LoadWorkState
}