package com.gradation.lift.navigation.navigation

import androidx.navigation.*
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.Router.HOME_GRAPH_NAME


/**
 * [navigateLoginGraphToRegisterDetailGraph]
 * 로그인시 홈화면으로 이동
 * @since 2023-12-24 20:37:04
 */
fun NavController.navigateLoginGraphToHomeGraph() {
    this.navigate(HOME_GRAPH_NAME) {
        launchSingleTop = true
        popUpTo(Router.LOGIN_GRAPH_NAME) {
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
    this.navigate(Router.REGISTER_DETAIL_GRAPH_NAME) {
        launchSingleTop = true
        popUpTo(Router.LOGIN_GRAPH_NAME) {
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
    this.navigate(Router.LOGIN_SIGN_IN_DEFAULT_ROUTER_NAME)
}

/**
 * [navigateSignInToSignUpDefaultInLoginGraph]
 * 일반 로그인으로 진행할 시에 필요한 회원가입을 위해 해당 목적지로 이동
 * @since 2023-12-24 20:37:12
 */
fun NavController.navigateSignInToSignUpDefaultInLoginGraph() {
    this.navigate(Router.LOGIN_SIGN_UP_DEFAULT_ROUTER_NAME)
}

/**
 * [navigateSignInToTermsOfUseInLoginGraph]
 * OAuth 로그인을 진행할 때, 최초로 가입한 사용자 일 경우 해당 목적지로 이동
 * @since 2023-12-24 20:34:57
 */
fun NavController.navigateSignInToTermsOfUseInLoginGraph() {
    this.navigate(Router.LOGIN_SIGN_IN_DEFAULT_ROUTER_NAME)
}


/**
 * [navigateSignInDefaultToVerifyEmailInLoginGraph]
 * 비밀번호를 잊어 버렸을 때 비밀번호를 찾기 위해 이메일 인증을 진행하는 화면으로 이동
 * @since 2023-12-24 20:34:57
 */
fun NavController.navigateSignInDefaultToVerifyEmailInLoginGraph() {
    this.navigate(Router.LOGIN_VERIFY_EMAIL_ROUTER_NAME)
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
 * [navigateSignUpDefaultToTermsOfUseInLoginGraph]
 * 일반 회원가입을 진행하고 약관동의 화면으로 이동
 * @since 2023-12-24 20:34:57
 */
fun NavController.navigateSignUpDefaultToTermsOfUseInLoginGraph() {
    this.navigate(Router.LOGIN_TERMS_OF_USE_ROUTER_NAME)
}


/**
 * [navigateSignUpDefaultToSignInInLoginGraph]
 * 이전 화면(로그인 화면) 이동
 * @since 2023-12-24 20:34:57
 */
fun NavController.navigateSignUpDefaultToSignInInLoginGraph() {
    this.popBackStack()
}


/**
 * [navigateTermsOfUseToTermsOfUseDetailInLoginGraph]
 * 상세 약관 페이지로 이동
 * @since 2023-12-24 20:34:57
 */
fun NavController.navigateTermsOfUseToTermsOfUseDetailInLoginGraph() {
    this.navigate(Router.LOGIN_TERMS_OF_USE_DETAIL_ROUTER_NAME)
}

/**
 * [navigateTermsOfUseToCompleteInLoginGraph]
 * 회원가입 완료화면으로 이동
 * @since 2023-12-24 21:01:39
 */
fun NavController.navigateTermsOfUseToCompleteInLoginGraph() {
    this.navigate(Router.LOGIN_COMPLETE_ROUTER_NAME)
}


/**
 * [navigateTermsOfUseToSignInInLoginGraph]
 * 초기 로그인 화면으로 되돌어가기(이전화면 이동)
 * @since 2023-12-24 21:01:03
 */
fun NavController.navigateTermsOfUseToSignInInLoginGraph() {
    this.navigate(Router.LOGIN_SIGN_IN_ROUTER_NAME) {
        popUpTo(Router.LOGIN_SIGN_IN_ROUTER_NAME) {
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
    this.navigate(Router.LOGIN_TERMS_OF_USE_ROUTER_NAME)
}


/**
 * [navigateCompleteToSignInInLoginGraph]
 * 회원가입을 완료하여 초기 로그인화면으로 이동
 * @since 2023-12-24 21:04:00
 */
fun NavController.navigateCompleteToSignInInLoginGraph() {
    this.navigate(Router.LOGIN_SIGN_IN_ROUTER_NAME) {
        popUpTo(Router.LOGIN_SIGN_IN_ROUTER_NAME) {
            inclusive = true
        }
    }
}



/**
 * [navigateVerifyEmailToSignInDefaultInLoginGraph]
 * 이메일 인증을 완료한 뒤, 비밀번호 재설정 화면으로 이동
 * @since 2023-12-24 20:34:57
 */
fun NavController.navigateVerifyEmailToResetPasswordInLoginGraph() {
    this.navigate(Router.LOGIN_RESET_PASSWORD_ROUTER_NAME)
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
    this.navigate(Router.LOGIN_SIGN_IN_DEFAULT_ROUTER_NAME) {
        popUpTo(Router.LOGIN_SIGN_IN_DEFAULT_ROUTER_NAME) {
            inclusive = true
        }
    }
}
