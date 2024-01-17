package com.gradation.lift.myInfo.updateProfilePicture.data.state

/**
* [UpdateProfilePictureState]
* 사용자의 상세정보를 업데이트시 업데이트 상태를 관측하기위한 상태
* @since 2023-08-25 16:43:44
*/
sealed interface UpdateProfilePictureState {
    data object Success : UpdateProfilePictureState
    data class Fail(val message: String) : UpdateProfilePictureState
    data object None : UpdateProfilePictureState
}


