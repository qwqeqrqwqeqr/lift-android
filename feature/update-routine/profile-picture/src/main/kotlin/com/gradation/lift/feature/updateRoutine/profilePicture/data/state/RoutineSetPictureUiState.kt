package com.gradation.lift.feature.updateRoutine.profilePicture.data.state

import com.gradation.lift.feature.updateRoutine.profilePicture.data.model.RoutineSetCategoryPicture


/**
 * [RoutineSetPictureUiState]
 * 루틴세트에 사용될 프로필 사진 목록
 */
sealed interface RoutineSetPictureUiState {
    data class Success(val routineSetPictureList: List<RoutineSetCategoryPicture>) :
        RoutineSetPictureUiState
    data object Loading : RoutineSetPictureUiState
    data object Fail : RoutineSetPictureUiState
}

