package com.gradation.lift.network.dto.routine

import com.gradation.lift.model.model.date.toWeekDay
import com.gradation.lift.model.model.routine.Routine
import com.gradation.lift.model.model.routine.RoutineSetRoutine
import com.gradation.lift.model.model.routine.toLabel
import com.gradation.lift.model.model.work.WorkCategory
import com.gradation.lift.model.model.work.WorkPart
import com.gradation.lift.model.model.work.WorkSet
import com.gradation.lift.network.common.Constants
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class GetRoutineSetRoutineByRoutineSetIdResponseDto(
    @Json(name = "routine_set_routine")
    val routineSetRoutine: List<RoutineSetRoutineDto>
){
    fun toDomain(): List<RoutineSetRoutine> =   this.routineSetRoutine.groupBy { it.routineSetDto.routineSetId }.map {
        RoutineSetRoutine(
            id = it.value.first().routineSetDto.routineSetId,
            name = it.value.first().routineSetDto.name,
            description = it.value.first().routineSetDto.description,
            weekday = it.value.first().routineSetDto.weekday.split(",")
                .map { weekday -> weekday.toWeekDay() },
            picture = Constants.DEFAULT_S3_URL + it.value.first().routineSetDto.picture,
            label = it.value.first().routineSetDto.label.split(",")
                .map { label -> label.toLabel() },
            count = it.value.first().routineSetDto.count,
            routine = it.value.map { routine ->
                Routine(
                    id = routine.routineDto.routineId,
                    routineSetId = routine.routineDto.routineSetId,
                    workCategory = WorkCategory(
                        id = routine.routineDto.workCategory.id,
                        name = routine.routineDto.workCategory.name,
                        workPart = WorkPart(
                            id = routine.routineDto.workCategory.workPart.id,
                            name = routine.routineDto.workCategory.workPart.name
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
