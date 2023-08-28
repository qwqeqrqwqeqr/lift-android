package com.gradation.lift.domain.repository

import com.gradation.lift.common.model.DataState
import com.gradation.lift.model.model.history.CreateHistory
import com.gradation.lift.model.model.history.History
import kotlinx.coroutines.flow.Flow

/**
 * [HistoryRepository]
 * 사용자의 운동 기록을 관리하는 저장소
 * @since 2023-08-28 17:47:30
 */
interface HistoryRepository {

    /**
     * [getHistory]
     * 사용자의 운동기록 불러오기
     * @since 2023-08-28 17:53:09
     */
    fun getHistory(): Flow<DataState<List<History>>>

    /**
     * [getHistoryByHistoryId]
     * 히스토리 아이디에 따른 사용자의 운동기록 불러오기
     * @param historyIdList set 형태로 아이디들을 받고, string 으로 변환하여 서버에 넘김
     * @since 2023-08-28 18:26:30
     */
    fun getHistoryByHistoryId(historyIdList: Set<Int>): Flow<DataState<List<History>>>

    /**
     * [createHistory]
     * 운동기록 생성
     * @since 2023-08-28 18:26:57
     */
    fun createHistory(createHistory: CreateHistory): Flow<DataState<Unit>>

    /**
     * [deleteHistory]
     * history id 를 통해 운동기록 삭제
     * @since 2023-08-28 18:27:31
     */
    fun deleteHistory(historyId: Int): Flow<DataState<Unit>>
}