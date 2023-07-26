package com.gradation.lift.database.model.work

import androidx.room.*
import com.gradation.lift.database.model.work_category.WorkCategoryEntity
import com.gradation.lift.database.util.Constants.Entity.WORK_ROUTINE_TABLE_NAME
import com.gradation.lift.database.util.WorkSetListTypeConverter
import com.gradation.lift.model.work.WorkSet
@Entity(tableName = WORK_ROUTINE_TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = WorkEntity::class,
            parentColumns = ["id"],
            childColumns = ["work_id"]
        )
    ])
data class WorkRoutineEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id :Int,

    @ColumnInfo(name = "work_id")
    val workId :Int,

    @Embedded(prefix = "work_category_")
    val workCategoryEntity: WorkCategoryEntity,

    @TypeConverters(WorkSetListTypeConverter::class)
    @ColumnInfo(name = "work_set_list")
    val workSetList : List<WorkSet>
)


