package com.gradation.lift.domain.usecase.history

import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.repository.HistoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class DeleteHistoryUseCase @Inject constructor(
    private val historyRepository: HistoryRepository,
) {
    operator fun invoke(historyId: Int): Flow<DataState<Unit>> =
        historyRepository.deleteHistory(historyId = historyId)
}