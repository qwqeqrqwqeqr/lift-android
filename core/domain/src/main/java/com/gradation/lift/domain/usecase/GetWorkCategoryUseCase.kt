package com.gradation.lift.domain.usecase

import com.gradation.lift.common.model.DataState
import com.gradation.lift.data.repository.WorkRepository
import com.gradation.lift.model.data.WorkPart
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetWorkCategoryUseCase @Inject constructor(
    private val workRepository: WorkRepository
) {

    operator fun invoke(): Flow<DataState<List<WorkPart>>> =flow{
        workRepository.getWorkCategory()
    }
}