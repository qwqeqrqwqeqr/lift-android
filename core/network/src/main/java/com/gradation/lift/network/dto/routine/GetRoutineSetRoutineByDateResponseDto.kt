package com.gradation.lift.network.dto.routine

import com.gradation.lift.domain.model.common.DAY_TYPE
import com.gradation.lift.domain.model.common.RepeatIntervalType
import com.gradation.lift.domain.model.common.WEEK_DAY_TYPE
import com.gradation.lift.domain.model.common.toWeekDay
import com.gradation.lift.domain.model.routine.Routine
import com.gradation.lift.domain.model.routine.RoutineSet
import com.gradation.lift.domain.model.routine.RoutineSetRoutine
import com.gradation.lift.domain.model.work.WorkCategory
import com.gradation.lift.domain.model.work.WorkPart
import com.gradation.lift.domain.model.work.WorkSet
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetRoutineSetRoutineByDateResponseDto(
    @SerialName("routine_set_routine")
    val routineSetRoutine: List<RoutineSetRoutineDto>
){
    fun toRoutineSetRoutine(): List<RoutineSetRoutine> = this.routineSetRoutine.groupBy{ it.id }.map {
        RoutineSetRoutine(
            id = it.value.first().id,
            shortDescription = it.value.first().shortDescription,
            longDescription = it.value.first().longDescription,
            repeatIntervalType = when (it.value.first().repeatType) {
                WEEK_DAY_TYPE ->  RepeatIntervalType.WeekDayType(weekday = toWeekDay(it.value.first().repeatInterval))
                DAY_TYPE -> RepeatIntervalType.DayType(interval = it.value.first().repeatInterval)
                else -> null
            }!!,
            routine = it.value.map { routine ->
                Routine(
                    id = routine.routineId,
                    routineSetId = routine.id,
                    workCategory = WorkCategory(
                        id = routine.workCategory.id,
                        name = routine.workCategory.name,
                        workpart = WorkPart(
                            id= routine.workCategory.workpart.id,
                            name =routine.workCategory.workpart.name
                        ),
                        shortDescription = routine.workCategory.shortDescription,
                        longDescription = routine.workCategory.longDescription
                    ),
                    workSetList = routine.workWeightList.zip(routine.workRepetitionList).map { workSet ->
                        WorkSet(weight = workSet.first, repetition = workSet.second)
                    },
                    maxWeight = routine.maxWeight,
                    minWeight = routine.minWeight,
                    totalWeight = routine.totalWeight,
                    maxRepetition = routine.maxRepetition,
                    minRepetition = routine.minRepetition,
                    totalRepetition = routine.totalRepetition
                )
            }
        )
    }


}