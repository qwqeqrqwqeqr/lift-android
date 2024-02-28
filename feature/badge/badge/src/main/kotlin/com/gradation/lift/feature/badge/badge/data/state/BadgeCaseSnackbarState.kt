package com.gradation.lift.feature.badge.badge.data.state

sealed interface BadgeCaseSnackbarState {

    data class FillMaxed(
        val message: String = "대표뱃지 설정은 최대 5개까지 가능해요",
    ) : BadgeCaseSnackbarState

    data class UpdateCompleted(
        val message: String = "뱃지 설정을 완료 하였습니다",
    ) : BadgeCaseSnackbarState

    data class Fail(
        val message: String = "뱃지 설정에 실패하였습니다.\n잠시후에 다시 시도해주세요",
    ) : BadgeCaseSnackbarState

    data object None : BadgeCaseSnackbarState
}