package com.gradation.lift.network.dto.routine


import com.gradation.lift.model.model.date.Weekday
import com.gradation.lift.model.model.date.toWeekDay
import com.gradation.lift.model.model.routine.Routine
import com.gradation.lift.model.model.routine.RoutineSetRoutine
import com.gradation.lift.model.model.routine.toLabel
import com.gradation.lift.model.model.work.WorkSet
import com.gradation.lift.network.common.Constants
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetRoutineSetRoutineByRoutineSetIdResponseDto(
    @SerialName("routine_set_routine")
    val routineSetRoutine: List<RoutineSetRoutineDto>
){
    fun toDomain(): List<RoutineSetRoutine> =
        this.routineSetRoutine.groupBy { it.routineSetDto.routineSetId }.map { routineSetGroup ->
            RoutineSetRoutine(
                id = routineSetGroup.value.first().routineSetDto.routineSetId,
                name = routineSetGroup.value.first().routineSetDto.name,
                description = routineSetGroup.value.first().routineSetDto.description,
                weekday = routineSetGroup.value.first().routineSetDto.weekday.split(",")
                    .map { weekday -> weekday.toWeekDay() }.filterNot { it is Weekday.None }.sortedBy { it.getWeekdayNumber() }.toSet(),
                picture = Constants.DEFAULT_S3_URL + routineSetGroup.value.first().routineSetDto.picture,
                label = routineSetGroup.value.first().routineSetDto.label.trim()
                    .takeIf { it.isNotEmpty() }
                    ?.split(",")
                    ?.map { label -> label.toLabel() }
                    ?.toSet() ?: emptySet(),
                count = routineSetGroup.value.first().routineSetDto.count,
                routine = routineSetGroup.value.map { routine ->
                    Routine(
                        id = routine.routineDto.routineId,
                        routineSetId = routine.routineDto.routineSetId,
                        workCategoryId = routine.routineDto.workCategoryId,
                        workCategoryName = routine.routineDto.workCategoryName,
                        workPart = routine.routineDto.workPart,
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
