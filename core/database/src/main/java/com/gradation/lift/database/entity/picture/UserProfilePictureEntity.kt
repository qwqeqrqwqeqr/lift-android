package com.gradation.lift.database.entity.picture

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gradation.lift.database.util.Constants.Entity.USER_PROFILE_PICTURE_TABLE_NAME
import com.gradation.lift.model.picture.UserProfilePicture


@Entity(tableName = USER_PROFILE_PICTURE_TABLE_NAME)
data class UserProfilePictureEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "url")
    val url : String
){
    fun toDomain() = UserProfilePicture(
        id,url
    )
}









