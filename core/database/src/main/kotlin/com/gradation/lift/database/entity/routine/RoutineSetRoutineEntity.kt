package com.gradation.lift.database.entity.routine

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.gradation.lift.database.util.Constants.Entity.ROUTINE_SET_ROUTINE_TABLE_NAME
import com.gradation.lift.database.util.StringListTypeConverter
import com.gradation.lift.model.model.date.toWeekDay
import com.gradation.lift.model.model.routine.RoutineSetRoutine
import com.gradation.lift.model.model.routine.toLabel

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
    @TypeConverters(StringListTypeConverter::class)
    val weekday: List<String>,

    @ColumnInfo(name = "label")
    @TypeConverters(StringListTypeConverter::class)
    val label: List<String>,

    @ColumnInfo(name = "count")
    val count: Int,
) {
    fun toDomain() = RoutineSetRoutine(
        id = id,
        name = name,
        description = description,
        weekday = weekday.map { it.toWeekDay() }.toSet(),
        picture = picture,
        label = label.map { it.toLabel() }.toSet(),
        count = count,
        routine = emptyList()
    )
}