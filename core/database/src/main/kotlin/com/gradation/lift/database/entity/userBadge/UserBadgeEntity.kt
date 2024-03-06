package com.gradation.lift.database.entity.userBadge

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.gradation.lift.database.entity.badge.BadgeEntity
import com.gradation.lift.database.util.Constants
import com.gradation.lift.database.util.LocalTimeTypeConverter
import com.gradation.lift.model.model.badge.UserBadge
import kotlinx.datetime.LocalDateTime


@Entity(tableName = Constants.Entity.USER_BADGE_TABLE_NAME)
data class UserBadgeEntity(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "description")
    val description: String? = null,
    @ColumnInfo(name = "hint")
    val hint: String,
    @ColumnInfo(name = "url")
    val url: String,
    @ColumnInfo(name = "color")
    val color: String,
    @ColumnInfo(name = "background_color")
    val backgroundColor: String,
    @TypeConverters(LocalTimeTypeConverter::class)
    @ColumnInfo(name = "badge_time_stamp")
    var badgeTimeStamp: LocalDateTime,
    @ColumnInfo(name = "main_flag")
    val mainFlag: Boolean,
) {
    fun toDomain(): UserBadge = UserBadge(
        badge = BadgeEntity(
            id,
            name,
            description,
            hint,
            url,
            color,
            backgroundColor,
        ).toDomain(),
        badgeTimeStamp = badgeTimeStamp,
        mainFlag = mainFlag
    )
}
