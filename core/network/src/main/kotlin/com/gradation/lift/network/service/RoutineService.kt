package com.gradation.lift.network.service

import com.gradation.lift.network.common.APIResultWrapper
import com.gradation.lift.network.dto.routine.*

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.PUT


/**
 * [RoutineService]
 * 루틴 및 루틴 세트와 관련한 서비스
 * 루틴 세트 : 루틴들을 모아둔 템플릿
 * 루틴 : 개별 운동에 대한 정보
 * @since 2023-08-28 22:31:37
 */
interface RoutineService {


    /**
     * [createRoutineSetRoutine]
     * 루틴세트 만들기
     * @since 2023-08-28 22:31:32
     */
    @POST("routine/routine-set-routine")
    suspend fun createRoutineSetRoutine(@Body createRoutineSetRoutineRequestDto: CreateRoutineSetRoutineRequestDto): Response<APIResultWrapper<CreateRoutineSetRoutineResponseDto>>

    /**
     * [updateRoutineSetRoutine]
     * 루틴세트 업데이트하기
     * @since 2023-09-06 16:12:37
     */
    @PUT("routine/routine-set-routine")
    suspend fun updateRoutineSetRoutine(@Body updateRoutineSetRoutineRequestDto: UpdateRoutineSetRoutineRequestDto): Response<APIResultWrapper<UpdateRoutineSetRoutineResponseDto>>

    /**
     * [updateRoutineSetCount]
     * 루틴세트의 사용횟수를 1 증가시킵니다.
     * @since 2023-10-13 11:35:46
     */
    @PUT("routine/routine-set/count")
    suspend fun updateRoutineSetCount(@Body updateRoutineSetCountRequestDto: UpdateRoutineSetCountRequestDto): Response<APIResultWrapper<UpdateRoutineSetCountResponseDto>>


    /**
     * [deleteRoutineSetRoutine]
     * 루틴세트 삭제하기
     * @since 2023-09-06 16:12:41
     */
    @DELETE("routine/routine-set-routine")
    suspend fun deleteRoutineSetRoutine(@Query("routine_set_id") routineSetId: Int): Response<APIResultWrapper<DeleteRoutineSetRoutineResponseDto>>


    /**
     * [getRoutine]
     * 사용자가 보유하고 있는 모든 루틴 불러오기
     * @since 2023-08-28 22:31:28
     */
    @GET("routine/routine")
    suspend fun getRoutine(): Response<APIResultWrapper<GetRoutineResponseDto>>

    /**
     * [getRoutineSetRoutine]
     * 사용자가 보유하고 있는 모든 루틴 세트 불러오기
     * @since 2023-08-28 22:31:23
     */
    @GET("routine/routine-set-routine")
    suspend fun getRoutineSetRoutine(): Response<APIResultWrapper<GetRoutineSetRoutineResponseDto>>

    /**
     * [getRoutineSetRoutineByWeekday]
     * 특정 요일에 대한 모든 루틴 세트 불러오기
     * @param weekday 3글자료 표기한 요일을 ,로 분류한 문자열 (ex. Mon,Tue,Wed,...)
     * @since 2023-10-13 11:21:09
     */
    @GET("routine/routine-set-routine/weekday")
    suspend fun getRoutineSetRoutineByWeekday(@Query("weekday") weekday: String): Response<APIResultWrapper<GetRoutineSetRoutineByWeekdayResponseDto>>


    /**
     * [getRoutineSetRoutineByLabel]
     * 특정 라벨에 대한 모든 루틴 세트 불러오기
     * @param label 3글자료 표기한 요일을 ,로 분류한 문자열 (ex. Mon,Tue,Wed,...)
     * @since 2023-10-13 11:21:09
     */
    @GET("routine/routine-set-routine/label")
    suspend fun getRoutineSetRoutineByLabel(@Query("label") label: String): Response<APIResultWrapper<GetRoutineSetRoutineByLabelResponseDto>>


    /**
     * [getRoutineSetRoutineByRoutineSetId]
     * 루틴세트 고유 아이디들과 일치하는 모든 루틴 세트 불러오기
     * @param routineSetIdList 루틴세트 고유 아이디 번호 리스트,  ','로 구분 하여 [String]으로 전달함
     * @since 2023-10-13 11:21:14
     */
    @GET("routine/routine-set-routine/routine-set-id")
    suspend fun getRoutineSetRoutineByRoutineSetId(@Query("routine_set_id_list") routineSetIdList: String): Response<APIResultWrapper<GetRoutineSetRoutineByRoutineSetIdResponseDto>>
}