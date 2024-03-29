package com.gradation.lift.database.entity.history

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.gradation.lift.database.util.Constants.Entity.HISTORY_TABLE_NAME
import com.gradation.lift.database.util.LocalDateTimeTypeConverter
import com.gradation.lift.database.util.LocalTimeTypeConverter
import com.gradation.lift.model.model.history.History
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
    val score: Int?,

    @ColumnInfo(name = "progress")
    val progress: Int,

    @TypeConverters(LocalTimeTypeConverter::class)
    @ColumnInfo(name = "work_time")
    val workTime: LocalTime,

    @TypeConverters(LocalTimeTypeConverter::class)
    @ColumnInfo(name = "rest_time")
    val restTime: LocalTime,

    @TypeConverters(LocalTimeTypeConverter::class)
    @ColumnInfo(name = "total_time")
    val totalTime: LocalTime,

    @TypeConverters(LocalDateTimeTypeConverter::class)
    @ColumnInfo(name = "history_time_stamp")
    val historyTimeStamp: LocalDateTime,
) {
    fun toDomain() = History(
        historyId = id,
        comment = comment,
        score = score,
        progress = progress,
        workTime = workTime,
        restTime = restTime,
        totalTime = totalTime,
        historyTimeStamp = historyTimeStamp,
        historyRoutine = emptyList()
    )
}




