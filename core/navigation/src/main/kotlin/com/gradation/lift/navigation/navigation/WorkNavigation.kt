package com.gradation.lift.navigation.navigation

import androidx.navigation.NavController
import com.gradation.lift.navigation.Route
import com.gradation.lift.navigation.Route.WORK_COMPLETE_DETAIL_ROUTER_NAME
import com.gradation.lift.navigation.Route.WORK_COMPLETE_ROUTER_NAME


fun NavController.navigateWorkGraphToHomeGraph() {
    this.navigate(Route.HOME_GRAPH_NAME) {
        launchSingleTop = true
        popUpTo(this@navigateWorkGraphToHomeGraph.graph.id) {
            inclusive = true
        }
    }
}
/**
 * [navigateWorkToFindWorkCategoryInWorkGraph]
 * 운동 추가를 위해 운동 종목 선택 화면으로 이동
 * @since 2024-01-24 21:33:30
 */
fun NavController.navigateWorkToFindWorkCategoryInWorkGraph() {
    this.navigate(Route.WORK_FIND_WORK_CATEGORY_ROUTER_NAME)
}


/**
 * [navigateFindWorkCategoryToWorkInWorkGraph]
 * 이전화면 이동
 * @since 2024-01-24 21:33:30
 */
fun NavController.navigateFindWorkCategoryToWorkInWorkGraph() {
    this.navigate(Route.WORK_WORK_ROUTER_NAME) {
        popUpTo(Route.WORK_WORK_ROUTER_NAME)
    }
}


/**
 * [navigateFindWorkCategoryToCreateWorkSetInWorkGraph]
 * 찾은 운동 카테고리를 바탕으로 운동추가 화면으로 이동
 * @since 2024-01-24 21:33:59
 */
fun NavController.navigateFindWorkCategoryToCreateWorkSetInWorkGraph(workCategoryId: Int) {
    this.navigate("${Route.WORK_CREATE_WORK_SET_ROUTER_NAME}/${workCategoryId}")
}

/**
 * [navigateCreateWorkSetToFindWorkCategoryInWorkGraph]
 * 이전 화면 이동
 * @since 2024-01-16 10:53:04
 */
fun NavController.navigateCreateWorkSetToFindWorkCategoryInWorkGraph() {
    navigate(Route.WORK_FIND_WORK_CATEGORY_ROUTER_NAME) {
        popUpTo(Route.WORK_FIND_WORK_CATEGORY_ROUTER_NAME) { inclusive = true }
    }
}

/**
 * [navigateCreateWorkSetToWorkInWorkGraph]
 * 운동 추가를 마치고 운동 준비 화면으로 다시 이동
 * @since 2024-01-24 21:34:59
 */
fun NavController.navigateCreateWorkSetToWorkInWorkGraph() {
    this.navigate(Route.WORK_WORK_ROUTER_NAME) {
        popUpTo(Route.WORK_WORK_ROUTER_NAME)
    }
}






fun NavController.navigateWorkToCompleteInWorkGraph() {
    this.navigate(WORK_COMPLETE_ROUTER_NAME) {
        popUpTo(WORK_COMPLETE_ROUTER_NAME) {
            inclusive = true
        }
    }
}

fun NavController.navigateCompleteToCompleteDetailInWorkGraph() {
    this.navigate(WORK_COMPLETE_DETAIL_ROUTER_NAME) {
        popUpTo(WORK_COMPLETE_DETAIL_ROUTER_NAME) {
            inclusive = true
        }
    }
}





