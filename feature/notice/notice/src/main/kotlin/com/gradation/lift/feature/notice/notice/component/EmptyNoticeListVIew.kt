package com.gradation.lift.feature.notice.notice.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.notice.notice.NoticeScreen
import com.gradation.lift.feature.notice.notice.data.NoticeUiState

@Composable
fun EmptyNoticeListView(modifier: Modifier = Modifier) {

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(LiftTheme.colorScheme.no17),

        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(
                painter = painterResource(id = LiftIcon.EmptyNotice),
                contentDescription = "EmptyAlarm",
                tint = Color.Unspecified
            )
            Spacer(modifier = modifier.padding(8.dp))
            Text(
                text = "등록된 공지사항이 없어요",
                color = LiftTheme.colorScheme.no2,
                style = LiftTheme.typography.no1
            )
            Text(
                text = "공지사항이 등록되면 알려드릴게요",
                color = LiftTheme.colorScheme.no2,
                style = LiftTheme.typography.no4
            )
        }


    }
}

@Preview
@Composable
fun EmptyNotificationNoticeScreenPreview() {
    LiftMaterialTheme {
        NoticeScreen(
            noticeUiState = NoticeUiState.Empty,
            updateSelectedNotice = {},
            navigateNotificationToNoticeDetailInNotificationGraph = {}
        )
    }
}