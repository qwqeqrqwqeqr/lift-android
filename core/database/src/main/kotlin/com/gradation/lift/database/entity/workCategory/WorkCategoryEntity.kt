package com.gradation.lift.database.entity.workCategory

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.gradation.lift.database.util.Constants.Entity.WORK_CATEGORY_TABLE_NAME
import com.gradation.lift.database.util.EffectContentListTypeConverter
import com.gradation.lift.database.util.SequenceContentListTypeConverter
import com.gradation.lift.database.util.StringListTypeConverter
import com.gradation.lift.model.model.work.EffectContent
import com.gradation.lift.model.model.work.SequenceContent
import com.gradation.lift.model.model.work.WorkCategory

@Entity(tableName = WORK_CATEGORY_TABLE_NAME)
data class WorkCategoryEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "equipment")
    var equipment: String,

    @ColumnInfo(name = "search_tag")
    var searchTag: List<String>,

    @ColumnInfo(name = "work_part")
    @TypeConverters(StringListTypeConverter::class)
    val workPart: List<String>,

    @ColumnInfo(name = "introduce")
    val introduce: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "sequence")
    @TypeConverters(SequenceContentListTypeConverter::class)
    val sequence: List<SequenceContent>,

    @ColumnInfo(name = "effect")
    @TypeConverters(EffectContentListTypeConverter::class)
    val effect: List<EffectContent>,

    @ColumnInfo(name = "caution")
    @TypeConverters(StringListTypeConverter::class)
    val caution: List<String>,
) {
    fun toDomain() = WorkCategory(
        id = id,
        name = name,
        equipment = equipment,
        searchTag = searchTag,
        workPart = workPart,
        introduce = introduce,
        description = description,
        sequence = sequence,
        effect = effect,
        caution = caution
    )
}





