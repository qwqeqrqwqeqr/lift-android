package com.gradation.lift.database.model

import androidx.room.*
import com.gradation.lift.database.util.WorkSetListTypeConverter
import com.gradation.lift.model.history.HistoryRoutine
import com.gradation.lift.model.work.WorkSet
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime


@Entity(tableName = "history")

data class HistoryEntity(

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

    @TypeConverters(WorkSetListTypeConverter::class)
    @ColumnInfo(name = "int")
    val historyTimeStamp: LocalDate,


    @Embedded(prefix = "history_routine_")
    val historyRoutine: List<HistoryRoutineEntity>,
)




