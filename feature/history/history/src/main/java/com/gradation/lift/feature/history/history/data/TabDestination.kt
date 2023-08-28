package com.gradation.lift.feature.history.history.data

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import com.gradation.lift.feature.history.daily_log.HistoryDailyLogRoute
import com.gradation.lift.feature.history.analytics.HistoryAnalyticsRoute

enum class TabDestination(
    val title: String = "",
    val screen: @Composable () -> Unit,
) {
    @RequiresApi(Build.VERSION_CODES.O)
    Analytics(
        title = "운동분석",
        screen = { HistoryAnalyticsRoute() }
    ),
    DailyLog(
        title = "운동일지",
        screen = { HistoryDailyLogRoute() }
    )
}
