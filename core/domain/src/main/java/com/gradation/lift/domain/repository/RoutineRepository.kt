package com.gradation.lift.domain.repository

import com.gradation.lift.domain.model.common.DataState
import com.gradation.lift.domain.model.routine.CreateRoutineSetRoutine
import com.gradation.lift.domain.model.routine.Routine
import com.gradation.lift.domain.model.routine.RoutineSet
import com.gradation.lift.domain.model.work.WorkCategory
import com.gradation.lift.domain.model.work.WorkPart
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface RoutineRepository {

    fun getRoutineSet(userId: String): Flow<DataState<List<RoutineSet>>>

    fun createRoutineSet(createRoutineSetRoutine: CreateRoutineSetRoutine): Flow<DataState<Boolean>>

    fun getRoutineSetByDate(userId: String, date: LocalDate): Flow<DataState<List<RoutineSet>>>

    fun getRoutineSetByRoutineSetId(userId: String, routineSetId: Int): Flow<DataState<RoutineSet>>

    fun getRoutine(userId: String): Flow<DataState<List<Routine>>>

    fun getRoutineByDate(userId: String, date: LocalDate, ): Flow<DataState<List<Routine>>>

    fun getRoutineByRoutineSetId(userId: String, routineSetId: Int): Flow<DataState<List<Routine>>>

    fun getRoutineByDateAndRoutineSetId(userId: String, date: LocalDate, routineSetId: Int): Flow<DataState<List<Routine>>>
}