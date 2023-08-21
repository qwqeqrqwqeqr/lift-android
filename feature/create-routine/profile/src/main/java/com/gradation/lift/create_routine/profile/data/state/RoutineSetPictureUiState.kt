package com.gradation.lift.create_routine.profile.data.state

import com.gradation.lift.create_routine.profile.data.model.RoutineSetCategoryPicture

sealed interface RoutineSetPictureUiState {
    data class Success(val routineSetPictureList: List<RoutineSetCategoryPicture>) :
        RoutineSetPictureUiState

    object Loading : RoutineSetPictureUiState
    object Fail : RoutineSetPictureUiState
}

