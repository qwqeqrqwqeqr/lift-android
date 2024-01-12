package com.gradation.lift.navigation.navigation

import androidx.navigation.NavController
import com.gradation.lift.navigation.Route

/**
 * [navigateMyInfoGraphToLoginGraph]
 * 로그아웃 수행시 사용
 * @since 2024-01-12 13:05:32
 */
fun NavController.navigateMyInfoGraphToLoginGraph() {
    this.navigate(Route.LOGIN_GRAPH_NAME) {
        launchSingleTop = true
        popUpTo(this@navigateMyInfoGraphToLoginGraph.graph.id) {
            inclusive = true
        }
    }
}

/**
 * [navigateMyInfoGraphToNoticeGraph]
 * 공지사항 화면으로 이동
 * @since 2024-01-12 13:05:32
 */
fun NavController.navigateMyInfoGraphToNoticeGraph() {
    this.navigate(Route.NOTICE_GRAPH_NAME)
}

/**
 * [navigateMyInfoToProfileInMyInfoGraph]
 * 내정보 화면에서 내 프로필 화면으로 이동
 * @since 2024-01-12 13:08:02
 */
fun NavController.navigateMyInfoToProfileInMyInfoGraph() {
    this.navigate(Route.MY_INFO_PROFILE_ROUTER_NAME)
}

/**
 * [navigateProfileToMyInfoInMyInfoGraph]
 * 내 프로필 화면에서 내정보 화면으로 이동
 * @since 2024-01-12 13:08:02
 */
fun NavController.navigateProfileToMyInfoInMyInfoGraph() {
    this.popBackStack()
}


/**
 * [navigateProfileToUpdateNameInMyInfoGraph]
 * 프로필 화면에서 이름 수정 화면 이동
 * @since 2024-01-12 13:08:02
 */
fun NavController.navigateProfileToUpdateNameInMyInfoGraph() {
    this.navigate(Route.MY_INFO_UPDATE_NAME_ROUTER_NAME)
}

/**
 * [navigateUpdateNameToProfileInMyInfoGraph]
 * 이름 수정 후 프로필 화면으로 이동
 * @since 2024-01-12 13:08:02
 */
fun NavController.navigateUpdateNameToProfileInMyInfoGraph() {
    this.navigate(Route.MY_INFO_PROFILE_ROUTER_NAME) {
        this.popUpTo(Route.MY_INFO_PROFILE_ROUTER_NAME) {
            inclusive = true
        }
    }
}

/**
 * [navigateProfileToUpdateInfoInMyInfoGraph]
 * 프로필 화면에서 기본 정보 수정 화면 이동
 * @since 2024-01-12 13:08:02
 */
fun NavController.navigateProfileToUpdateInfoInMyInfoGraph() {
    this.navigate(Route.MY_INFO_UPDATE_INFO_ROUTER_NAME)
}

/**
 * [navigateUpdateInfoToProfileInMyInfoGraph]
 * 정보 수정 후 프로필 화면으로 이동
 * @since 2024-01-12 13:08:02
 */
fun NavController.navigateUpdateInfoToProfileInMyInfoGraph() {
    this.navigate(Route.MY_INFO_PROFILE_ROUTER_NAME) {
        this.popUpTo(Route.MY_INFO_PROFILE_ROUTER_NAME) {
            inclusive = true
        }
    }
}


/**
 * [navigateProfileToUpdateProfilePictureInMyInfoGraph]
 * 프로필 화면에서 사진 수정 화면 이동
 * @since 2024-01-12 13:08:02
 */
fun NavController.navigateProfileToUpdateProfilePictureInMyInfoGraph() {
    this.navigate(Route.MY_INFO_UPDATE_PROFILE_PICTURE_ROUTER_NAME)
}

/**
 * [navigateUpdateProfilePictureToProfileInMyInfoGraph]
 * 사진 수정 후 프로필 화면으로 이동
 * @since 2024-01-12 13:08:02
 */
fun NavController.navigateUpdateProfilePictureToProfileInMyInfoGraph() {
    this.navigate(Route.MY_INFO_PROFILE_ROUTER_NAME) {
        this.popUpTo(Route.MY_INFO_PROFILE_ROUTER_NAME) {
            inclusive = true
        }
    }
}



