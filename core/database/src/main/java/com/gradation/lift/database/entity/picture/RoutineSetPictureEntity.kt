package com.gradation.lift.database.entity.picture

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gradation.lift.database.util.Constants.Entity.ROUTINE_SET_PICTURE_TABLE_NAME
import com.gradation.lift.model.picture.RoutineSetPicture


@Entity(tableName = ROUTINE_SET_PICTURE_TABLE_NAME)
data class RoutineSetPictureEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "category")
    val category: String,
    @ColumnInfo(name = "url")
    val url: String,
){
    fun toDomain() = RoutineSetPicture(
        id, category, url
    )
}



