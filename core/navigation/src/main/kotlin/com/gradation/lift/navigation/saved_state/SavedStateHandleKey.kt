package com.gradation.lift.navigation.saved_state

/**
 * [SavedStateHandleKey]
 * SavedStateHandle 을 통해 관리해야하는 키
 * @since 2023-08-17 13:36:08
 */
object SavedStateHandleKey {
    object RoutineSet{
        const val WORK_ROUTINE_SET_LIST_ID_KEY ="WORK_ROUTINE_SET_LIST_ID_KEY"
        const val DETAIL_ROUTINE_SET_ID_KEY ="DETAIL_ROUTINE_SET_ID_KEY"
        const val UPDATE_ROUTINE_SET_ID_KEY ="UPDATE_ROUTINE_SET_ID_KEY"
        const val UPDATE_WORK_CATEGORY_ID_KEY ="UPDATE_WORK_CATEGORY_ID_KEY"
        const val CREATE_WORK_CATEGORY_ID_KEY =" CREATE_WORK_CATEGORY_ID_KEY"
    }

    object Login{
        const val RESET_PASSWORD_EMAIL_KEY ="RESET_PASSWORD_EMAIL_KEY"
    }
}