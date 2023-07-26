package com.gradation.lift.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity


@Entity(tableName = "work_part")
data class WorkPartEntity(
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
)
