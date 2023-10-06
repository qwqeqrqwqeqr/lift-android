package com.gradation.lift.feature.notification.notification.data

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.gradation.lift.feature.notification.notice.NotificationNoticeRoute
import com.gradation.lift.feature.notification.push.NotificationPushRoute

sealed class TabDestination(
    val title: String,
    val screen: @Composable (NavController) -> Unit,
) {
    class Push(
        title: String = "활동알림",
        screen: @Composable (NavController) -> Unit = { NotificationPushRoute(navController = it) }
    ) : TabDestination(title, screen)

    class Notice(
        title: String = "공지사항",
        screen: @Composable (NavController) -> Unit = { NotificationNoticeRoute(navController = it) }
    ) : TabDestination(title, screen)

}
