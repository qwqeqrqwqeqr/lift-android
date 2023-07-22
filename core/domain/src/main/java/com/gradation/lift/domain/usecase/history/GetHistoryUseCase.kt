package com.gradation.lift.domain.usecase.history

import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.repository.HistoryRepository
import com.gradation.lift.model.history.History
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetHistoryUseCase  @Inject constructor(
    private val historyRepository: HistoryRepository
){
    operator fun invoke(): Flow<DataState<List<History>>> =
        historyRepository.getHistory()
}