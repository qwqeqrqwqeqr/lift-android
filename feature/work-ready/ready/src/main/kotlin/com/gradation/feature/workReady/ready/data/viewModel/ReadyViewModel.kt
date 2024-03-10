package com.gradation.feature.workReady.ready.data.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.feature.workReady.ready.data.state.WorkRoutineInfoState
import com.gradation.lift.domain.usecase.work.FetchWorkUseCase
import com.gradation.lift.feature.workReady.common.model.WorkReadyRoutine
import com.gradation.lift.model.model.work.Work
import com.gradation.lift.model.model.work.WorkSet
import com.gradation.lift.model.utils.Constants.WORK_ID_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.gradation.lift.model.model.work.WorkRoutine as WorkRoutineModel

@HiltViewModel
class ReadyViewModel @Inject constructor(
    private val fetchWorkUseCase: FetchWorkUseCase,
) : ViewModel() {

    internal val workRoutineInfoState = WorkRoutineInfoState()

    val createWork: (List<Int>, List<WorkReadyRoutine>) -> Unit =
        { usedRoutineSetIdList, workRoutineList ->
            viewModelScope.launch {
                fetchWorkUseCase(
                    Work(
                        id = WORK_ID_KEY,
                        routine = workRoutineList.map { workRoutine ->
                            WorkRoutineModel(
                                workId = WORK_ID_KEY,
                                workRoutineId = workRoutine.id,
                                workCategoryId = workRoutine.workCategoryId,
                                workCategoryName = workRoutine.workCategoryName,
                                workPart = workRoutine.workPart,
                                workSetList = workRoutine.workSetList.mapIndexed { workSetId, workSet ->
                                    WorkSet(
                                        workSetId = workSetId,
                                        weight = workSet.weight.toFloat(),
                                        repetition = workSet.repetition.toInt()
                                    )
                                }
                            )
                        },
                        usedRoutineSetIdList = usedRoutineSetIdList
                    )
                ).collect()
            }
        }
}