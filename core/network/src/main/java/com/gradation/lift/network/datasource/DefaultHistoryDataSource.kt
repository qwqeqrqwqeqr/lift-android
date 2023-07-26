package com.gradation.lift.network.datasource

import com.gradation.lift.model.history.CreateHistory
import com.gradation.lift.model.history.History
import com.gradation.lift.network.common.APIResult
import com.gradation.lift.network.dto.history.CreateHistoryRequestDto
import com.gradation.lift.network.dto.history.CreateHistoryRoutineDto
import com.gradation.lift.network.handler.NetworkResultHandler
import com.gradation.lift.network.service.HistoryService
import com.gradation.lift.network.service.RoutineService
import com.squareup.moshi.Json
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.datetime.LocalTime
import javax.inject.Inject

class DefaultHistoryDataSource @Inject constructor(
    private val historyService: HistoryService,
    private val networkResultHandler: NetworkResultHandler,
) : HistoryDataSource {
    override suspend fun getHistory(): Flow<APIResult<List<History>>> = flow {
        networkResultHandler {
            historyService.getHistory()
        }.collect { result ->
            when (result) {
                is APIResult.Fail -> emit(APIResult.Fail(result.message))

                is APIResult.Success -> emit(APIResult.Success(result.data.toHistory()))
            }
        }
    }

    override suspend fun getHistoryByHistoryId(historyIdList: Set<Int>): Flow<APIResult<List<History>>> =
        flow {
            networkResultHandler {
                historyService.getHistoryByHistoryId(historyIdList.joinToString(","))
            }.collect { result ->
                when (result) {
                    is APIResult.Fail -> emit(APIResult.Fail(result.message))

                    is APIResult.Success -> emit(APIResult.Success(result.data.toHistory()))
                }
            }
        }

    override suspend fun createHistory(createHistory: CreateHistory): Flow<APIResult<Boolean>> =
        flow {
            networkResultHandler {
                historyService.createHistory(
                    CreateHistoryRequestDto(
                        comment = createHistory.comment,
                        score = createHistory.score,
                        restTime = createHistory.restTime,
                        totalTime = createHistory.totalTime,
                        historyTimeStamp  = createHistory.historyTimeStamp,
                        historyRoutine = createHistory.historyRoutine.map { historyRoutine ->
                            CreateHistoryRoutineDto(
                                workCategory = historyRoutine.workCategoryId,
                                workWeightList = historyRoutine.workSet.map { it.weight },
                                workRepetitionList = historyRoutine.workSet.map { it.repetition }
                            )
                        }
                    )
                )
            }.collect { result ->
                when (result) {
                    is APIResult.Fail -> emit(APIResult.Fail(result.message))

                    is APIResult.Success -> emit(APIResult.Success(result.data.result))
                }
            }
        }

    override suspend fun deleteHistory(historyId: Int): Flow<APIResult<Boolean>> = flow {
        networkResultHandler {
            historyService.deleteHistory(historyId)
        }.collect { result ->
            when (result) {
                is APIResult.Fail -> emit(APIResult.Fail(result.message))

                is APIResult.Success -> emit(APIResult.Success(result.data.result))
            }
        }
    }

}