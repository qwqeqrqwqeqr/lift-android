package com.gradation.lift.database.mapper

import com.gradation.lift.database.entity.work.WorkEntity
import com.gradation.lift.database.entity.work.WorkRoutineEntity
import com.gradation.lift.model.work.Work
import com.gradation.lift.model.work.WorkRoutine

fun Work.toEntity() = WorkEntity(
    id, restTime, totalTime
)

fun WorkRoutine.toEntity() = WorkRoutineEntity(
    id, workId, workCategory.toEntity(), workSetList
)