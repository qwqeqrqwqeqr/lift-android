package com.gradation.lift.database.model

import androidx.room.*
import com.gradation.lift.database.util.WorkSetListTypeConverter
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime

/**
 * [WorkEntity]
 * 진행중인 운동 가록하는 Entity
 */
@Entity(tableName = "work")

data class WorkEntity(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "comment")
    val comment: String?,

    @ColumnInfo(name = "int")
    val score: Int,

    @TypeConverters(WorkSetListTypeConverter::class)
    @ColumnInfo(name = "int")
    val restTime: LocalTime,

    @TypeConverters(WorkSetListTypeConverter::class)
    @ColumnInfo(name = "int")
    val totalTime: LocalTime,

    @Embedded(prefix = "work_routine_")
    val workRoutineEntity: List<WorkRoutineEntity>,
)


