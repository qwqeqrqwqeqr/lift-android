package com.gradation.lift.domain.usecase.workPart

import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.repository.WorkPartRepository
import com.gradation.lift.model.model.work.WorkPart
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWorkPartUseCase @Inject constructor(
    private val workPartRepository: WorkPartRepository,
) {
    operator fun invoke(): Flow<DataState<List<WorkPart>>> =
        workPartRepository.getWorkPart()
}