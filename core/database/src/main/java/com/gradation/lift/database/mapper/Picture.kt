package com.gradation.lift.database.mapper

import com.gradation.lift.database.entity.picture.RoutineSetPictureEntity
import com.gradation.lift.database.entity.picture.UserProfilePictureEntity
import com.gradation.lift.model.picture.RoutineSetPicture
import com.gradation.lift.model.picture.UserProfilePicture


fun UserProfilePicture.toEntity() = UserProfilePictureEntity(
    id, url
)

fun RoutineSetPicture.toEntity() = RoutineSetPictureEntity(
    id, category, url
)