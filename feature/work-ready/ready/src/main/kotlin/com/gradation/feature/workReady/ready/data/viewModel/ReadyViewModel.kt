package com.gradation.feature.workReady.ready.data.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.feature.workReady.ready.data.state.WorkRoutineInfoState
import com.gradation.lift.domain.usecase.work.CreateWorkUseCase
import com.gradation.lift.domain.usecase.work.DeleteAllWorkUseCase
import com.gradation.lift.feature.workReady.common.model.WorkRoutine
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
    private val createWorkUseCase: CreateWorkUseCase,
    private val deleteAllWorkUseCase: DeleteAllWorkUseCase,
) : ViewModel() {

    internal val workRoutineInfoState = WorkRoutineInfoState()

    val createWork: (List<Int>, List<WorkRoutine>) -> Unit =
        { usedRoutineSetIdList, workRoutineList ->
            viewModelScope.launch {
                deleteAllWorkUseCase().collect()
                createWorkUseCase(
                        Work(
                            id = WORK_ID_KEY,
                            routine = workRoutineList.map { workRoutine ->
                                WorkRoutineModel(
                                    workId = WORK_ID_KEY,
                                    workCategoryId = workRoutine.workCategoryId,
                                    workCategoryName = workRoutine.workCategoryName,
                                    workPart = workRoutine.workPart,
                                    workSetList = workRoutine.workSetList.map { workSet ->
                                        WorkSet(
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