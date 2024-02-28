package com.gradation.lift.database.entity.badge

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gradation.lift.database.util.Constants
import com.gradation.lift.model.model.badge.Badge


@Entity(tableName = Constants.Entity.BADGE_TABLE_NAME)

data class BadgeEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "hint")
    val hint: String,
    @ColumnInfo(name = "url")
    val url: String,
    @ColumnInfo(name = "color")
    val color: String,
    @ColumnInfo(name = "background_color")
    val backgroundColor: String,
) {
    fun toDomain() = Badge(
        id, name, description, hint, url, color, backgroundColor
    )
}
