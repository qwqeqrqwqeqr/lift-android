package com.gradation.lift.feature.myInfo.myInfo.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.myInfo.myInfo.data.model.MyInfoContent
import com.gradation.lift.feature.myInfo.myInfo.data.state.MyInfoScreenState
import com.gradation.lift.ui.modifier.noRippleClickable

@Composable
fun MyInfoListView(
    modifier: Modifier = Modifier,
    navigateMyInfoGraphToNoticeGraph: () -> Unit,
    navigateToOssScreen: () -> Unit,
    myInfoScreenState: MyInfoScreenState,
) {
    val myInfoContentList = listOf(
        MyInfoContent(
            LiftIcon.Speaker,
            "공지사항",
            navigateMyInfoGraphToNoticeGraph
        ),
        MyInfoContent(
            LiftIcon.License,
            "오픈소스 라이센스",
            navigateToOssScreen
        ),
        MyInfoContent(
            LiftIcon.LiftSmallLogo,
            "현재 버전 ${myInfoScreenState.versionName}",
            null,
        )
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(LiftTheme.colorScheme.no5)
    ) {
        myInfoContentList.forEach {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = LiftTheme.space.space16,
                        horizontal = LiftTheme.space.space20
                    )
                    .noRippleClickable {
                        it.navigate?.let { navigate -> navigate() }
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = modifier,
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space12)
                ) {
                    Icon(
                        modifier = modifier.size(LiftTheme.space.space18),
                        painter = painterResource(id = it.icon),
                        contentDescription = "${it.icon}",
                        tint = Color.Unspecified
                    )
                    LiftText(
                        modifier = modifier,
                        textStyle = LiftTextStyle.No3,
                        text = it.content,
                        color = LiftTheme.colorScheme.no3,
                        textAlign = TextAlign.Start
                    )
                }
                if (it.navigate != null) {
                    Icon(
                        modifier = modifier.size(LiftTheme.space.space12),
                        painter = painterResource(id = LiftIcon.ChevronRightLarge),
                        contentDescription = "chevronRightLarge",
                        tint = LiftTheme.colorScheme.no2
                    )
                }
            }
        }
    }
}


