package com.gradation.lift.database.entity.work_category

import androidx.room.*
import com.gradation.lift.database.util.Constants.Entity.WORK_CATEGORY_TABLE_NAME
import com.gradation.lift.model.work.WorkCategory
import com.gradation.lift.model.work.WorkPart

@Entity(tableName = WORK_CATEGORY_TABLE_NAME)
data class WorkCategoryEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "name")
    var name: String,

    @Embedded(prefix = "work_part_")
    val workPart: WorkPartEntity,

    @ColumnInfo(name = "introduce")
    val introduce: String,

    @ColumnInfo(name = "description")
    val description: String,
) {
    fun toDomain() = WorkCategory(
        id = id,
        name = name,
        workPart = workPart.toDomain(),
        introduce = introduce,
        description = description
    )
}





