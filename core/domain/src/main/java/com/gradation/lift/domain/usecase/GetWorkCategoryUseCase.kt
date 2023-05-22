package com.gradation.lift.domain.usecase

import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.model.WorkPart
import com.gradation.lift.domain.repository.WorkRepository
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