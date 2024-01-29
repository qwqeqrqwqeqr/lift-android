package com.gradation.lift.network.service

import com.gradation.lift.network.common.APIResultWrapper
import com.gradation.lift.network.dto.history.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Query


/**
 * [HistoryService]
 * 운동 기록과 관련한 서비스
 * @since 2023-08-28 22:33:09
 */
interface HistoryService {

    /**
     *  [getHistory]
     *  운동 기록 불러오기
     *  @since 2023-08-28 22:33:04
     */
    @GET("history/history")
    suspend fun getHistory(): Response<APIResultWrapper<GetHistoryResponseDto>>


    /**
     * [getHistoryByHistoryId]
     * 운동 기록 고유 아이디 번호에 따른 운동 기록 불러오기
     * @param historyIdList
     * 운동기록 고유 아이디 번호 리스트,  ','로 구분 하여 [String]으로 전달함
     * @since 2023-08-28 22:32:59
     */
    @GET("history/history-by-history-id")
    suspend fun getHistoryByHistoryId(@Query("history_id_list") historyIdList: String): Response<APIResultWrapper<GetHistoryByHistoryIdResponseDto>>


    /**
     * [createHistory]
     * 운동 기록 생성하기
     * @since 2023-08-28 22:32:54
     */
    @POST("history/history")
    suspend fun createHistory(@Body createHistoryRequestDto: CreateHistoryRequestDto): Response<APIResultWrapper<CreateHistoryResponseDto>>


    /**
     * [deleteHistory]
     * 운동 기록 삭제
     * @param historyId 운동기록 고유 아이디를 지정하여 운동을 삭제한다.
     * @since 2023-08-28 22:32:48
     */
    @DELETE("history/history")
    suspend fun deleteHistory(@Query("history_id") historyId: Int): Response<APIResultWrapper<DeleteHistoryResponseDto>>


    /**
     * [updateHistoryInfo]
     * 운동 기록 업데이트
     * @param updateHistoryInfoRequestDto 운동 기록의 아아디와 변경할 메모와 점수
     * @since 2024-01-29 20:08:24
     */
    @PATCH("history/history-info")
    suspend fun updateHistoryInfo(@Body updateHistoryInfoRequestDto: UpdateHistoryInfoRequestDto): Response<APIResultWrapper<UpdateHistoryInfoResponseDto>>
}


