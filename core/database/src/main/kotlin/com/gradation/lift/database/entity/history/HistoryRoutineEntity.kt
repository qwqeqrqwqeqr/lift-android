package com.gradation.lift.database.entity.history

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.gradation.lift.database.util.Constants.Entity.HISTORY_ROUTINE_TABLE_NAME
import com.gradation.lift.database.util.StringListTypeConverter
import com.gradation.lift.database.util.WorkSetListTypeConverter
import com.gradation.lift.model.model.history.HistoryRoutine
import com.gradation.lift.model.model.work.WorkSet

@Entity(
    tableName = HISTORY_ROUTINE_TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = HistoryEntity::class,
            parentColumns = ["id"],
            childColumns = ["history_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class HistoryRoutineEntity(


    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "history_id")
    val historyId: Int,

    @ColumnInfo(name = "work_category_id")
    var workCategoryId: Int,

    @ColumnInfo(name = "work_category_name")
    var workCategoryName: String,

    @TypeConverters(StringListTypeConverter::class)
    @ColumnInfo(name = "work_part")
    var workPart: List<String>,

    @TypeConverters(WorkSetListTypeConverter::class)
    @ColumnInfo(name = "work_set_list")
    val workSetList: List<WorkSet>,
) {
    fun toDomain() = HistoryRoutine(
        id, historyId, workCategoryId, workCategoryName, workPart, workSetList
    )
}


