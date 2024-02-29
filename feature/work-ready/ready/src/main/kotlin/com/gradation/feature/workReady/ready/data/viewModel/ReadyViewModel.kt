package com.gradation.feature.workReady.ready.data.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.feature.workReady.ready.data.state.WorkRoutineInfoState
import com.gradation.lift.domain.usecase.work.CreateWorkUseCase
import com.gradation.lift.domain.usecase.work.DeleteAllWorkUseCase
import com.gradation.lift.feature.workReady.common.model.WorkRoutine
import com.gradation.lift.model.model.work.Work
import com.gradation.lift.model.model.work.WorkSet
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.UUID
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
                    UUID.randomUUID().let {
                        Work(
                            id = it,
                            routine = workRoutineList.map { workRoutine ->
                                WorkRoutineModel(
                                    workId = it,
                                    workCategory = workRoutine.workCategory,
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
                    }
                ).collect()
            }
        }
}