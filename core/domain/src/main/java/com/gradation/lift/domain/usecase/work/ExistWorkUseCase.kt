package com.gradation.lift.domain.usecase.work

import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.repository.WorkRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ExistWorkUseCase @Inject constructor(
    private val workRepository: WorkRepository,
) {
    operator fun invoke(): Flow<DataState<Boolean>> =
        workRepository.existWork()
}

