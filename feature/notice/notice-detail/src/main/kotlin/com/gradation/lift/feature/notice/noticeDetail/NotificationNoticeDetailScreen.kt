package com.gradation.lift.feature.notice.noticeDetail

import android.annotation.SuppressLint
import android.icu.text.DecimalFormat
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.gradation.lift.designsystem.component.LiftBackTopBar
import com.gradation.lift.designsystem.component.LiftButton
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.model.model.notification.Notice

@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
fun NoticeDetailRoute(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: NotificationNoticeDetailViewModel = hiltViewModel(),
) {



}

@Composable
fun NoticeDetailScreen(
    modifier: Modifier = Modifier,
    selectedNotice: Notice,
    navigateNoticeDetailToNotificationInNotificationGraph: () -> Unit,
) {
    Scaffold(
        topBar = {
            LiftBackTopBar(
                title = "공지사항",
                onBackClickTopBar = navigateNoticeDetailToNotificationInNotificationGraph
            )
        }
    ) {
        Surface(
            color = LiftTheme.colorScheme.no5, modifier = modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Column(
                modifier = modifier.fillMaxSize()
            ) {
                Column(modifier = modifier.padding(16.dp)) {
                    Text(
                        text = selectedNotice.title,
                        color = LiftTheme.colorScheme.no9,
                        style = LiftTheme.typography.no2,
                    )

                    Text(
                        text = "${selectedNotice.date.year.toString().subSequence(2, 4)}." +
                                "${DecimalFormat("00").format(selectedNotice.date.monthNumber)}." +
                                "${selectedNotice.date.dayOfMonth}",
                        color = LiftTheme.colorScheme.no9,
                        style = LiftTheme.typography.no7,
                    )
                }
                Spacer(
                    modifier = modifier
                        .height(10.dp)
                        .fillMaxWidth()
                        .background(LiftTheme.colorScheme.no1)
                )
                Column(
                    modifier = modifier
                        .padding(
                            start = 16.dp,
                            end = 16.dp,
                            top = 16.dp,
                            bottom = 64.dp
                        )
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = selectedNotice.description,
                        color = LiftTheme.colorScheme.no9,
                        style = LiftTheme.typography.no6,
                    )
                    LiftButton(
                        modifier = modifier
                            .height(36.dp)
                            .align(Alignment.CenterHorizontally),
                        shape = RoundedCornerShape(6.dp),
                        onClick = navigateNoticeDetailToNotificationInNotificationGraph,
                    ) {
                        Text(
                            text = "목록",
                            style = LiftTheme.typography.no3,
                            color = LiftTheme.colorScheme.no5,
                        )
                    }
                }
            }
        }
    }
}

