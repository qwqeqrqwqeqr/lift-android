package com.gradation.lift.database.mapper

import com.gradation.lift.database.entity.routine.RoutineEntity
import com.gradation.lift.database.entity.routine.RoutineSetRoutineEntity
import com.gradation.lift.model.routine.Routine
import com.gradation.lift.model.routine.RoutineSetRoutine


fun RoutineSetRoutine.toEntity() = RoutineSetRoutineEntity(
    id, name, description, weekday, picture
)

fun Routine.toEntity() = RoutineEntity(
    id, routineSetId, workCategory.toEntity(), workSetList
)