package com.gradation.lift.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user_profile_picture")
data class UserProfilePictureEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val url: String



    val category: String,
)

data class RoutineSetPictureEntity()