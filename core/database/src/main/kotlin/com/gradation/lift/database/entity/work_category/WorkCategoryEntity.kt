package com.gradation.lift.database.entity.work_category

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.gradation.lift.database.util.Constants.Entity.WORK_CATEGORY_TABLE_NAME
import com.gradation.lift.database.util.ListTypeConverter
import com.gradation.lift.model.model.work.WorkCategory

@Entity(tableName = WORK_CATEGORY_TABLE_NAME)
data class WorkCategoryEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "work_part")
    @TypeConverters(ListTypeConverter::class)
    val workPart: List<String>,

    @ColumnInfo(name = "introduce")
    val introduce: String?,

    @ColumnInfo(name = "description")
    val description: String?,
) {
    fun toDomain() = WorkCategory(
        id = id,
        name = name,
        workPart = workPart,
        introduce = introduce,
        description = description
    )
}





