package com.gradation.lift.database.mapper

import com.gradation.lift.database.entity.workCategory.WorkCategoryEntity
import com.gradation.lift.database.entity.workCategory.WorkPartEntity
import com.gradation.lift.model.model.work.WorkCategory
import com.gradation.lift.model.model.work.WorkPart


fun WorkPart.toEntity() = WorkPartEntity(
    id, name
)


fun WorkCategory.toEntity() = WorkCategoryEntity(
    id = id,
    name = name,
    equipment = equipment,
    searchTag = searchTag,
    workPart = workPart,
    introduce = introduce,
    description = description,
    sequence = sequence.map { "${it.sequence}:${it.title}:${it.content}" },
    effect = effect.map { "${it.title}:${it.content}" },
    caution = caution
)