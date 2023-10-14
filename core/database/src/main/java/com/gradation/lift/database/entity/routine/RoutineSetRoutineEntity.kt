package com.gradation.lift.database.entity.routine

import androidx.room.*
import com.gradation.lift.database.util.Constants.Entity.ROUTINE_SET_ROUTINE_TABLE_NAME
import com.gradation.lift.database.util.LabelTypeConverter
import com.gradation.lift.database.util.WeekdayTypeConverter
import com.gradation.lift.model.model.date.Weekday
import com.gradation.lift.model.model.routine.Label
import com.gradation.lift.model.model.routine.RoutineSetRoutine

@Entity(
    tableName = ROUTINE_SET_ROUTINE_TABLE_NAME
)
data class RoutineSetRoutineEntity(

    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "description")
    val description: String = "",

    @ColumnInfo(name = "picture")
    val picture: String,

    @ColumnInfo(name = "weekday")
    @TypeConverters(WeekdayTypeConverter::class)
    val weekday: List<Weekday>,

    @ColumnInfo(name = "label")
    @TypeConverters(LabelTypeConverter::class)
    val label: List<Label>,

    @ColumnInfo(name = "count")
    val count: Int,
) {
    fun toDomain() = RoutineSetRoutine(
        id = id,
        name = name,
        description = description,
        weekday = weekday,
        picture = picture,
        label = label,
        count = count,
        routine = emptyList()
    )
}