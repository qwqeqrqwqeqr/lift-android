package com.gradation.lift.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gradation.lift.database.util.Constants.Entity.WORK_PART_TABLE_NAME


@Entity(tableName = WORK_PART_TABLE_NAME)
data class WorkPartEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
)
