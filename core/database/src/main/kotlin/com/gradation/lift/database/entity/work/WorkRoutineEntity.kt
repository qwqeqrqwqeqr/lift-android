package com.gradation.lift.database.entity.work

import androidx.room.*
import com.gradation.lift.database.entity.work_category.WorkCategoryEntity
import com.gradation.lift.database.util.Constants.Entity.WORK_ROUTINE_TABLE_NAME
import com.gradation.lift.database.util.WorkSetListTypeConverter
import com.gradation.lift.model.model.work.WorkRoutine
import com.gradation.lift.model.model.work.WorkSet
import java.util.UUID

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
    val id: Int = 0,

    @ColumnInfo(name = "work_id")
    val workId: UUID,

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


