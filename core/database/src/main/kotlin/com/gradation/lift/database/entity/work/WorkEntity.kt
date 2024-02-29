package com.gradation.lift.database.entity.work

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.gradation.lift.database.util.Constants.Entity.WORK_TABLE_NAME
import com.gradation.lift.database.util.IntListTypeConverter
import com.gradation.lift.database.util.LocalTimeTypeConverter
import com.gradation.lift.model.model.work.Work
import kotlinx.datetime.LocalTime
import java.util.UUID

@Entity(
    tableName = WORK_TABLE_NAME
)
data class WorkEntity(
    @ColumnInfo(name = "id")
    @PrimaryKey
    val id: UUID,

    @TypeConverters(LocalTimeTypeConverter::class)
    @ColumnInfo(name = "work_time")
    val workTime: LocalTime,

    @TypeConverters(LocalTimeTypeConverter::class)
    @ColumnInfo(name = "rest_time")
    val restTime: LocalTime,

    @TypeConverters(LocalTimeTypeConverter::class)
    @ColumnInfo(name = "total_time")
    val totalTime: LocalTime,

    @TypeConverters(IntListTypeConverter::class)
    @ColumnInfo(name = "used_routine_set_id_list")
    val usedRoutineSetIdList: List<Int>,

    ) {
    fun toDomain(): Work = Work(
        id = this.id,
        workTime = this.workTime,
        restTime = this.restTime,
        totalTime = this.totalTime,
        usedRoutineSetIdList = this.usedRoutineSetIdList,
        routine = emptyList(),
    )
}


