package com.gradation.lift.domain.repository

import com.gradation.lift.common.model.DataState
import com.gradation.lift.model.routine.CreateRoutineSetRoutine
import com.gradation.lift.model.routine.Routine
import com.gradation.lift.model.routine.RoutineSet
import com.gradation.lift.model.routine.RoutineSetRoutine
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDate

interface RoutineRepository {

    fun getRoutineSet(): Flow<DataState<List<RoutineSet>>>

    fun createRoutineSet(createRoutineSetRoutine: CreateRoutineSetRoutine): Flow<DataState<Boolean>>

    fun getRoutineSetByDate(date: LocalDate): Flow<DataState<List<RoutineSet>>>

    fun getRoutineSetByRoutineSetId(routineSetId: Int): Flow<DataState<RoutineSet>>

    fun getRoutine(): Flow<DataState<List<Routine>>>

    fun getRoutineByDate(date: LocalDate): Flow<DataState<List<Routine>>>

    fun getRoutineByRoutineSetId(routineSetId: Int): Flow<DataState<List<Routine>>>

    fun getRoutineByDateAndRoutineSetId(date: LocalDate, routineSetId: Int): Flow<DataState<List<Routine>>>

    fun getRoutineSetRoutine(): Flow<DataState<List<RoutineSetRoutine>>>

    fun getRoutineSetRoutineByDate(date: LocalDate): Flow<DataState<List<RoutineSetRoutine>>>
}