package com.gradation.lift.navigation

/**
 * [Router]
 * 네비게이션 라우터 경로
 * graph 의 경우 접두어로 _graph 사용할 것
 * 현재 사용하는 그래프는 하단과 같음
 * [BADGE_GRAPH_NAME]
 * [CREATE_ROUTINE_GRAPH_NAME]
 * [UPDATE_ROUTINE_GRAPH_NAME]
 * [HISTORY_GRAPH_NAME]
 * [HOME_GRAPH_NAME]
 * [LOGIN_GRAPH_NAME]
 * [MY_INFO_GRAPH_NAME]
 * [NOTIFICATION_GRAPH_NAME]
 * [REGISTER_DETAIL_GRAPH_NAME]
 * [ROUTINE_DETAIL_GRAPH_NAME]
 * [WORK_GRAPH_NAME]
 * @since 2023-10-18 12:43:52
 * */
object Router {


    const val BADGE_GRAPH_NAME = "badge_graph"
    const val BADGE_BADGE_ROUTER_NAME = "badge_badge"
    const val BADGE_SETTING_ROUTER_NAME = "badge_setting"

    const val NEW_BADGE_GRAPH_NAME = "new_badge_graph"
    const val NEW_BADGE_NEW_BADGE_ROUTER_NAME = "new_badge_new_badge"

    const val CREATE_ROUTINE_GRAPH_NAME = "create_routine_graph"
    const val CREATE_ROUTINE_ROUTINE_SET_ROUTER_NAME = "create_routine_routine_set"
    const val CREATE_ROUTINE_ROUTINE_ROUTER_NAME = "create_routine_routine"
    const val CREATE_ROUTINE_PROFILE_PICTURE_ROUTER_NAME = "create_routine_profile_picture"
    const val CREATE_ROUTINE_FIND_WORK_CATEGORY_ROUTER_NAME = "create_routine_find_work_category"

    const val UPDATE_ROUTINE_GRAPH_NAME = "update_routine_graph"
    const val UPDATE_ROUTINE_ROUTINE_SET_ROUTER_NAME = "update_routine_routine_set"
    const val UPDATE_ROUTINE_ROUTINE_ROUTER_NAME = "update_routine_routine"
    const val UPDATE_ROUTINE_PROFILE_PICTURE_ROUTER_NAME = "update_routine_profile_picture"
    const val UPDATE_ROUTINE_FIND_WORK_CATEGORY_ROUTER_NAME = "update_routine_find_work_category"

    const val HISTORY_GRAPH_NAME = "history_graph"
    const val HISTORY_HISTORY_ROUTER_NAME = "history_history"

    const val HOME_GRAPH_NAME = "home_graph"
    const val HOME_HOME_ROUTER_NAME = "home"

    const val LOGIN_GRAPH_NAME = "login_graph"
    const val LOGIN_SIGN_IN_ROUTER_NAME = "login_sign_in"
    const val LOGIN_COMPLETE_ROUTER_NAME = "login_complete"
    const val LOGIN_RESET_PASSWORD_ROUTER_NAME = "login_reset_password"
    const val LOGIN_SIGN_IN_DEFAULT_ROUTER_NAME = "login_sign_in_default"
    const val LOGIN_SIGN_UP_DEFAULT_ROUTER_NAME = "login_sign_up_default"
    const val LOGIN_TERMS_OF_USE_ROUTER_NAME = "login_terms_of_use"
    const val LOGIN_TERMS_OF_USE_DETAIL_ROUTER_NAME = "login_terms_of_use_detail"
    const val LOGIN_VERIFY_EMAIL_ROUTER_NAME = "login_verify_email"

    const val MY_INFO_GRAPH_NAME = "myinfo_graph"
    const val MY_INFO_MY_INFO_ROUTER_NAME = "myinfo_myinfo"
    const val MY_INFO_UPDATE_ROUTER_NAME = "myinfo_update"
    const val MY_INFO_UPDATE_PROFILE_ROUTER_NAME = "myinfo_update_profile"

    const val NOTIFICATION_GRAPH_NAME = "notification_graph"
    const val NOTIFICATION_NOTIFICATION_ROUTER_NAME = "notification_notification"
    const val NOTIFICATION_NOTICE_DETAIL_ROUTER_NAME = "notification_notice_detail"
    const val NOTIFICATION_PUSH_DETAIL_ROUTER_NAME = "notification_push_detail"

    const val REGISTER_DETAIL_GRAPH_NAME = "register_detail_graph"
    const val REGISTER_DETAIL_GENDER_ROUTER_NAME = "register_detail_gender"
    const val REGISTER_DETAIL_HEIGHT_WEIGHT_ROUTER_NAME = "register_detail_height_weight"
    const val REGISTER_DETAIL_NAME_ROUTER_NAME = "register_detail_name"
    const val REGISTER_DETAIL_PROFILE_PICTURE_ROUTER_NAME = "register_detail_profile_picture"

    const val ROUTINE_DETAIL_GRAPH_NAME = "routine_detail_graph"
    const val ROUTINE_DETAIL_ROUTINE_LIST_ROUTER_NAME = "routine_detail_routine_list"
    const val ROUTINE_DETAIL_ROUTINE_ROUTER_NAME = "routine_detail_routine"

    const val WORK_GRAPH_NAME = "work_graph"
    const val WORK_ROUTINE_SELECTION_ROUTER_NAME = "work_routine_selection"
    const val WORK_WORK_ROUTER_NAME = "work_work"
    const val WORK_COMPLETE_ROUTER_NAME = "work_complete"
}