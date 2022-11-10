package com.gradation.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.gradation.model.data.Week

@Entity(tableName = "routine_sets")
data class RoutineSetEntity (
    val shortDescription: String,
    val longDescription: String,


    val week: Week,
    @ColumnInfo(name = "using_count")
    val usingCount : Int,
)