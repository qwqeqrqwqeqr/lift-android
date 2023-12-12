package com.gradation.lift.database.entity.history

import androidx.room.*
import com.gradation.lift.database.entity.work_category.WorkCategoryEntity
import com.gradation.lift.database.util.Constants.Entity.HISTORY_ROUTINE_TABLE_NAME
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

    @Embedded(prefix = "work_category_")
    val workCategory: WorkCategoryEntity,

    @TypeConverters(WorkSetListTypeConverter::class)
    @ColumnInfo(name = "work_set_list")
    val workSetList: List<WorkSet>,
) {
    fun toDomain() = HistoryRoutine(
        id = id,
        historyId = historyId,
        workCategory = workCategory.toDomain(),
        workSetList = workSetList
    )
}


