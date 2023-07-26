package com.gradation.lift.database.model.routine

import androidx.room.*
import com.gradation.lift.database.model.work_category.WorkCategoryEntity
import com.gradation.lift.database.util.Constants.Entity.ROUTINE_TABLE_NAME
import com.gradation.lift.database.util.WorkSetListTypeConverter
import com.gradation.lift.model.work.WorkSet

@Entity(tableName = ROUTINE_TABLE_NAME)
data class RoutineEntity(

    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "routine_set_id")
    var routineSetId: Int,

    @Embedded(prefix = "work_category_")
    val workCategoryEntity: WorkCategoryEntity,


    @TypeConverters(WorkSetListTypeConverter::class)
    @ColumnInfo(name = "work_set_list")
    val workSetList: List<WorkSet>,
)




