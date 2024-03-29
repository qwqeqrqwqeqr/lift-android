package com.gradation.lift.navigation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.gradation.lift.navigation.Route
import com.gradation.lift.navigation.Route.ANALYTICS_GRAPH_NAME
import com.gradation.lift.navigation.Route.BADGE_BADGE_ROUTER_NAME
import com.gradation.lift.navigation.Route.HISTORY_GRAPH_NAME
import com.gradation.lift.navigation.Route.HOME_GRAPH_NAME
import com.gradation.lift.navigation.Route.ROUTINE_DETAIL_ROUTINE_ROUTER_NAME
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey.Badge.BADGE_PAGE_KEY


fun NavController.navigateHomeGraphToWorkReadyReadyRouter() {
    val emptyString = ""
    this.navigate("${Route.WORK_READY_READY_ROUTER_NAME}?${SavedStateHandleKey.Work.WORK_ROUTINE_SET_ID_LIST_KEY}=$emptyString")
}

fun NavController.navigateHomeGraphToWorkReadyRoutineSelectionRouter() {
    this.navigate(Route.WORK_READY_ROUTINE_SELECTION_ROUTER_NAME)
}

fun NavController.navigateHomeGraphToCreateRoutineGraph() {
    this.navigate(Route.CREATE_ROUTINE_GRAPH_NAME)
}

fun NavController.navigateHomeGraphToRoutineDetailGraph() {
    this.navigate(Route.ROUTINE_DETAIL_GRAPH_NAME)
}

fun NavController.navigateHomeGraphToRoutineDetailRoutineRouter(routineSetId: Int) {
    this.navigate("$ROUTINE_DETAIL_ROUTINE_ROUTER_NAME/$routineSetId")
}

fun NavController.navigateHomeGraphToBadgeBadgeRouter(page: Int) {
    navigate("$BADGE_BADGE_ROUTER_NAME?$BADGE_PAGE_KEY=$page")
}

fun NavController.navigateHomeGraphToInquiryGraph() {
    this.navigate(Route.INQUIRY_GRAPH_NAME)
}

fun NavController.navigateHomeToBadgeInHomeGraph() {
    this.navigate(Route.HOME_BADGE_ROUTER_NAME)
}


fun NavController.navigateHomeGraphToMyinfoProfileRouter() {
    this.navigate(Route.MY_INFO_PROFILE_ROUTER_NAME)
}

fun NavController.navigateHomeGraphToWorkGraph() {
    this.navigate(Route.WORK_GRAPH_NAME)
}


fun NavController.navigateBadgeToHomeInHomeGraph() {
    this.navigate(HOME_GRAPH_NAME) {
        launchSingleTop = true
        popUpTo(this@navigateBadgeToHomeInHomeGraph.graph.id) {
            inclusive = true
        }
    }
}


fun NavHostController.navigateHomeGraph() {
    this.navigate(HOME_GRAPH_NAME) {
        launchSingleTop = true
        popUpTo(currentDestination!!.id) {
            saveState = true
            inclusive = true
        }
    }
}


fun NavController.navigateMyInfoGraph() {
    this.navigate(Route.MY_INFO_GRAPH_NAME) {
        launchSingleTop = true
        popUpTo(currentDestination!!.id) {
            saveState = true
            inclusive = true
        }
    }
}

fun NavHostController.navigateHistoryGraph() {
    this.navigate(HISTORY_GRAPH_NAME) {
        launchSingleTop = true
        popUpTo(currentDestination!!.id) {
            saveState = true
            inclusive = true
        }
    }
}

fun NavHostController.navigateAnalyticsGraph() {
    this.navigate(ANALYTICS_GRAPH_NAME) {
        launchSingleTop = true
        popUpTo(currentDestination!!.id) {
            saveState = true
            inclusive = true
        }
    }
}





