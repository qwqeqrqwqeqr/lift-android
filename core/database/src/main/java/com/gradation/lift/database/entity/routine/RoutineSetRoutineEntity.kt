package com.gradation.lift.database.entity.routine

import androidx.room.*
import com.gradation.lift.database.util.Constants.Entity.ROUTINE_SET_ROUTINE_TABLE_NAME
import com.gradation.lift.database.util.WeekdayTypeConverter
import com.gradation.lift.model.model.common.Weekday
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

    @ColumnInfo(name = "weekday")
    @TypeConverters(WeekdayTypeConverter::class)
    val weekday: Weekday,

    @ColumnInfo(name = "picture")
    val picture: String,
){
    fun toDomain() = RoutineSetRoutine(
        id=id,
        name=name,
        description=description,
        weekday=weekday,
        picture=picture,
        routine = emptyList()
    )
}