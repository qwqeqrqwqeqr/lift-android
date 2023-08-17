package com.gradation.lift.navigation

/**
 * [Router]
 * 네비게이션 라우터 경로
 * graph 의 경우 접두어로 _graph 사용할 것
 * @since 2023-08-17 13:32:46
 */
object Router {

    /**
     * CREATE_ROUTINE
     * 루틴 만들기
     */
    const val CREATE_ROUTINE_GRAPH_NAME = "create_routine_graph"
    const val CREATE_ROUTINE_ROUTINE_SET_ROUTER_NAME = "routine_set"
    const val CREATE_ROUTINE_ROUTINE_ROUTER_NAME = "routine"
    const val CREATE_ROUTINE_PROFILE_ROUTER_NAME = "profile"
    const val CREATE_ROUTINE_FIND_WORK_CATEGORY_ROUTER_NAME = "find_work_category"

    /**
     * LOGIN
     * 로그인
     */
    const val LOGIN_GRAPH_NAME = "login_graph"
    const val LOGIN_SIGN_IN_ROUTER_NAME = "sign_in"
    const val LOGIN_SIGN_UP_ROUTER_NAME = "sign_up"
    const val LOGIN_TERMS_OF_USE_ROUTER_NAME = "terms_of_use"
    const val LOGIN_VERIFICATION_ROUTER_NAME = "verification"
    const val LOGIN_COMPLETE_ROUTER_NAME = "complete"
    const val LOGIN_FIND_EMAIL_PASSWORD_ROUTER_NAME = "find_email_password"

    /**
     * REGISTER_DETAIL
     * 사용자 상세정보
     */
    const val REGISTER_DETAIL_GRAPH_NAME = "register_detail_graph"
    const val REGISTER_DETAIL_GENDER_ROUTER_NAME = "gender"
    const val REGISTER_DETAIL_HEIGHT_WEIGHT_ROUTER_NAME = "height_weight"
    const val REGISTER_DETAIL_NAME_ROUTER_NAME = "name"
    const val REGISTER_DETAIL_UNIT_OF_WEIGHT_ROUTER_NAME = "unit_of_weight"
    const val REGISTER_DETAIL_PROFILE_PICTURE_ROUTER_NAME = "profile_picture"

    /**
     * MAIN
     * 메인
     */
    const val MAIN_GRAPH_NAME = "main_graph"
    const val HOME_ROUTER_NAME = "home"
    const val HISTORY_ROUTER_NAME = "history"
    const val MY_INFO_ROUTER_NAME = "myinfo"

    /**
     * WORK
     * 운동
     */
    const val WORK_GRAPH_NAME = "work_graph"
    const val WORK_ROUTINE_SELECTION_ROUTER_NAME = "routine_selection"
    const val WORK_CHANGE_ORDER_ROUTER_NAME = "change_order"
    const val WORK_WORK_ROUTER_NAME = "work"
    const val WORK_COMPLETE_ROUTER_NAME = "complete"

}