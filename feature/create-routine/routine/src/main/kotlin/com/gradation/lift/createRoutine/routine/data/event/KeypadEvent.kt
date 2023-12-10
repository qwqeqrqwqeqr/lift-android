package com.gradation.lift.createRoutine.routine.data.event

import com.gradation.lift.createRoutine.routine.data.model.WorkSet
import com.gradation.lift.createRoutine.routine.data.state.KeypadWorkSetState


/**
 * [KeypadEvent]
 * [Clear] 값 지우기
 * [AppendNumber] 숫자 추가
 * [AppendPoint] 소수점 추가
 * [PlusNumber] 숫자 더하기
 * [MinusNumber] 숫자 빼기
 * [Done] 작성완료 (검사)
 * [UpdateState] 키패드 상태 값 변경
 * [Init] 초기화 (값 세팅)
 * @since 2023-12-08 11:28:56
 */
internal sealed interface KeypadEvent {
    data object Clear : KeypadEvent
    data class AppendNumber(val number:String) : KeypadEvent
    data class AppendPoint(val point:String) : KeypadEvent
    data class PlusNumber(val number:String) : KeypadEvent
    data class MinusNumber(val number:String) : KeypadEvent
    data object Done : KeypadEvent
    data class UpdateState(val state: KeypadWorkSetState): KeypadEvent

    data class Init(val index:Int, val workSet: WorkSet): KeypadEvent
}