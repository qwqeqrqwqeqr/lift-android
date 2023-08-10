package com.gradation.lift.network.service

import com.gradation.lift.network.common.APIResultWrapper
import com.gradation.lift.network.dto.routine.*

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.Response
import retrofit2.http.Body


/**
 * [RoutineService]
 * 루틴 및 루틴 세트와 관련한 서비스
 * 루틴 세트 : 루틴들을 모아둔 템플릿
 * 루틴 : 개별 운동에 대한 정보
 */
interface RoutineService {


    /**
     * [createRoutineSet]
     * 루틴세트 만들기
     */
    @POST("routine/routine-set/")
    suspend fun createRoutineSet(@Body createRoutineSetRequestDto: CreateRoutineSetRequestDto): Response<APIResultWrapper<CreateRoutineSetResponseDto>>


    /**
     * [getRoutine]
     * 사용자가 보유하고 있는 모든 루틴 불러오기
     */
    @GET("routine/routine/")
    suspend fun getRoutine(): Response<APIResultWrapper<GetRoutineResponseDto>>

    /**
     * [getRoutineSetRoutine]
     * 사용자가 보유하고 있는 모든 루틴 세트 불러오기
     */
    @GET("routine/routine-set-routine/")
    suspend fun getRoutineSetRoutine(): Response<APIResultWrapper<GetRoutineSetRoutineResponseDto>>

    /**
     * [getRoutineSetRoutineByWeekday]
     * 특정 요일에 대한 모든 루틴 세트 불러오기
     * @param weekday 3글자료 표기한 요일 (ex. Mon,Tue,Wed,...)
     */
    @GET("routine/routine-set-routine-by-weekday/")
    suspend fun getRoutineSetRoutineByWeekday(@Query("weekday") weekday: String): Response<APIResultWrapper<GetRoutineSetRoutineByWeekdayResponseDto>>

    /**
     * [getRoutineSetRoutineByRoutineSetId]
     * 루틴세트 고유 아이디들과 일치하는 모든 루틴 세트 불러오기
     * @param 루틴세트 고유 아이디 번호 리스트,  ','로 구분 하여 [String]으로 전달함
     */
    @GET("routine/routine-set-routine-by-routine-set-id/")
    suspend fun getRoutineSetRoutineByRoutineSetId(@Query("routine_set_id_list") routineSetIdList: String): Response<APIResultWrapper<GetRoutineSetRoutineByRoutineSetIdResponseDto>>
}