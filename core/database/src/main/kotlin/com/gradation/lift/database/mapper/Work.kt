package com.gradation.lift.database.mapper

import com.gradation.lift.database.entity.work.WorkEntity
import com.gradation.lift.database.entity.work.WorkRoutineEntity
import com.gradation.lift.model.model.work.Work
import com.gradation.lift.model.model.work.WorkRoutine

fun Work.toEntity() = WorkEntity(
    id, workTime, restTime, totalTime, usedRoutineSetIdList, checkedWorkSetInfoList
)

fun WorkRoutine.toEntity() = WorkRoutineEntity(
    workId = workId,
    workRoutineId = workRoutineId,
    workCategoryId = workCategoryId,
    workCategoryName = workCategoryName,
    workPart = workPart,
    workSetList = workSetList
)