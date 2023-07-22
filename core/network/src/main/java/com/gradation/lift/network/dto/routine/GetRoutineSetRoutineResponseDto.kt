package com.gradation.lift.network.dto.routine

import com.gradation.lift.model.common.toWeekDay
import com.gradation.lift.model.routine.Routine
import com.gradation.lift.model.routine.RoutineSetRoutine
import com.gradation.lift.model.work.WorkCategory
import com.gradation.lift.model.work.WorkPart
import com.gradation.lift.model.work.WorkSet
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class GetRoutineSetRoutineResponseDto(
    @Json(name = "routine_set_routine")
    val routineSetRoutine: List<RoutineSetRoutineDto>
){
    fun toRoutineSetRoutine(): List<RoutineSetRoutine> = this.routineSetRoutine.groupBy{ it.routineSetDto.routineSetId }.map {
        RoutineSetRoutine(
            id = it.value.first().routineSetDto.routineSetId,
            name = it.value.first().routineSetDto.name,
            description = it.value.first().routineSetDto.description,
            weekday = it.value.first().routineSetDto.weekday.toWeekDay(),
            picture = it.value.first().routineSetDto.picture,
            routine = it.value.map { routine ->
                Routine(
                    id = routine.routineDto.routineId,
                    routineSetId = routine.routineDto.routineSetId,
                    workCategory = WorkCategory(
                        id = routine.routineDto.workCategory.id,
                        name = routine.routineDto.workCategory.name,
                        workpart = WorkPart(
                            id = routine.routineDto.workCategory.workpart.id,
                            name = routine.routineDto.workCategory.workpart.name
                        ),
                        introduce = routine.routineDto.workCategory.introduce,
                        description = routine.routineDto.workCategory.description
                    ),
                    workSetList = routine.routineDto.workWeightList.zip(routine.routineDto.workRepetitionList)
                        .map { workSet ->
                            WorkSet(
                                weight = workSet.first,
                                repetition = workSet.second
                            )
                        }
                )
            }
        )
    }
}
