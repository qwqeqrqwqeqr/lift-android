package com.gradation.lift.feature.work.completedetail.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.date.GetNowUseCase
import com.gradation.lift.domain.usecase.history.CreateHistoryUseCase
import com.gradation.lift.domain.usecase.routine.UpdateRoutineSetCountUseCase
import com.gradation.lift.feature.work.common.data.model.WorkRestTime
import com.gradation.lift.feature.work.completedetail.data.state.CreateWorkHistoryState
import com.gradation.lift.feature.work.completedetail.data.state.HistoryInfoState
import com.gradation.lift.model.model.history.CreateHistory
import com.gradation.lift.model.model.history.CreateHistoryRoutine
import com.gradation.lift.model.model.routine.UpdateRoutineSetCount
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDateTime
import javax.inject.Inject


@HiltViewModel
internal class CompleteDetailViewModel @Inject constructor(
    private val createHistoryUseCase: CreateHistoryUseCase,
    private val updateRoutineSetCountUseCase: UpdateRoutineSetCountUseCase,
    getNowUseCase: GetNowUseCase
): ViewModel(){

    val currentTime: LocalDateTime = getNowUseCase()

    val historyInfoState = HistoryInfoState(viewModelScope = viewModelScope)

    val createWorkHistoryState: MutableStateFlow<CreateWorkHistoryState> =
        MutableStateFlow(CreateWorkHistoryState.None)

    val updateCreateWorkHistoryState: (CreateWorkHistoryState) -> Unit =
        { createWorkHistoryState.value = it }


    val createHistory: (Int,WorkRestTime, List<CreateHistoryRoutine>,List<Int>) -> Unit =
        { progress, workRestTime, historyRoutineList,usedRoutineSetIdList ->
            viewModelScope.launch {
                createHistoryUseCase(
                    CreateHistory(
                        comment = historyInfoState.comment.value,
                        score = historyInfoState.score.value,
                        progress = progress,
                        workTime = workRestTime.workTime,
                        restTime = workRestTime.restTime,
                        totalTime = workRestTime.totalTime,
                        historyTimeStamp = getNowUseCase(),
                        historyRoutine = historyRoutineList
                    )
                ).collect {
                    when (it) {
                        is DataState.Fail -> createWorkHistoryState.value =
                            CreateWorkHistoryState.Fail(message = it.message)

                        is DataState.Success ->
                        {
                            updateRoutineSetCountUseCase(UpdateRoutineSetCount(usedRoutineSetIdList)).collect()
                            createWorkHistoryState.value = CreateWorkHistoryState.Success
                        }
                    }
                }
            }
        }
}