package com.gradation.lift.domain.usecase.work

import android.util.Log
import com.gradation.lift.domain.model.common.DataState
import com.gradation.lift.domain.model.WorkPart
import com.gradation.lift.domain.repository.WorkRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

class GetWorkCategoryUseCase @Inject constructor(
    private val workRepository: WorkRepository
) {
    operator fun invoke(): Flow<DataState<List<WorkPart>>> =
        workRepository.getWorkPart()
}