package com.gradation.lift.navigation

/**
 * [Router]
 * 네비게이션 라우터 경로
 * graph 의 경우 접두어로 _graph 사용할 것
 * 현재 사용하는 그래프는 하단과 같음
 * [BADGE_GRAPH_NAME]
 * [CREATE_ROUTINE_GRAPH_NAME]
 * [HISTORY_GRAPH_NAME]
 * [HOME_GRAPH_NAME]
 * [LOGIN_GRAPH_NAME]
 * [MY_INFO_GRAPH_NAME]
 * [NOTIFICATION_GRAPH_NAME]
 * [REGISTER_DETAIL_GRAPH_NAME]
 * [WORK_GRAPH_NAME]
 * @since 2023-08-19 14:55:43
 * */
object Router {


    const val BADGE_GRAPH_NAME = "badge_graph"
    const val BADGE_BADGE_ROUTER_NAME = "badge"
    const val BADGE_SETTING_ROUTER_NAME = "setting"


    const val CREATE_ROUTINE_GRAPH_NAME = "create_routine_graph"
    const val CREATE_ROUTINE_ROUTINE_SET_ROUTER_NAME = "routine_set"
    const val CREATE_ROUTINE_ROUTINE_ROUTER_NAME = "routine"
    const val CREATE_ROUTINE_PROFILE_ROUTER_NAME = "profile"
    const val CREATE_ROUTINE_FIND_WORK_CATEGORY_ROUTER_NAME = "find_work_category"

    const val HISTORY_GRAPH_NAME = "history_graph"
    const val HISTORY_HISTORY_ROUTER_NAME = "history"
    const val HISTORY_ANALYTICS_ROUTER_NAME = "analytics"
    const val HISTORY_DAILY_LOG_ROUTER_NAME = "daily_log"

    const val HOME_GRAPH_NAME = "home_graph"
    const val HOME_HOME_ROUTER_NAME = "home"

    const val LOGIN_GRAPH_NAME = "login_graph"
    const val LOGIN_SIGN_IN_ROUTER_NAME = "sign_in"
    const val LOGIN_SIGN_UP_ROUTER_NAME = "sign_up"
    const val LOGIN_COMPLETE_ROUTER_NAME = "complete"
    const val LOGIN_FIND_EMAIL_PASSWORD_ROUTER_NAME = "find_email_password"

    const val MY_INFO_GRAPH_NAME = "myinfo_graph"
    const val MY_INFO_MY_INFO_ROUTER_NAME = "myinfo"
    const val MY_INFO_UPDATE_ROUTER_NAME = "update"
    const val MY_INFO_UPDATE_PROFILE_ROUTER_NAME = "update_profile"

    const val NOTIFICATION_GRAPH_NAME = "notification_graph"
    const val NOTIFICATION_NOTICE_ROUTER_NAME = "notice"
    const val NOTIFICATION_NOTIFICATION_ROUTER_NAME = "notification"

    const val REGISTER_DETAIL_GRAPH_NAME = "register_detail_graph"
    const val REGISTER_DETAIL_GENDER_ROUTER_NAME = "gender"
    const val REGISTER_DETAIL_HEIGHT_WEIGHT_ROUTER_NAME = "height_weight"
    const val REGISTER_DETAIL_NAME_ROUTER_NAME = "name"
    const val REGISTER_DETAIL_PROFILE_PICTURE_ROUTER_NAME = "profile_picture"

    const val WORK_GRAPH_NAME = "work_graph"
    const val WORK_ROUTINE_SELECTION_ROUTER_NAME = "routine_selection"
    const val WORK_CHANGE_ORDER_ROUTER_NAME = "change_order"
    const val WORK_WORK_ROUTER_NAME = "work"
    const val WORK_COMPLETE_ROUTER_NAME = "complete"
}