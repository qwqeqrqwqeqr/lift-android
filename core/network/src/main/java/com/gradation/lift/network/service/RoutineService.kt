package com.gradation.lift.network.service

import com.gradation.lift.network.common.APIResultWrapper
import com.gradation.lift.network.dto.routine.CreateRoutineSetRequestDto
import com.gradation.lift.network.dto.routine.GetRoutineSetResponseDto
import com.gradation.lift.network.dto.work.GetWorkPartResponseDto
import retrofit2.http.GET
import retrofit2.http.POST

interface RoutineService {


    @GET("routine/routine-set/")
    suspend fun getRoutineSet(): APIResultWrapper<List<GetRoutineSetResponseDto>>

    @POST("routine/routine-set/")
    suspend fun createRoutineSet(createRoutineSetRequestDto: CreateRoutineSetRequestDto): APIResultWrapper<Boolean>


    @GET("routine/routine-set-by-date/")
    suspend fun getRoutineSetByDate(): APIResultWrapper<List<GetWorkPartResponseDto>>

    @GET("routine/routine")
    suspend fun getRoutine(): APIResultWrapper<List<GetWorkPartResponseDto>>

    @GET("routine/routine-by-date/")
    suspend fun getRoutineByDate(): APIResultWrapper<List<GetWorkPartResponseDto>>
}