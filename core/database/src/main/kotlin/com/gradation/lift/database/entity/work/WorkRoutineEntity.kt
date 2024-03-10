package com.gradation.lift.database.entity.work

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.gradation.lift.database.util.Constants.Entity.WORK_ROUTINE_TABLE_NAME
import com.gradation.lift.database.util.StringListTypeConverter
import com.gradation.lift.database.util.WorkSetListTypeConverter
import com.gradation.lift.model.model.work.WorkRoutine
import com.gradation.lift.model.model.work.WorkSet

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
    val id: Long = 0,

    @ColumnInfo(name = "work_id")
    val workId: Long,

    @ColumnInfo(name = "work_routine_id")
    var workRoutineId: Int,

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
    fun toDomain(): WorkRoutine = WorkRoutine(
        workId, workRoutineId, workCategoryId, workCategoryName, workPart, workSetList
    )
}


