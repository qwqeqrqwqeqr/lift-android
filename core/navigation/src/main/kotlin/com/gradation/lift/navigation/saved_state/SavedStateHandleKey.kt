package com.gradation.lift.navigation.saved_state

/**
 * [SavedStateHandleKey]
 * SavedStateHandle 을 통해 관리해야하는 키
 * @since 2023-08-17 13:36:08
 */
object SavedStateHandleKey {
    object RoutineSet {
        const val DETAIL_ROUTINE_SET_ID_KEY = "DETAIL_ROUTINE_SET_ID_KEY"
    }

    object Login {
        const val RESET_PASSWORD_EMAIL_KEY = "RESET_PASSWORD_EMAIL_KEY"
    }

    object CreateRoutine {
        const val CREATE_WORK_CATEGORY_ID_KEY = "CREATE_ROUTINE_CREATE_WORK_CATEGORY_ID_KEY"
        const val CREATE_ROUTINE_SELECTED_ROUTINE_INDEX_KEY =
            "CREATE_ROUTINE_SELECTED_ROUTINE_INDEX_KEY"
    }

    object Badge {
        const val BADGE_PAGE_KEY = "BADGE_PAGE_KEY"
    }

    object UpdateRoutine {
        const val UPDATE_ROUTINE_SET_ID_KEY = "UPDATE_ROUTINE_SET_ID_KEY"
        const val UPDATE_WORK_CATEGORY_ID_KEY = "UPDATE_WORK_CATEGORY_ID_KEY"
        const val UPDATE_ROUTINE_SELECTED_ROUTINE_INDEX_KEY =
            "UPDATE_ROUTINE_SELECTED_ROUTINE_INDEX_KEY"
    }

    object Notice {
        const val NOTICE_ID_KEY = "NOTICE_ID_KEY"
    }

    object MyInfo {
        const val USER_PROFILE_PICTURE_KEY = "MY_INFO_USER_PROFILE_PICTURE_KEY"
        const val USER_GENDER_KEY = "MY_INFO_USER_GENDER_KEY"
        const val USER_HEIGHT_KEY = "MY_INFO_USER_HEIGHT_KEY"
        const val USER_WEIGHT_KEY = "MY_INFO_USER_WEIGHT_KEY"
        const val USER_NAME_KEY = "MY_INFO_USER_NAME_KEY_KEY"
        const val TERMS_POLICY_TITLE_KEY = "TERMS_POLICY_TITLE_KEY"
        const val TERMS_POLICY_CONTENTS_KEY = "TERMS_POLICY_CONTENTS_KEY"

        const val MY_INFO_CANCEL_MEMBERSHIP_REASON_KEY = "MY_INFO_CANCEL_MEMBERSHIP_REASON_KEY"
    }

    object Work {
        const val WORK_ROUTINE_SET_ID_LIST_KEY = "WORK_ROUTINE_SET_ID_LIST_KEY"
        const val WORK_READY_WORK_CATEGORY_ID_KEY = "WORK_READY_WORK_CATEGORY_ID_KEY"
        const val WORK_WORK_CATEGORY_ID_KEY = "WORK_WORK_CATEGORY_ID_KEY"
    }

    object History {
        const val HISTORY_COMMENT_KEY = "HISTORY_COMMENT_KEY"
        const val HISTORY_SCORE_KEY = "HISTORY_SCORE_KEY"
        const val HISTORY_ID_KEY = "HISTORY_ID_KEY"
        const val HISTORY_DATE_KEY = "HISTORY_DATE_KEY"
    }
}