package com.gradation.lift.feature.notice.notice

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.component.container.LiftDefaultContainer
import com.gradation.lift.designsystem.component.label.NoticeCategoryDefaultLabel
import com.gradation.lift.designsystem.component.label.NoticeCategoryUpdateLabel
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.component.topBar.LiftTopBar
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.notice.notice.data.NoticeUiState
import com.gradation.lift.ui.mapper.toDateText
import com.gradation.lift.ui.modifier.noRippleClickable


@Composable
fun NoticeScreen(
    modifier: Modifier,
    noticeUiState: NoticeUiState,
    navigateNotificationToNoticeDetailInNotificationGraph: () -> Unit,
    navigateNoticeToNoticeDetailInNoticeGraph: (Int) -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            LiftTopBar(
                modifier = modifier,
                backgroundColor = LiftTheme.colorScheme.no5,
                title = "공지사항",
                onClick = navigateNotificationToNoticeDetailInNotificationGraph
            )
        },
    ) { it ->
        when (noticeUiState) {
            NoticeUiState.Empty -> Column(
                modifier = modifier
                    .fillMaxSize()
                    .background(LiftTheme.colorScheme.no17)
                    .padding(
                        start = LiftTheme.space.space20,
                        end = LiftTheme.space.space20,
                    )
                    .padding(it),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(
                    LiftTheme.space.space12,
                    Alignment.CenterVertically
                )
            ) {
                Icon(
                    painter = painterResource(id = LiftIcon.EmptyNotice),
                    contentDescription = "emptyNotice",
                    tint = Color.Unspecified
                )
                Column(
                    modifier = modifier,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
                ) {
                    LiftText(
                        textStyle = LiftTextStyle.No1,
                        text = "등록된 공지사항이 존재하지 않네요.",
                        color = LiftTheme.colorScheme.no2,
                        textAlign = TextAlign.Center
                    )
                    LiftText(
                        textStyle = LiftTextStyle.No4,
                        text = "공지사항이 등록되면 알려드릴게요.",
                        color = LiftTheme.colorScheme.no2,
                        textAlign = TextAlign.Center
                    )
                }
            }

            NoticeUiState.Loading ->
                Spacer(
                    modifier = modifier
                        .fillMaxSize()
                        .background(LiftTheme.colorScheme.no17)
                )

            is NoticeUiState.Success -> LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .background(LiftTheme.colorScheme.no17)
                    .padding(it)
                    .padding(
                        start = LiftTheme.space.space20,
                        end = LiftTheme.space.space20,
                        top = LiftTheme.space.space24
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space12)
            ) {
                items(noticeUiState.noticeList) {
                    LiftDefaultContainer(
                        modifier = modifier
                            .fillMaxWidth()
                            .noRippleClickable { navigateNoticeToNoticeDetailInNoticeGraph(it.id) },
                        verticalPadding = LiftTheme.space.space8,
                        horizontalPadding = LiftTheme.space.space16,
                    ) {
                        Column(
                            modifier = modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
                        ) {
                            Row(
                                modifier = modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                when (it.category) {
                                    "업데이트" -> NoticeCategoryUpdateLabel(modifier)
                                    else -> NoticeCategoryDefaultLabel(modifier)
                                }
                                LiftText(
                                    textStyle = LiftTextStyle.No8,
                                    text = it.date.toDateText(),
                                    color = LiftTheme.colorScheme.no9,
                                    textAlign = TextAlign.Start
                                )
                            }
                            LiftText(
                                textStyle = LiftTextStyle.No3,
                                text = it.title,
                                color = LiftTheme.colorScheme.no9,
                                textAlign = TextAlign.Start
                            )
                        }
                    }
                }
            }
        }
    }
}
