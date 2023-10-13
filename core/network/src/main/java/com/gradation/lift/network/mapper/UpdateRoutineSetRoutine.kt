package com.gradation.lift.network.mapper

import com.gradation.lift.model.model.routine.UpdateRoutineSetRoutine
import com.gradation.lift.network.common.Constants
import com.gradation.lift.network.dto.routine.UpdateRoutineDto
import com.gradation.lift.network.dto.routine.UpdateRoutineSetRoutineRequestDto


fun UpdateRoutineSetRoutine.toDto(): UpdateRoutineSetRoutineRequestDto =
    UpdateRoutineSetRoutineRequestDto(
        id = this.id,
        name = this.name,
        description = this.description,
        weekday = this.weekday.joinToString(","),
        label = this.label.joinToString(","),
        picture = this.picture.replace(Constants.DEFAULT_S3_URL, ""),
        routine = this.routine.map { routine ->
            UpdateRoutineDto(
                id = routine.id,
                routineSetId = this.id,
                workCategory = routine.workCategory,
                workWeightList = routine.workSetList.map { it.weight },
                workRepetitionList = routine.workSetList.map { it.repetition }
            )
        }
    )