package com.gradation.lift.database.mapper

import com.gradation.lift.database.entity.work_category.WorkCategoryEntity
import com.gradation.lift.database.entity.work_category.WorkPartEntity
import com.gradation.lift.model.model.work.WorkCategory
import com.gradation.lift.model.model.work.WorkPart


fun WorkPart.toEntity() = WorkPartEntity(
    id, name
)


fun WorkCategory.toEntity() = WorkCategoryEntity(
    id, name, workPart.toEntity(), introduce, description
)