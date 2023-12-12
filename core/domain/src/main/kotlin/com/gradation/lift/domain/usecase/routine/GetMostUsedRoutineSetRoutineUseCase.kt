package com.gradation.lift.domain.usecase.routine

import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.repository.RoutineRepository
import com.gradation.lift.model.model.routine.RoutineSetRoutine
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMostUsedRoutineSetRoutineUseCase @Inject constructor(
    private val routineRepository: RoutineRepository
) {
    /**
     * [GetMostUsedRoutineSetRoutineUseCase]
     * 가장 많이 사용한 루틴을 불러오기
     * [limit] 몇개의 루틴을 가져올지에 대한 파라미터 기본 값 5
     */
    operator fun invoke(limit: Int = 5): Flow<DataState<List<RoutineSetRoutine>>> = flow {
        routineRepository.getRoutineSetRoutine().collect {
            when (it) {
                is DataState.Fail -> emit(DataState.Fail(it.message))
                is DataState.Success -> {
                    emit(DataState.Success(it.data.sortedByDescending { it.count }.take(limit)))
                }
            }
        }

    }

}