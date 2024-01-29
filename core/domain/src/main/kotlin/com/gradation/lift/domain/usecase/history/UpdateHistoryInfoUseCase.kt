package com.gradation.lift.domain.usecase.history

import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.repository.HistoryRepository
import com.gradation.lift.model.model.history.History
import com.gradation.lift.model.model.history.UpdateHistoryInfo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateHistoryInfoUseCase @Inject constructor(
    private val historyRepository: HistoryRepository,
) {
    operator fun invoke(updateHistoryInfo: UpdateHistoryInfo): Flow<DataState<Boolean>> =
        historyRepository.updateHistoryInfo(updateHistoryInfo)
}