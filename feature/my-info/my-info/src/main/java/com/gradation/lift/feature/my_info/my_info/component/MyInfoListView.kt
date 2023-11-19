package com.gradation.lift.feature.my_info.my_info.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.gradation.lift.ui.extensions.noRippleClickable
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
fun MyInfoListView(
    modifier: Modifier = Modifier,
    versionName: String,
    navigateMyInfoToUpdateInMyInfoGraph: () -> Unit,
    navigateMyInfoGraphToNotificationGraph: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                LiftTheme.colorScheme.no5,
                shape = RoundedCornerShape(18.dp, 18.dp, 18.dp, 18.dp)
            )
    ) {
        Row(
            modifier = modifier
                .padding(16.dp)
                .fillMaxWidth()
                .noRippleClickable { navigateMyInfoToUpdateInMyInfoGraph() },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    modifier = modifier.width(24.dp),
                    painter = painterResource(id = LiftIcon.Kettlebell),
                    contentDescription = "kettlebell",
                    tint = Color.Unspecified
                )
                Spacer(modifier = modifier.padding(8.dp))
                Column {
                    Text(
                        text = "내 정보 수정",
                        color = LiftTheme.colorScheme.no11,
                        style = LiftTheme.typography.no3
                    )
                    Text(
                        text = "기존에 설정한 정보를 변경합니다.",
                        color = LiftTheme.colorScheme.no2,
                        style = LiftTheme.typography.no6
                    )
                }
            }
            Icon(
                modifier = modifier,
                painter = painterResource(id = LiftIcon.ChevronRightLarge),
                contentDescription = "kettlebell",
                tint = LiftTheme.colorScheme.no2
            )
        }
        HorizontalDivider(
            modifier = modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = LiftTheme.colorScheme.no17
        )
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .noRippleClickable { navigateMyInfoGraphToNotificationGraph() },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    modifier = modifier.width(24.dp),
                    painter = painterResource(id = LiftIcon.Speaker),
                    contentDescription = "speaker",
                    tint = Color.Unspecified
                )
                Spacer(modifier = modifier.padding(8.dp))
                Column {
                    Text(
                        text = "공지사항",
                        color = LiftTheme.colorScheme.no11,
                        style = LiftTheme.typography.no3
                    )
                }
            }
            Icon(
                modifier = modifier,
                painter = painterResource(id = LiftIcon.ChevronRightLarge),
                contentDescription = "kettlebell",
                tint = LiftTheme.colorScheme.no2
            )
        }
        HorizontalDivider(
            modifier = modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = LiftTheme.colorScheme.no17
        )
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    modifier = modifier.width(24.dp),
                    painter = painterResource(id = LiftIcon.Chat),
                    contentDescription = "chat",
                    tint = Color.Unspecified
                )
                Spacer(modifier = modifier.padding(8.dp))
                Column {
                    Text(
                        text = "고객센터",
                        color = LiftTheme.colorScheme.no11,
                        style = LiftTheme.typography.no3
                    )
                }
            }
            Icon(
                modifier = modifier,
                painter = painterResource(id = LiftIcon.ChevronRightLarge),
                contentDescription = "kettlebell",
                tint = LiftTheme.colorScheme.no2
            )
        }
        HorizontalDivider(
            modifier = modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = LiftTheme.colorScheme.no17
        )
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    modifier = modifier.width(24.dp),
                    painter = painterResource(id = LiftIcon.TermsPolicies),
                    contentDescription = "terms_policies",
                    tint = Color.Unspecified
                )
                Spacer(modifier = modifier.padding(8.dp))
                Column {
                    Text(
                        text = "약관 및 정책",
                        color = LiftTheme.colorScheme.no11,
                        style = LiftTheme.typography.no3
                    )
                }
            }
            Icon(
                modifier = modifier,
                painter = painterResource(id = LiftIcon.ChevronRightLarge),
                contentDescription = "kettlebell",
                tint = LiftTheme.colorScheme.no2
            )
        }
        HorizontalDivider(
            modifier = modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = LiftTheme.colorScheme.no17
        )
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {

                Icon(
                    modifier = modifier.width(24.dp),
                    painter = painterResource(id = LiftIcon.LiftSmallLogo),
                    contentDescription = "lift",
                    tint = Color.Unspecified
                )
                Spacer(modifier = modifier.padding(8.dp))
                Row {
                    Text(
                        text = "현재 버전",
                        color = LiftTheme.colorScheme.no11,
                        style = LiftTheme.typography.no3
                    )
                    Text(
                        text = " $versionName",
                        color = LiftTheme.colorScheme.no11,
                        style = LiftTheme.typography.no4
                    )

                }
            }
        }
    }
}