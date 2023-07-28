package com.gradation.lift.database.model.history

import androidx.room.*
import com.gradation.lift.database.util.Constants.Entity.HISTORY_TABLE_NAME
import com.gradation.lift.database.util.LocalDateTimeTypeConverter
import com.gradation.lift.database.util.LocalTimeTypeConverter
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime

@Entity(
    tableName = HISTORY_TABLE_NAME
)
data class HistoryEntity(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "comment")
    val comment: String?,

    @ColumnInfo(name = "score")
    val score: Int,


    @TypeConverters(LocalTimeTypeConverter::class)
    @ColumnInfo(name = "rest_time")
    val restTime: LocalTime,

    @TypeConverters(LocalTimeTypeConverter::class)
    @ColumnInfo(name = "total_time")
    val totalTime: LocalTime,

    @TypeConverters(LocalDateTimeTypeConverter::class)
    @ColumnInfo(name = "history_time_stamp")
    val historyTimeStamp: LocalDateTime,
)




