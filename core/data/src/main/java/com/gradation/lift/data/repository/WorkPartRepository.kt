package com.gradation.lift.data.repository

import android.util.Log
import com.gradation.lift.common.di.DispatcherProvider
import com.gradation.lift.common.model.DataState
import com.gradation.lift.model.data.WorkCategory
import com.gradation.lift.model.data.WorkPart
import com.gradation.lift.network.dto.work.GetWorkCategoryByWorkPartResponseDto
import com.gradation.lift.network.dto.work.GetWorkCategoryResponseDto
import com.gradation.lift.network.service.WorkService
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import java.io.IOException
import javax.inject.Inject


interface WorkRepository {
    suspend fun getWorkPart(): Flow<DataState<List<WorkPart>>>
    suspend fun getWorkCategory(): Flow<DataState<List<WorkCategory>>>
    suspend fun getWorkCategoryByWorkPart(workpart: Int):  Flow<DataState<List<WorkCategory>>>
}


class DefaultWorkPartRepository @Inject constructor(
    private val workService: WorkService,
    private val dispatcherProvider: DispatcherProvider
) : WorkRepository {
    override suspend fun getWorkPart(): Flow<DataState<List<WorkPart>>> = flow {
        when (val response = networkResultHandler { workService.getWorkPart() }) {
            is NetworkResult.Success -> {
                //TODO mapNotNull 처리 이렇게 로직 만들어도 문제 없는지 고민 해봐야 할 듯
                emit(DataState.Success(response.data?.mapNotNull { it.toWorkPart() }))
            }
            is NetworkResult.Fail -> emit(DataState.Error("${response.code} : ${response.message}"))
            is NetworkResult.Error -> emit(DataState.Error(response.exception.toString()))
        }
    }.onStart { emit(DataState.Loading) }
        .flowOn(dispatcherProvider.io)
        .retryWhen { cause, attempt ->
            if (cause is IOException && attempt < 3L) {
                emit(DataState.Loading)
                delay(1000)
                true
            } else {
                false
            }
        }


    override suspend fun getWorkCategory(): Flow<DataState<List<WorkCategory>>> = flow {
        when (val response = networkResultHandler { workService.getWorkCategory() }) {
            is NetworkResult.Success -> {
                emit(DataState.Success(response.data?.map{ it.toWorkCategory() }))
            }
            is NetworkResult.Fail -> emit(DataState.Error("${response.code} : ${response.message}"))
            is NetworkResult.Error -> emit(DataState.Error(response.exception.toString()))
        }
    }.onStart { emit(DataState.Loading) }
        .flowOn(dispatcherProvider.io)
        .retryWhen { cause, attempt ->
            if (cause is IOException && attempt < 3L) {
                emit(DataState.Loading)
                delay(1000)
                true
            } else {
                false
            }
    }

    override suspend fun getWorkCategoryByWorkPart(workpart: Int):  Flow<DataState<List<WorkCategory>>> = flow {
        when (val response = networkResultHandler { workService.getWorkCategoryByWorkPart(workpart) }) {
            is NetworkResult.Success -> {
                emit(DataState.Success(response.data?.map{ it.toWorkCategory() }))
            }
            is NetworkResult.Fail -> emit(DataState.Error("${response.code} : ${response.message}"))
            is NetworkResult.Error -> emit(DataState.Error(response.exception.toString()))
        }
    }.onStart { emit(DataState.Loading) }
        .flowOn(dispatcherProvider.io)
        .retryWhen { cause, attempt ->
            if (cause is IOException && attempt < 3L) {
                emit(DataState.Loading)
                delay(1000)
                true
            } else {
                false
            }
        }

}