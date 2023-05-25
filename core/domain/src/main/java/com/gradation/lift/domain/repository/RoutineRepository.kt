package com.gradation.lift.domain.repository

import com.gradation.lift.domain.model.common.DataState
import com.gradation.lift.domain.model.routine.Routine
import com.gradation.lift.domain.model.routine.RoutineSet
import com.gradation.lift.domain.model.work.WorkCategory
import com.gradation.lift.domain.model.work.WorkPart
import kotlinx.coroutines.flow.Flow

interface RoutineRepository {

    fun getRoutineSet(userId: String): Flow<DataState<List<RoutineSet>>>
    fun getRoutineSetByDate(userId: String, date: String): Flow<DataState<List<RoutineSet>>>
    fun getRoutine(userId: String): Flow<DataState<List<Routine>>>
    fun getRoutineByDate(userId: String): Flow<DataState<List<Routine>>>
    fun getRoutineByRoutineSet(userId: String, date: String): Flow<DataState<List<Routine>>>


    fun createRoutineSet(routineSet: RoutineSet): Flow<DataState<Boolean>>
}