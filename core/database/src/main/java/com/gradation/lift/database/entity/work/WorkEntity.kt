package com.gradation.lift.database.entity.work

import androidx.room.*
import com.gradation.lift.database.util.Constants.Entity.WORK_TABLE_NAME
import com.gradation.lift.database.util.LocalTimeTypeConverter
import com.gradation.lift.model.model.work.Work
import kotlinx.datetime.LocalTime

@Entity(
    tableName = WORK_TABLE_NAME
)
data class WorkEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @TypeConverters(LocalTimeTypeConverter::class)
    @ColumnInfo(name = "work_time")
    val workTime: LocalTime,

    @TypeConverters(LocalTimeTypeConverter::class)
    @ColumnInfo(name = "rest_time")
    val restTime: LocalTime,

    @TypeConverters(LocalTimeTypeConverter::class)
    @ColumnInfo(name = "total_time")
    val totalTime: LocalTime,
) {
    fun toDomain(): Work = Work(
        id = this.id,
        workTime = this.workTime,
        restTime = this.restTime,
        totalTime = this.totalTime,
        routine = emptyList()
    )
}


