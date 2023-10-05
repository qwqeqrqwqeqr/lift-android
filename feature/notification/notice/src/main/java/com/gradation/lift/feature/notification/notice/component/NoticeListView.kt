package com.gradation.lift.feature.notification.notice.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.extensions.noRippleClickable
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.model.model.notification.Notice


@Composable
fun NoticeListView(
    modifier: Modifier = Modifier,
    noticeList: List<Notice>,
    updateSelectedNotice: (Notice) -> Unit,
    navigateNotificationToNoticeDetailInNotificationGraph: () -> Unit
) {
    LazyColumn(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(noticeList) { notice ->
            Row(modifier = modifier
                .fillMaxWidth()
                .noRippleClickable {
                    updateSelectedNotice(notice)
                    navigateNotificationToNoticeDetailInNotificationGraph()
                }
                .background(LiftTheme.colorScheme.no5)
                .border(
                    width = 1.dp,
                    color = LiftTheme.colorScheme.no8,
                    shape = RoundedCornerShape(size = 12.dp)
                )
                .padding(start = 16.dp, end = 0.dp, top = 16.dp, bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween) {
                Column(
                    modifier = modifier.weight(1f),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = modifier.size(12.dp),
                            painter = painterResource(id = LiftIcon.Speaker),
                            contentDescription = "Speaker",
                            tint = Color.Unspecified
                        )
                        Text(
                            text = notice.title,
                            color = LiftTheme.colorScheme.no9,
                            style = LiftTheme.typography.no2,
                        )
                        Text(
                            text = "${notice.date.monthNumber}.${notice.date.dayOfMonth}",
                            color = LiftTheme.colorScheme.no9,
                            style = LiftTheme.typography.no4,
                        )
                    }
                    Text(
                        modifier = modifier.fillMaxWidth(),
                        text = notice.description,
                        color = LiftTheme.colorScheme.no9,
                        style = LiftTheme.typography.no4,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                IconButton(onClick = {}, modifier = modifier) {
                    Icon(
                        painter = painterResource(LiftIcon.ChevronRightLarge),
                        contentDescription = "",
                        tint = Color.Unspecified,
                    )
                }

            }
        }
    }
}