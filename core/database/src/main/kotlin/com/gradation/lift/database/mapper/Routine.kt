package com.gradation.lift.database.mapper

import com.gradation.lift.database.entity.routine.RoutineEntity
import com.gradation.lift.database.entity.routine.RoutineSetRoutineEntity
import com.gradation.lift.model.model.routine.Routine
import com.gradation.lift.model.model.routine.RoutineSetRoutine
import com.gradation.lift.model.model.routine.getId


fun RoutineSetRoutine.toEntity() = RoutineSetRoutineEntity(
    id,
    name,
    description,
    picture,
    weekday.map { it.getWeekdayValue() },
    label.map { it.getId() },
    count
)

fun Routine.toEntity() = RoutineEntity(
    id, routineSetId, workCategoryId, workCategoryName, workPart, workSetList
)