package com.gradation.lift.navigation.navigation

import androidx.navigation.*
import com.gradation.lift.navigation.Route
import com.gradation.lift.navigation.Route.HOME_GRAPH_NAME


/**
 * [navigateLoginGraphToRegisterDetailGraph]
 * 로그인시 홈화면으로 이동
 * @since 2023-12-24 20:37:04
 */
fun NavController.navigateLoginGraphToHomeGraph() {
    this.navigate(HOME_GRAPH_NAME) {
        launchSingleTop = true
        popUpTo(Route.LOGIN_GRAPH_NAME) {
            inclusive = true
        }
    }
}

/**
 * [navigateLoginGraphToRegisterDetailGraph]
 * 최초 회원가입한 회원일 경우 상세정보 등록 화면으로 이동
 * @since 2023-12-24 20:37:08
 */
fun NavController.navigateLoginGraphToRegisterDetailGraph() {
    this.navigate(Route.REGISTER_DETAIL_GRAPH_NAME) {
        launchSingleTop = true
        popUpTo(Route.LOGIN_GRAPH_NAME) {
            inclusive = true
        }
    }
}


/**
 * [navigateSignInToTermsOfUseInLoginGraph]
 * 이메일로 시작하기(일반 로그인) 화면으로 이동
 * @since 2023-12-24 20:37:12
 */
fun NavController.navigateSignInToSignInDefaultInLoginGraph() {
    this.navigate(Route.LOGIN_SIGN_IN_DEFAULT_ROUTER_NAME)
}

/**
 * [navigateSignInToSignUpCreateEmailDefaultInLoginGraph]
 * 일반 로그인으로 진행할 시에 필요한 회원가입을 위해 해당 목적지로 이동
 * @since 2023-12-24 20:37:12
 */
fun NavController.navigateSignInToSignUpCreateEmailDefaultInLoginGraph() {
    this.navigate(Route.LOGIN_SIGN_UP_CREATE_EMAIL_ROUTER_NAME)
}

/**
 * [navigateSignInToTermsOfUseInLoginGraph]
 * OAuth 로그인을 진행할 때, 최초로 가입한 사용자 일 경우 해당 목적지로 이동
 * @since 2023-12-24 20:34:57
 */
fun NavController.navigateSignInToTermsOfUseInLoginGraph() {
    this.navigate(Route.LOGIN_TERMS_OF_USE_ROUTER_NAME)
}


/**
 * [navigateSignInDefaultToVerifyEmailInLoginGraph]
 * 비밀번호를 잊어 버렸을 때 비밀번호를 찾기 위해 이메일 인증을 진행하는 화면으로 이동
 * @since 2023-12-24 20:34:57
 */
fun NavController.navigateSignInDefaultToVerifyEmailInLoginGraph() {
    this.navigate(Route.LOGIN_VERIFY_EMAIL_ROUTER_NAME)
}


/**
 * [navigateSignInDefaultToSignInInLoginGraph]
 * 이전 화면(로그인 화면) 이동
 * @since 2023-12-24 20:34:57
 */
fun NavController.navigateSignInDefaultToSignInInLoginGraph() {
    this.popBackStack()
}


/**
 * [navigateSignUpCreateEmailToSignUpCreatePasswordInLoginGraph]
 * 이메일 인증을 마치고 비밀번호 설정 화면으로 이동
 * @since 2023-12-29 17:56:20
 */
fun NavController.navigateSignUpCreateEmailToSignUpCreatePasswordInLoginGraph() {
    this.navigate(Route.LOGIN_SIGN_UP_CREATE_PASSWORD_ROUTER_NAME)
}

/**
 * [navigateSignUpCreatePasswordToTermsOfUseInLoginGraph]
 * 비밀번호 설정을 마치고 약관동의 화면으로 이동
 * @since 2023-12-29 17:56:20
 */
fun NavController.navigateSignUpCreatePasswordToTermsOfUseInLoginGraph() {
    this.navigate(Route.LOGIN_TERMS_OF_USE_ROUTER_NAME)
}


/**
 * [navigateSignUpToSignInInLoginGraph]
 * 회원가입 절차를 취소하고 로그인화면으로 이동
 * @since 2023-12-29 17:56:57
 */
fun NavController.navigateSignUpToSignInInLoginGraph() {
    this.navigate(Route.LOGIN_SIGN_IN_ROUTER_NAME) {
        popUpTo(Route.LOGIN_SIGN_IN_ROUTER_NAME) {
            inclusive = true
        }
    }
}


/**
 * [navigateTermsOfUseToTermsOfUseDetailInLoginGraph]
 * 상세 약관 페이지로 이동
 * @since 2023-12-24 20:34:57
 */
fun NavController.navigateTermsOfUseToTermsOfUseDetailInLoginGraph() {
    this.navigate(Route.LOGIN_TERMS_OF_USE_DETAIL_ROUTER_NAME)
}


/**
 * [navigateTermsOfUseToSignInInLoginGraph]
 * 초기 로그인 화면으로 되돌어가기(이전화면 이동)
 * @since 2023-12-24 21:01:03
 */
fun NavController.navigateTermsOfUseToSignInInLoginGraph() {
    this.navigate(Route.LOGIN_SIGN_IN_ROUTER_NAME) {
        popUpTo(Route.LOGIN_SIGN_IN_ROUTER_NAME) {
            inclusive = true
        }
    }
}


/**
 * [navigateTermsOfUseDetailToTermsOfUseInLoginGraph]
 * 상세 약관 페이지로 이동
 * @since 2023-12-24 20:34:57
 */
fun NavController.navigateTermsOfUseDetailToTermsOfUseInLoginGraph() {
    popBackStack()
}


/**
 * [navigateVerifyEmailToSignInDefaultInLoginGraph]
 * 이메일 인증을 완료한 뒤, 비밀번호 재설정 화면으로 이동
 * @since 2023-12-24 20:34:57
 */
fun NavController.navigateVerifyEmailToResetPasswordInLoginGraph() {
    this.navigate(Route.LOGIN_RESET_PASSWORD_ROUTER_NAME)
}

/**
 * [navigateVerifyEmailToSignInDefaultInLoginGraph]
 * 이전 화면으로 이동
 * @since 2023-12-24 20:34:57
 */
fun NavController.navigateVerifyEmailToSignInDefaultInLoginGraph() {
    popBackStack()
}


/**
 * [navigateResetPasswordToSignInDefaultInLoginGraph]
 * 비밀번호 재설정을 완료하거나, 비밀번호 재설정을 하고싶지않아 이전화면으로 돌아갈 때 둘다 일반로그인 목적지로 이동
 * @since 2023-12-24 21:06:58
 */
fun NavController.navigateResetPasswordToSignInDefaultInLoginGraph() {
    this.navigate(Route.LOGIN_SIGN_IN_DEFAULT_ROUTER_NAME) {
        popUpTo(Route.LOGIN_SIGN_IN_DEFAULT_ROUTER_NAME) {
            inclusive = true
        }
    }
}
