package com.gradation.lift.database.model

import androidx.room.*
import com.gradation.lift.database.util.Constants.Entity.HISTORY_ROUTINE_TABLE_NAME
import com.gradation.lift.database.util.WorkSetListTypeConverter
import com.gradation.lift.model.work.WorkSet

@Entity(tableName = HISTORY_ROUTINE_TABLE_NAME)
data class HistoryRoutineEntity(


    @PrimaryKey
    @ColumnInfo(name = "id")
    val id :Int,

    @ColumnInfo(name = "history_id")
    val historyId :Int,

    @Embedded(prefix = "work_category_")
    val workCategoryEntity: WorkCategoryEntity,

    @TypeConverters(WorkSetListTypeConverter::class)
    @ColumnInfo(name = "work_set_list")
    val workSetList : List<WorkSet>

)


