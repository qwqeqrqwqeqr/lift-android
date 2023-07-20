package com.gradation.lift.network.service

import com.gradation.lift.network.common.APIResultWrapper
import com.gradation.lift.network.dto.history.*
import com.gradation.lift.network.dto.work.GetWorkCategoryByWorkPartResponseDto
import com.gradation.lift.network.dto.work.GetWorkCategoryResponseDto
import com.gradation.lift.network.dto.work.GetWorkPartResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface HistoryService {
    @GET("history/history/")
    suspend fun getHistory(): Response<APIResultWrapper<GetHistoryResponseDto>>

    @GET("history/history-by-history-id/")
    suspend fun getHistoryByHistoryId(@Query("history_id_list") historyIdList: String): Response<APIResultWrapper<GetHistoryByHistoryIdResponseDto>>

    @POST("history/history/")
    suspend fun createHistory(@Body createHistoryRequestDto: CreateHistoryRequestDto): Response<APIResultWrapper<CreateHistoryResponseDto>>

    @DELETE("history/history/")
    suspend fun deleteHistory(@Query("history_id") historyId: Int): Response<APIResultWrapper<DeleteHistoryResponseDto>>
}


