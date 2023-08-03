package com.gradation.lift.network.mapper

import com.gradation.lift.model.routine.CreateRoutineSetRoutine
import com.gradation.lift.network.BuildConfig.LIFT_S3_URL
import com.gradation.lift.network.common.Constants
import com.gradation.lift.network.dto.routine.CreateRoutineDto
import com.gradation.lift.network.dto.routine.CreateRoutineSetRequestDto


fun CreateRoutineSetRoutine.toDto() : CreateRoutineSetRequestDto =
    CreateRoutineSetRequestDto(
        name = this.name,
        description = this.description,
        weekday = this.weekday.map { it.getWeekdayValue() },
        picture = this.picture.replace(Constants.DEFAULT_S3_URL,""),
        routine = this.routine.map { routine ->
            CreateRoutineDto(
                workCategory = routine.workCategory,
                workWeightList = routine.workSetList.map { it.weight },
                workRepetitionList = routine.workSetList.map { it.repetition }
            )
        }
    )