package com.gradation.lift.database.entity.routine

import androidx.room.*
import com.gradation.lift.database.entity.work_category.WorkCategoryEntity
import com.gradation.lift.database.util.Constants.Entity.ROUTINE_TABLE_NAME
import com.gradation.lift.database.util.WorkSetListTypeConverter
import com.gradation.lift.model.model.routine.Routine
import com.gradation.lift.model.model.work.WorkSet

@Entity(
    tableName = ROUTINE_TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = RoutineSetRoutineEntity::class,
            parentColumns = ["id"],
            childColumns = ["routine_set_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class RoutineEntity(

    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "routine_set_id")
    var routineSetId: Int,

    @Embedded(prefix = "work_category")
    val workCategoryEntity: WorkCategoryEntity,


    @TypeConverters(WorkSetListTypeConverter::class)
    @ColumnInfo(name = "work_set_list")
    val workSetList: List<WorkSet>,
) {
    fun toDomain() = Routine(
        id = id,
        routineSetId = routineSetId,
        workCategory = workCategoryEntity.toDomain(),
        workSetList = workSetList
    )
}




