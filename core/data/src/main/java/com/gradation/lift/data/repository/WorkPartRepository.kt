package com.gradation.lift.data.repository

import com.gradation.lift.common.di.DispatcherProvider
import com.gradation.lift.common.model.DataState
import com.gradation.lift.model.data.WorkPart
import com.gradation.lift.network.common.NetworkResult
import com.gradation.lift.network.common.networkResultHandler
import com.gradation.lift.network.dto.work.GetWorkCategoryByWorkPartResponseDto
import com.gradation.lift.network.dto.work.GetWorkCategoryResponseDto
import com.gradation.lift.network.service.WorkService
import kotlinx.coroutines.flow.*
import java.io.IOException
import javax.inject.Inject


interface WorkRepository {
    suspend fun getWorkPart(): Flow<DataState<List<WorkPart>>>
    suspend fun getWorkCategory(): List<GetWorkCategoryResponseDto>
    suspend fun getWorkCategoryByWorkPart(workpart: Int): List<GetWorkCategoryByWorkPartResponseDto>
}


class DefaultWorkPartRepository @Inject constructor(
    private val workService: WorkService,
    private val dispatcherProvider: DispatcherProvider
) : WorkRepository {
    override suspend fun getWorkPart(): Flow<DataState<List<WorkPart>>> {

        val result = flow {
            when (val response = networkResultHandler { workService.getWorkPart() }) {
                is NetworkResult.Success -> emit(DataState.Success(response))
                is NetworkResult.Fail -> emit(DataState.Success(response))

                is NetworkResult.Error -> emit(DataState.Error(response.exception))
            }
        }
            .onStart { emit(DataState.Loading) }
            .flowOn(dispatcherProvider.io)

//            .retryWhen { cause, attempt ->
//                if (cause is IOException && attempt < 2L) {    // retry on IOException
//                    emit(RetryWrapperValue(e))
//                    delay(1000)                // delay for one second before retry
//                    true
//                } else {                       // do not retry otherwise
//                    false
//                }
//            }



    }

    override suspend fun getWorkCategory(): List<GetWorkCategoryResponseDto> {
        TODO("Not yet implemented")
    }

    override suspend fun getWorkCategoryByWorkPart(workpart: Int): List<GetWorkCategoryByWorkPartResponseDto> {
        TODO("Not yet implemented")
    }


}