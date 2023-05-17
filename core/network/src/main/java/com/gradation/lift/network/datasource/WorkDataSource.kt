package com.gradation.lift.network.datasource

import com.gradation.lift.model.data.WorkPart
import com.gradation.lift.network.dto.work.GetWorkCategoryByWorkPartResponseDto
import com.gradation.lift.network.dto.work.GetWorkCategoryResponseDto
import com.gradation.lift.network.service.WorkService
import retrofit2.HttpException
import javax.inject.Inject

interface WorkDataSource {
    suspend fun  getWorkPart(): List<WorkPart>
    suspend fun getWorkCategory(): List<GetWorkCategoryResponseDto>
    suspend fun getWorkCategoryByWorkPart(workpart: Int): List<GetWorkCategoryByWorkPartResponseDto>
}


class WorkDataSourceImpl @Inject constructor(private val workService: WorkService):WorkDataSource{


    override suspend fun getWorkPart(): List<WorkPart> {

        if(workService.getWorkPart().isSuccessful)



        return emptyList()
    }

    override suspend fun getWorkCategory(): List<GetWorkCategoryResponseDto> {
        TODO("Not yet implemented")
    }

    override suspend fun getWorkCategoryByWorkPart(workpart: Int): List<GetWorkCategoryByWorkPartResponseDto> {
        TODO("Not yet implemented")
    }
}



