package com.gradation.lift.network.service

import com.gradation.lift.network.common.APIResultWrapper
import com.gradation.lift.network.dto.routine.*

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import kotlinx.datetime.LocalDate
import retrofit2.http.Body

interface RoutineService {

    @GET("routine/routine-set/")
    suspend fun getRoutineSet(@Query("user_id") userId: String): APIResultWrapper<GetRoutineSetResponseDto>

    @POST("routine/routine-set/")
    suspend fun createRoutineSet(@Body createRoutineSetRequestDto: CreateRoutineSetRequestDto): APIResultWrapper<Boolean>

    @GET("routine/routine-set-by-date/")
    suspend fun getRoutineSetByDate(
        @Query("user_id") userId: String,
        @Query("date") date: LocalDate,
    ): APIResultWrapper<GetRoutineSetByDateResponseDto>

    @GET("routine/routine-set-by-date/")
    suspend fun getRoutineSetByRoutineSetId(
        @Query("user_id") userId: String,
        @Query("routine_set_id") routineSetId: Int,
    ): APIResultWrapper<GetRoutineSetByRoutineSetIdResponseDto>

    @GET("routine/routine")
    suspend fun getRoutine(@Query("user_id") userId: String): APIResultWrapper<GetRoutineResponseDto>

    @GET("routine/routine-by-date")
    suspend fun getRoutineByDate(
        @Query("user_id") userId: String,
        @Query("date") date: LocalDate,
    ): APIResultWrapper<GetRoutineByDateResponseDto>

    @GET("routine/routine-by-routine-set-id/")
    suspend fun getRoutineByRoutineSetId(
        @Query("user_id") userId: String,
        @Query("routine_set_id") routineSetId: Int,
    ): APIResultWrapper<GetRoutineByRoutineSetIdResponseDto>

    @GET("routine/routine-by-date-and-routine-set-id/")
    suspend fun getRoutineByDateAndRoutineSetId(
        @Query("user_id") userId: String,
        @Query("date") date: LocalDate,
        @Query("routine_set_id") routineSetId: Int,
    ): APIResultWrapper<GetRoutineByDateAndRoutineSetIdResponseDto>

    @GET("routine/routine-set-routine/")
    suspend fun getRoutineSetRoutine(@Query("user_id") userId: String): APIResultWrapper<GetRoutineSetRoutineResponseDto>

    @GET("routine/routine-set-routine-by-date/")
    suspend fun getRoutineSetRoutineByDate(
        @Query("user_id") userId: String,
        @Query("date") date: LocalDate,
    ): APIResultWrapper<GetRoutineSetRoutineByDateResponseDto>

}