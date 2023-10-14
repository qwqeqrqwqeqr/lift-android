package com.gradation.lift.database.entity.badge

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.gradation.lift.database.util.Constants
import com.gradation.lift.database.util.LocalTimeTypeConverter
import com.gradation.lift.model.model.badge.UserBadge
import kotlinx.datetime.LocalDateTime


@Entity(tableName = Constants.Entity.USER_BADGE_TABLE_NAME)
data class UserBadgeEntity(

    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Long = 0,

    @Embedded(prefix = "badge_")
    var badge: BadgeEntity,


    @TypeConverters(LocalTimeTypeConverter::class)
    @ColumnInfo(name = "badge_time_stamp")
    var badgeTimeStamp: LocalDateTime,

    @ColumnInfo(name = "main_flag")
    val mainFlag: Boolean
) {
    fun toDomain() = UserBadge(
        badge.toDomain(), badgeTimeStamp, mainFlag
    )
}
