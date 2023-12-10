package com.gradation.lift.createRoutine.profilePicture.data.state

import com.gradation.lift.createRoutine.profilePicture.data.model.RoutineSetCategoryPicture

/**
 * [RoutineSetPictureUiState]
 * 루틴세트에 사용될 프로필 사진 목록
 */
sealed interface RoutineSetPictureUiState {
    data class Success(val routineSetPictureList: List<RoutineSetCategoryPicture>) :
        RoutineSetPictureUiState
    object Loading : RoutineSetPictureUiState
    object Fail : RoutineSetPictureUiState
}

