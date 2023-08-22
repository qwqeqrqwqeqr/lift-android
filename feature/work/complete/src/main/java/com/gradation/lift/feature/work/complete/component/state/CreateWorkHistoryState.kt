package com.gradation.lift.feature.work.complete.component.state

/**
 * [CreateWorkHistoryState]
 * 운동 기록 생성 상태
 * 루틴이 성공적으로 만들어 질 경우 [Success] 상태를 가짐
 * 초기값은 [None]으로 설정되어 있음
 * [Fail] 상태는 에러메시지를 보유하고 있음 메시지는 Snackbar를 통해 호출됨
 * @since 2023-08-22 17:38:07
 */
sealed interface CreateWorkHistoryState{
    object Success : CreateWorkHistoryState
    object None : CreateWorkHistoryState
    data class Fail(val message:String) : CreateWorkHistoryState
}