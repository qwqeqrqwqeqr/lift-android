package com.gradation.lift.domain.usecase.work

import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.repository.WorkRepository
import com.gradation.lift.model.model.work.Work
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchWorkUseCase @Inject constructor(
    private val workRepository: WorkRepository,
) {
    operator fun invoke(work: Work): Flow<DataState<Unit>> =
        workRepository.fetchWork(work)
}