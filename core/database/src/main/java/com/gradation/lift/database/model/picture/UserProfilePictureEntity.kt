package com.gradation.lift.database.model.picture

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gradation.lift.database.util.Constants.Entity.USER_PROFILE_PICTURE_TABLE_NAME


@Entity(tableName = USER_PROFILE_PICTURE_TABLE_NAME)
data class UserProfilePictureEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "url")
    val url : String
)









