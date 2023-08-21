package com.gradation.lift.create_routine.profile.data.state

import com.gradation.lift.create_routine.profile.data.model.RoutineSetCategoryPicture

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

