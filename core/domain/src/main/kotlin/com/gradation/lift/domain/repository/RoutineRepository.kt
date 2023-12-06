package com.gradation.lift.domain.repository

import com.gradation.lift.common.model.DataState
import com.gradation.lift.model.model.date.Weekday
import com.gradation.lift.model.model.routine.CreateRoutineSetRoutine
import com.gradation.lift.model.model.routine.Label
import com.gradation.lift.model.model.routine.Routine
import com.gradation.lift.model.model.routine.RoutineSetRoutine
import com.gradation.lift.model.model.routine.UpdateRoutineSetCount
import com.gradation.lift.model.model.routine.UpdateRoutineSetRoutine
import kotlinx.coroutines.flow.Flow

/**
 * [RoutineRepository]
 * 사용자의 루틴들을 관리하는 저장소
 * @since 2023-08-28 19:55:42
 */
interface RoutineRepository {


    /**
     * [createRoutineSetRoutine]
     * 루틴 세트 생성
     * @since 2023-08-28 19:55:50
     */
    fun createRoutineSetRoutine(createRoutineSetRoutine: CreateRoutineSetRoutine): Flow<DataState<Unit>>


    /**
     * [updateRoutineSetRoutine]
     * 루틴 세트 업데이트
     * @since 2023-09-06 16:45:57
     */
    fun updateRoutineSetRoutine(updateRoutineSetRoutine: UpdateRoutineSetRoutine): Flow<DataState<Unit>>

    /**
     * [updateRoutineSetCount]
     * 루틴 세트의 사용횟수 1 증가
     * @since 2023-10-13 11:46:09
     */
    fun updateRoutineSetCount(updateRoutineSetCount: UpdateRoutineSetCount): Flow<DataState<Unit>>

    /**
     * [deleteRoutineSetRoutine]
     * 루틴 세트 삭제
     * @since 2023-09-06 16:46:09
     */
    fun deleteRoutineSetRoutine(routineSetId: Int): Flow<DataState<Unit>>

    /**
     * [getRoutine]
     * 사용자의 모든 루틴 불러오기
     * @since 2023-08-28 19:56:11
     */
    fun getRoutine(): Flow<DataState<List<Routine>>>

    /**
     * [getRoutineSetRoutine]
     * 사용자의 모든 루틴 세트 불러오기
     * 해당 서비스는 루틴이 포함된 루틴 세트의 목록들을 불러옴
     * @since 2023-08-28 19:57:12
     */
    fun getRoutineSetRoutine(): Flow<DataState<List<RoutineSetRoutine>>>

    /**
     * [getRoutineSetRoutineByWeekday]
     * 해당 요일들에 해당하는 사용자의 루틴 세트 불러오기
     * 요일 리스트를 Set로 받은 후, string으로 변환하여 API를 요청함
     * @since 2023-10-13 11:30:00
     */
    fun getRoutineSetRoutineByWeekday(weekday: Set<Weekday>): Flow<DataState<List<RoutineSetRoutine>>>

    /**
     * [getRoutineSetRoutineByLabel]
     * 해당 요일들에 해당하는 사용자의 루틴 세트 불러오기
     * 라벨 리스트를 Set로 받은 후, string으로 변환하여 API를 요청함
     * @since 2023-10-13 11:30:53
     */
    fun getRoutineSetRoutineByLabel(label: Set<Label>): Flow<DataState<List<RoutineSetRoutine>>>

    /**
     * [getRoutineSetRoutineByRoutineSetId]
     * 루틴 세트 번호(아이디)와 일치하는 루틴세트 불러오기
     * 루틴 세트 번호 목럭을 Set로 받은 후, string으로 변환하여 API를 요청함
     * 2023-08-28 19:58:32
     */
    fun getRoutineSetRoutineByRoutineSetId(routineSetIdList: Set<Int>): Flow<DataState<List<RoutineSetRoutine>>>


}