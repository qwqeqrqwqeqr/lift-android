package com.gradation.lift.feature.update_routine.profile_picture.data.state

import com.gradation.lift.feature.update_routine.profile_picture.data.model.RoutineSetCategoryPicture


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

