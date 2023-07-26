package com.gradation.lift.database.model

import androidx.room.*


@Entity(tableName = "work_category")
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
)




