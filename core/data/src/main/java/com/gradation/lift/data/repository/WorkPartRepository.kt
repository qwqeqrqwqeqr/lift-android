package com.gradation.lift.data.repository

import com.gradation.lift.common.di.DispatcherProvider
import com.gradation.lift.model.data.WorkPart
import com.gradation.lift.network.dto.work.GetWorkCategoryByWorkPartResponseDto
import com.gradation.lift.network.dto.work.GetWorkCategoryResponseDto
import com.gradation.lift.network.service.WorkService
import javax.inject.Inject


interface WorkRepository {
    suspend fun getWorkPart(): List<WorkPart>?
    suspend fun getWorkCategory(): List<GetWorkCategoryResponseDto>
    suspend fun getWorkCategoryByWorkPart(workpart: Int): List<GetWorkCategoryByWorkPartResponseDto>
}


class DefaultWorkPartRepository @Inject constructor(
    private val workService: WorkService,
    private val dispatcherProvider: DispatcherProvider
) : WorkRepository {
    override suspend fun getWorkPart(): List<WorkPart>? {
        TODO("Not yet implemented")
    }

    override suspend fun getWorkCategory(): List<GetWorkCategoryResponseDto> {
        TODO("Not yet implemented")
    }

    override suspend fun getWorkCategoryByWorkPart(workpart: Int): List<GetWorkCategoryByWorkPartResponseDto> {
        TODO("Not yet implemented")
    }


}