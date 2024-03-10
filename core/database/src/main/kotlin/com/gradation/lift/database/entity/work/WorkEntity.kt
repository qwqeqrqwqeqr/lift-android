package com.gradation.lift.database.entity.work

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.gradation.lift.database.util.CheckedWorkSetInfoListTypeConverter
import com.gradation.lift.database.util.Constants.Entity.WORK_TABLE_NAME
import com.gradation.lift.database.util.IntListTypeConverter
import com.gradation.lift.database.util.LocalTimeTypeConverter
import com.gradation.lift.model.model.work.CheckedWorkSetInfo
import com.gradation.lift.model.model.work.Work
import kotlinx.datetime.LocalTime

@Entity(
    tableName = WORK_TABLE_NAME
)
data class WorkEntity(

    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Long,

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

    @TypeConverters(CheckedWorkSetInfoListTypeConverter::class)
    @ColumnInfo(name = "checked_work_set_info_list")
    val checkedWorkSetInfoList: List<CheckedWorkSetInfo> = emptyList(),
) {
    fun toDomain(): Work = Work(
        id = id,
        workTime = workTime,
        restTime = restTime,
        totalTime = totalTime,
        usedRoutineSetIdList = usedRoutineSetIdList,
        checkedWorkSetInfoList = checkedWorkSetInfoList,
        routine = emptyList(),
    )
}


