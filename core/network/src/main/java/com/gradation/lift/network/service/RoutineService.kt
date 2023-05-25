package com.gradation.lift.network.service

import com.gradation.lift.network.common.APIResultWrapper
import com.gradation.lift.network.dto.routine.*
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RoutineService {

    @GET("routine/routine-set/")
    suspend fun getRoutineSet(@Query("user_id") userId: String): APIResultWrapper<List<GetRoutineSetResponseDto>>

    @POST("routine/routine-set/")
    suspend fun createRoutineSet(createRoutineSetRequestDto: CreateRoutineSetRequestDto): APIResultWrapper<Boolean>

    @GET("routine/routine-set-by-date/")
    suspend fun getRoutineSetByDate(
        @Query("user_id") userId: String,
        @Query("date") date: String,
    ): APIResultWrapper<List<GetRoutineSetByDateResponseDto>>

    @GET("routine/routine")
    suspend fun getRoutine(@Query("user_id") userId: String): APIResultWrapper<List<GetRoutineResponseDto>>

    @GET("routine/routine-by-date/")
    suspend fun getRoutineByDate(
        @Query("user_id") userId: String,
        @Query("date") date: String,
    ): APIResultWrapper<List<GetRoutineByDateResponseDto>>


    //TODO  getRoutineByRoutineSet 추가할 것
    //TODO  getRoutineSet 을 진행할 시, 동반되는 routine도 같이 부를것
}