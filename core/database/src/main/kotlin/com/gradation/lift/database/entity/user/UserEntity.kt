package com.gradation.lift.database.entity.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gradation.lift.database.util.Constants.Entity.USER_TABLE_NAME
import com.gradation.lift.model.model.user.Gender
import com.gradation.lift.model.model.user.UnitOfWeight
import com.gradation.lift.model.model.user.UserDetail


@Entity(
    tableName = USER_TABLE_NAME
)
data class UserEntity(

    @PrimaryKey
    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "gender")
    val gender: Gender,

    @ColumnInfo(name = "height")
    val height: Float,

    @ColumnInfo(name = "weight")
    val weight: Float,

    @ColumnInfo(name = "profile_picture")
    val profilePicture: String,

    @ColumnInfo(name = "unit_of_weight")
    val unitOfWeight: UnitOfWeight,
) {
    fun toDomain() = UserDetail(
        name, gender, height, weight, profilePicture, unitOfWeight
    )
}