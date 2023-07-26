package com.gradation.lift.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gradation.lift.database.util.Constants.Entity.ROUTINE_SET_PICTURE_TABLE_NAME


@Entity(tableName = ROUTINE_SET_PICTURE_TABLE_NAME)
data class RoutineSetPictureEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "category")
    val category: String,
    @ColumnInfo(name = "url")
    val url: String,
)



