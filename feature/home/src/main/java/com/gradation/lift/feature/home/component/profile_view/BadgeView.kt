package com.gradation.lift.feature.home.component.profile_view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.component.LiftOutlineButton
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme


/**
 * 현재 Badge 기능을 구현하지 않아 해당 컴포넌트를  보류합니다.
 * @since 2023-08-18 20:45:21
 */
@Composable
internal fun BadgeView(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start)
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = modifier
                        .background(
                            color = LiftTheme.colorScheme.no1,
                            shape = RoundedCornerShape(96.dp)
                        )
                        .size(56.dp),
                    contentAlignment = Alignment.Center
                ){
                    IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(LiftIcon.Plus),
                            contentDescription = "",
                            tint = LiftTheme.colorScheme.no6,
                            modifier=modifier.size(16.dp)
                        )
                    }
                }
                Text(
                    text = "뱃지추가",
                    style = LiftTheme.typography.no4,
                    color = LiftTheme.colorScheme.no9,
                )

            }

        }
        Spacer(modifier = modifier.padding(8.dp))

        LiftOutlineButton(
            modifier = modifier
                .height(32.dp),
            contentPadding = PaddingValues(
                start = 15.dp, top = 0.dp, end = 15.dp, bottom = 0.dp
            ),
            shape = RoundedCornerShape(6.dp),
            onClick = {},
        ) {
            Text(
                text = "전체보기",
                style = LiftTheme.typography.no5,
                color = LiftTheme.colorScheme.no4,
            )
            Spacer(modifier = modifier.padding(2.dp))
            Icon(
                painterResource(id = LiftIcon.ChevronRight),
                contentDescription = null,
            )
        }
    }
}

