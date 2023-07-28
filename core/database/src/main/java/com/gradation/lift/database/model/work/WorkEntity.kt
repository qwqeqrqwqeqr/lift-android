package com.gradation.lift.database.model.work

import androidx.room.*
import com.gradation.lift.database.util.Constants.Entity.WORK_TABLE_NAME
import com.gradation.lift.database.util.LocalTimeTypeConverter
import kotlinx.datetime.LocalTime

@Entity(
    tableName = WORK_TABLE_NAME
)
data class WorkEntity(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @TypeConverters(LocalTimeTypeConverter::class)
    @ColumnInfo(name = "rest_time")
    val restTime: LocalTime,

    @TypeConverters(LocalTimeTypeConverter::class)
    @ColumnInfo(name = "total_time")
    val totalTime: LocalTime,

    )


