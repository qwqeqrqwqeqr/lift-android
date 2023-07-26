package com.gradation.lift.database.model

import androidx.room.*
import com.gradation.lift.database.util.Constants.Entity.WORK_TABLE_NAME
import com.gradation.lift.database.util.LocalTimeTypeConverter
import com.gradation.lift.database.util.WorkSetListTypeConverter
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime

/**
 * [WorkEntity]
 * 진행중인 운동 가록하는 Entity
 */
@Entity(
    tableName = WORK_TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = WorkRoutineEntity::class,
            parentColumns = ["id"],
            childColumns = ["work_id"]
        )
    ]
)

data class WorkEntity(

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

    )


