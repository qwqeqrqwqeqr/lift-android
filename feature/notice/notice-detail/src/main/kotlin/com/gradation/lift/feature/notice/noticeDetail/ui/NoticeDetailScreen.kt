package com.gradation.lift.feature.notice.noticeDetail.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.component.topBar.LiftTopBar
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.notice.noticeDetail.data.NoticeUiState
import com.gradation.lift.ui.mapper.toDateText


@Composable
fun NoticeDetailScreen(
    modifier: Modifier = Modifier,
    noticeUiState: NoticeUiState,
    navigateNoticeDetailToNoticeInNoticeGraph: () -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            LiftTopBar(
                modifier = modifier,
                backgroundColor = LiftTheme.colorScheme.no5,
                title = "공지사항",
                onClick = navigateNoticeDetailToNoticeInNoticeGraph
            )
        },
    ) { it ->
        when (noticeUiState) {
            is NoticeUiState.Fail -> Spacer(
                modifier = modifier
                    .fillMaxSize()
                    .background(LiftTheme.colorScheme.no5)
            )

            NoticeUiState.Loading -> Spacer(
                modifier = modifier
                    .fillMaxSize()
                    .background(LiftTheme.colorScheme.no5)
            )

            is NoticeUiState.Success -> {
                Column(
                    modifier = modifier
                        .padding(it)
                        .fillMaxSize()
                        .background(LiftTheme.colorScheme.no5)
                        .padding(
                            top = LiftTheme.space.space16,
                            start = LiftTheme.space.space20,
                            end = LiftTheme.space.space20,
                        ),
                    verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space24)
                ) {
                    Column(
                        modifier = modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
                    ) {
                        LiftText(
                            textStyle = LiftTextStyle.No1,
                            text = noticeUiState.notice.title,
                            color = LiftTheme.colorScheme.no9,
                            textAlign = TextAlign.Start
                        )
                        LiftText(
                            textStyle = LiftTextStyle.No8,
                            text = noticeUiState.notice.date.toDateText(),
                            color = LiftTheme.colorScheme.no9,
                            textAlign = TextAlign.Start
                        )
                    }
                    LiftText(
                        textStyle = LiftTextStyle.No6,
                        text = noticeUiState.notice.description,
                        color = LiftTheme.colorScheme.no9,
                        textAlign = TextAlign.Start
                    )
                }
            }
        }
    }
}

