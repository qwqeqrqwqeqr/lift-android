package com.gradation.lift.domain.repository

import com.gradation.lift.common.model.DataState
import com.gradation.lift.model.model.picture.RoutineSetPicture
import com.gradation.lift.model.model.picture.UserProfilePicture
import kotlinx.coroutines.flow.Flow

interface PictureRepository {
    fun getUserProfilePicture(): Flow<DataState<List<UserProfilePicture>>>

    fun getRoutineSetPicture(): Flow<DataState<List<RoutineSetPicture>>>
}