package com.gradation.lift.domain.usecase.workCategory

import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.repository.WorkCategoryRepository
import com.gradation.lift.model.model.work.WorkCategory
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRecommendWorkCategoryUseCase @Inject constructor(
    private val workCategoryRepository: WorkCategoryRepository,
) {
    operator fun invoke(): Flow<DataState<List<WorkCategory>>> =
        workCategoryRepository.getRecommendWorkCategory()
}