package com.gradation.lift.domain.usecase.history

import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.repository.HistoryRepository
import com.gradation.lift.model.model.history.CreateHistory
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreateHistoryUseCase @Inject constructor(
    private val historyRepository: HistoryRepository,
) {
    operator fun invoke(createHistory: CreateHistory): Flow<DataState<Boolean>> =
        historyRepository.createHistory(createHistory = createHistory)
}