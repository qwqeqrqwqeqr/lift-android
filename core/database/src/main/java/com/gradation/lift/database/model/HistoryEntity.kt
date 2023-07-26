package com.gradation.lift.database.model

import androidx.room.*
import com.gradation.lift.database.util.Constants.Entity.HISTORY_TABLE_NAME
import com.gradation.lift.database.util.LocalDateTypeConverter
import com.gradation.lift.database.util.LocalTimeTypeConverter
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime

@Entity(
    tableName = HISTORY_TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = HistoryRoutineEntity::class,
            parentColumns = ["id"],
            childColumns = ["history_id"]
        )
    ]
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

    @TypeConverters(LocalDateTypeConverter::class)
    @ColumnInfo(name = "history_time_stamp")
    val historyTimeStamp: LocalDate,

    )




