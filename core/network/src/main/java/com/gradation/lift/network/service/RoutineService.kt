package com.gradation.lift.network.service

import com.gradation.lift.network.common.APIResultWrapper
import com.gradation.lift.network.dto.routine.*

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import kotlinx.datetime.LocalDate
import retrofit2.Response
import retrofit2.http.Body

interface RoutineService {


    @POST("routine/routine-set/")
    suspend fun createRoutineSet(@Body createRoutineSetRequestDto: CreateRoutineSetRequestDto): Response<APIResultWrapper<CreateRoutineSetResponseDto>>

    @GET("routine/routine/")
    suspend fun getRoutine(): Response<APIResultWrapper<GetRoutineResponseDto>>

    @GET("routine/routine-set-routine/")
    suspend fun getRoutineSetRoutine(): Response<APIResultWrapper<GetRoutineSetRoutineResponseDto>>

    @GET("routine/routine-set-routine-by-weekday/")
    suspend fun getRoutineSetRoutineByWeekday(@Query("weekday") weekday: String): Response<APIResultWrapper<GetRoutineSetRoutineByWeekdayResponseDto>>

    @GET("routine/routine-set-routine-by-routine-set-id/")
    suspend fun getRoutineSetRoutineByRoutineSetId(@Query("routine_set_id_list") routineSetIdList: String): Response<APIResultWrapper<GetRoutineSetRoutineByRoutineSetIdResponseDto>>
}