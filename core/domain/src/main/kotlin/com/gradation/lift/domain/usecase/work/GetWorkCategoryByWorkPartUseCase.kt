package com.gradation.lift.domain.usecase.work

import com.gradation.lift.model.model.work.WorkCategory
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.repository.WorkRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWorkCategoryByWorkPartUseCase @Inject constructor(
    private val workRepository: WorkRepository,
) {
    operator fun invoke(workPart: String): Flow<DataState<List<WorkCategory>>> =
        workRepository.getWorkCategoryByWorkPart(workPart)
}