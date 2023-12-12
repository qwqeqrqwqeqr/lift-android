package com.gradation.lift.domain.usecase.history

import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.repository.HistoryRepository
import com.gradation.lift.model.model.history.History
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetHistoryByHistoryIdUseCase @Inject constructor(
    private val historyRepository: HistoryRepository,
) {
    operator fun invoke(historyIdList: Set<Int>): Flow<DataState<List<History>>> =
        historyRepository.getHistoryByHistoryId(historyIdList = historyIdList)
}