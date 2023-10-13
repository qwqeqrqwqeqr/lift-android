package com.gradation.lift.database.mapper

import com.gradation.lift.database.entity.routine.RoutineEntity
import com.gradation.lift.database.entity.routine.RoutineSetRoutineEntity
import com.gradation.lift.model.model.routine.Routine
import com.gradation.lift.model.model.routine.RoutineSetRoutine


fun RoutineSetRoutine.toEntity() = RoutineSetRoutineEntity(
    id = id, name = name, description = description, weekday = weekday[0], picture
)

//TODO 모델 변경 -> 반드시 수정할 것  2023-10-13 12:03:28
fun Routine.toEntity() = RoutineEntity(
    id, routineSetId, workCategory.toEntity(), workSetList
)