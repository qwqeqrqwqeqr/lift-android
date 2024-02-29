package com.gradation.lift.navigation.navigation

import androidx.navigation.NavController
import com.gradation.lift.navigation.Route.WORK_GRAPH_NAME
import com.gradation.lift.navigation.Route.WORK_READY_CREATE_WORK_SET_ROUTER_NAME
import com.gradation.lift.navigation.Route.WORK_READY_FIND_WORK_CATEGORY_ROUTER_NAME
import com.gradation.lift.navigation.Route.WORK_READY_READY_ROUTER_NAME
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey.Work.WORK_ROUTINE_SET_ID_LIST_KEY

/**
 * [navigateRoutineSelectionToReadyInWorkReadyGraph]
 * 선택된 운동들을 가지고 운동 준비 화면으로 이동
 * @since 2024-01-16 10:54:22
 */
fun NavController.navigateRoutineSelectionToReadyInWorkReadyGraph() {
    val emptyString = ""
    this.navigate("${WORK_READY_READY_ROUTER_NAME}?${WORK_ROUTINE_SET_ID_LIST_KEY}=$emptyString")
}

/**
 * [navigateReadyToFindWorkCategoryInWorkReadyGraph]
 * 운동 추가를 위해 운동 종목 선택 화면으로 이동
 * @since 2024-01-16 10:53:04
 */
fun NavController.navigateReadyToFindWorkCategoryInWorkReadyGraph() {
    this.navigate(WORK_READY_FIND_WORK_CATEGORY_ROUTER_NAME)
}


/**
 * [navigateFindWorkCategoryToReadyInWorkReadyGraph]
 * 이전화면 이동
 * @since 2024-01-16 10:53:04
 */
fun NavController.navigateFindWorkCategoryToReadyInWorkReadyGraph() {
    popBackStack()
}


/**
 * [navigateFindWorkCategoryToCreateWorkSetInWorkReadyGraph]
 * 찾은 운동 카테고리를 바탕으로 운동추가 화면으로 이동
 * @since 2024-01-16 10:52:37
 */
fun NavController.navigateFindWorkCategoryToCreateWorkSetInWorkReadyGraph(workCategoryId: Int) {
    this.navigate("$WORK_READY_CREATE_WORK_SET_ROUTER_NAME/${workCategoryId}")
}

/**
 * [navigateCreateWorkSetToFindWorkCategoryInWorkReadyGraph]
 * 이전 화면 이동
 * @since 2024-01-16 10:53:04
 */
fun NavController.navigateCreateWorkSetToFindWorkCategoryInWorkReadyGraph() {
    navigate(WORK_READY_FIND_WORK_CATEGORY_ROUTER_NAME) {
        popUpTo(WORK_READY_FIND_WORK_CATEGORY_ROUTER_NAME) { inclusive = true }
    }
}

/**
 * [navigateCreateWorkSetToReadyInWorkReadyGraph]
 * 운동 추가를 마치고 운동 준비 화면으로 다시 이동
 * @since 2024-01-16 10:57:20
 */
fun NavController.navigateCreateWorkSetToReadyInWorkReadyGraph() {
    this.navigate(WORK_READY_READY_ROUTER_NAME) {
        popUpTo(WORK_READY_READY_ROUTER_NAME) {
            inclusive = true
        }
    }
}


fun NavController.navigateWorkReadyGraphToWorkGraph() {
    this.navigate(WORK_GRAPH_NAME) {
        this.popUpTo(WORK_GRAPH_NAME) {
            inclusive = true
        }
    }
}


