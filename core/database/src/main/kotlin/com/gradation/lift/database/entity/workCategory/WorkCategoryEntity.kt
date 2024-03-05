package com.gradation.lift.database.entity.workCategory

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.gradation.lift.database.util.Constants.Entity.WORK_CATEGORY_TABLE_NAME
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
    val sequence: List<String>,

    @ColumnInfo(name = "effect")
    val effect: List<String>,

    @ColumnInfo(name = "caution")
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
        sequence = sequence.map {
            it.split(":")
                .let { item ->
                    SequenceContent(
                        sequence = item[0].toInt(),
                        title = item[1],
                        content = item[2]
                    )
                }
        },
        effect = effect.map {
            it.split(":")
                .let { item -> EffectContent(title = item[0], content = item[1]) }
        },
        caution = caution
    )
}





