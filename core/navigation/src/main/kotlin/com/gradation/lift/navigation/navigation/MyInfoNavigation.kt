package com.gradation.lift.navigation.navigation

import androidx.navigation.NavController
import com.gradation.lift.navigation.Route
import com.gradation.lift.navigation.Route.MY_INFO_CANCEL_MEMBERSHIP_CONFIRM_ROUTER_NAME
import com.gradation.lift.navigation.Route.MY_INFO_CANCEL_MEMBERSHIP_ROUTER_NAME
import com.gradation.lift.navigation.Route.MY_INFO_TERMS_POLICY_DETAIL_ROUTER_NAME
import com.gradation.lift.navigation.Route.MY_INFO_TERMS_POLICY_ROUTER_NAME
import com.gradation.lift.navigation.Route.MY_INFO_UPDATE_INFO_ROUTER_NAME
import com.gradation.lift.navigation.Route.MY_INFO_UPDATE_NAME_ROUTER_NAME
import com.gradation.lift.navigation.Route.MY_INFO_UPDATE_PROFILE_PICTURE_ROUTER_NAME

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
 * [navigateMyInfoToTermsPolicyInMyInfoGraph]
 * 약관 정책 화면으로 이동
 * @since 2024-02-08 17:07:51
 */
fun NavController.navigateMyInfoToTermsPolicyInMyInfoGraph() {
    this.navigate(MY_INFO_TERMS_POLICY_ROUTER_NAME)
}

/**
 * [navigateMyInfoToTermsPolicyInMyInfoGraph]
 * 약관 정책 화면에서 내정보 화면으로 이동
 * @since 2024-02-08 17:07:51
 */
fun NavController.navigateTermsPolicyToMyInfoInMyInfoGraph() {
    this.popBackStack()
}

/**
 * [navigateMyInfoToTermsPolicyInMyInfoGraph]
 * 약관 정책 화면에서 상세화면으로 이동
 * @since 2024-02-08 17:07:51
 */
fun NavController.navigateTermsPolicyToTermsPolicyDetailMyInfoGraph(
    title: String,
    content: String,
) {
    this.navigate("$MY_INFO_TERMS_POLICY_DETAIL_ROUTER_NAME/$title/$content")
}

/**
 * [navigateMyInfoToTermsPolicyInMyInfoGraph]
 * 상세 화면에서 이전 화면으로 이동
 * @since 2024-02-08 17:07:51
 */
fun NavController.navigateTermsPolicyDetailToTermsPolicyMyInfoGraph() {
    this.popBackStack()
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
fun NavController.navigateProfileToUpdateNameInMyInfoGraph(name: String) {
    this.navigate("$MY_INFO_UPDATE_NAME_ROUTER_NAME/${name}")
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
fun NavController.navigateProfileToUpdateInfoInMyInfoGraph(
    gender: String,
    height: Float,
    weight: Float,
) {
    this.navigate("$MY_INFO_UPDATE_INFO_ROUTER_NAME/$gender/$height/$weight")
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
fun NavController.navigateProfileToUpdateProfilePictureInMyInfoGraph(profilePicture: String) {
    this.navigate("$MY_INFO_UPDATE_PROFILE_PICTURE_ROUTER_NAME/$profilePicture")
}


/**
 * [navigateUpdateProfilePictureToProfileInMyInfoGraph]
 * 사진 수정 후 프로필 화면으로 이동
 * @since 2024-01-12 13:08:02
 */
fun NavController.navigateUpdateProfilePictureToProfileInMyInfoGraph() {
    navigate(Route.MY_INFO_PROFILE_ROUTER_NAME) {
        popUpTo(Route.MY_INFO_PROFILE_ROUTER_NAME) {
            inclusive = true
        }
    }
}


/**
 * [navigateProfileToUpdateProfilePictureInMyInfoGraph]
 * 프로필 화면에서 회원 탈퇴 화면으로 이동
 * @since 2024-02-19 17:24:33
 */
fun NavController.navigateProfileToCancelMembershipInMyInfoGraph() {
    this.navigate(MY_INFO_CANCEL_MEMBERSHIP_ROUTER_NAME)
}

/**
 * [navigateProfileToUpdateProfilePictureInMyInfoGraph]
 * 회원 탈퇴 화면에서 프로필 화면으로 이동
 * @since 2024-02-19 17:24:33
 */
fun NavController.navigateCancelMembershipToProfileInMyInfoGraph() {
    popBackStack()
}

/**
 * [navigateProfileToUpdateProfilePictureInMyInfoGraph]
 * 회원 탈퇴 화면에서 회원 탈퇴 확인 화면으로 이동
 * @since 2024-02-19 17:24:33
 */
fun NavController.navigateCancelMembershipToCancelMembershipConfirmInMyInfoGraph(reason: String) {
    this.navigate("$MY_INFO_CANCEL_MEMBERSHIP_CONFIRM_ROUTER_NAME/$reason")
}

/**
 * [navigateProfileToUpdateProfilePictureInMyInfoGraph]
 * 회원 탈퇴 확인 화면에서 회원 탈퇴 화면으로 이동
 * @since 2024-02-19 17:24:33
 */
fun NavController.navigateCancelMembershipConfirmToCancelMembershipInMyInfoGraph() {
    popBackStack()
}