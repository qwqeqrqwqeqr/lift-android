package com.gradation.lift.database.entity.routine

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.gradation.lift.database.util.Constants.Entity.ROUTINE_TABLE_NAME
import com.gradation.lift.database.util.StringListTypeConverter
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
    var id: Int?,

    @ColumnInfo(name = "routine_set_id")
    var routineSetId: Int,

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
    fun toDomain() = Routine(
        id, routineSetId, workCategoryId, workCategoryName, workPart, workSetList
    )
}




