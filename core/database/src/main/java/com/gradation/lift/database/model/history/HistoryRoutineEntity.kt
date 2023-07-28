package com.gradation.lift.database.model.history

import androidx.room.*
import com.gradation.lift.database.model.work_category.WorkCategoryEntity
import com.gradation.lift.database.util.Constants.Entity.HISTORY_ROUTINE_TABLE_NAME
import com.gradation.lift.database.util.WorkSetListTypeConverter
import com.gradation.lift.model.work.WorkSet

@Entity(
    tableName = HISTORY_ROUTINE_TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = HistoryEntity::class,
            parentColumns = ["id"],
            childColumns = ["history_id"]
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
    val workCategoryEntity: WorkCategoryEntity,

    @TypeConverters(WorkSetListTypeConverter::class)
    @ColumnInfo(name = "work_set_list")
    val workSetList: List<WorkSet>,
)



