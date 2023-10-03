package com.gradation.lift.feature.notification.notification.data

import androidx.compose.runtime.Composable
import com.gradation.lift.feature.notification.notice.NotificationNoticeRoute
import com.gradation.lift.feature.notification.push.NotificationPushRoute

sealed class TabDestination(
    val title: String,
    val screen: @Composable () -> Unit,
) {
    class Push(
        title: String = "활동알림",
        screen: @Composable () -> Unit = { NotificationPushRoute() }
    ) : TabDestination(title, screen)

    class Notice(
        title: String = "공지사항",
        screen: @Composable () -> Unit = { NotificationNoticeRoute() }
    ) : TabDestination(title, screen)

}
