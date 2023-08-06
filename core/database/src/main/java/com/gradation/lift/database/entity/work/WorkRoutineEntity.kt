package com.gradation.lift.database.entity.work

import androidx.room.*
import com.gradation.lift.database.entity.work_category.WorkCategoryEntity
import com.gradation.lift.database.util.Constants.Entity.WORK_ROUTINE_TABLE_NAME
import com.gradation.lift.database.util.WorkSetListTypeConverter
import com.gradation.lift.model.work.Work
import com.gradation.lift.model.work.WorkRoutine
import com.gradation.lift.model.work.WorkSet

@Entity(
    tableName = WORK_ROUTINE_TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = WorkEntity::class,
            parentColumns = ["id"],
            childColumns = ["work_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class WorkRoutineEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "work_id")
    val workId: Int,

    @Embedded(prefix = "work_category_")
    val workCategory: WorkCategoryEntity,

    @TypeConverters(WorkSetListTypeConverter::class)
    @ColumnInfo(name = "work_set_list")
    val workSetList: List<WorkSet>,
) {
    fun toDomain(): WorkRoutine = WorkRoutine(
        workId = this.workId,
        workCategory = this.workCategory.toDomain(),
        workSetList = this.workSetList
    )
}


