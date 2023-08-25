package com.gradation.lift.feature.my_info.my_info.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme


@Composable
fun StatisticView(
    modifier:Modifier= Modifier,
    workCount: Int
){
    Row(
        horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterHorizontally)
    ) {
        Column(
            modifier = modifier
                .weight(1f)
                .border(
                    width = 1.dp,
                    color = LiftTheme.colorScheme.no8,
                    shape = RoundedCornerShape(size = 12.dp)
                )
                .background(
                    color = LiftTheme.colorScheme.no5,
                    shape = RoundedCornerShape(size = 12.dp)
                )
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    modifier = modifier
                        .padding(2.dp)
                        .width(13.dp)
                        .height(16.dp),
                    painter = painterResource(id = LiftIcon.Badge),
                    contentDescription = "",
                    tint = Color.Unspecified
                )
                Spacer(modifier = modifier.padding(4.dp))
                Text(
                    text = "내 뱃지",
                    color = LiftTheme.colorScheme.no11,
                    style = LiftTheme.typography.no6
                )
                Spacer(modifier = modifier.padding(2.dp))

                Icon(
                    painter = painterResource(id = LiftIcon.ChevronRightSharp),
                    contentDescription = "",
                    tint = Color.Unspecified
                )
            }
            Text(
                text = "준비 중",
                color = LiftTheme.colorScheme.no9,
                style = LiftTheme.typography.no3,
                textAlign = TextAlign.Center
            )
        }
        Column(
            modifier = modifier
                .weight(1f)
                .border(
                    width = 1.dp,
                    color = LiftTheme.colorScheme.no8,
                    shape = RoundedCornerShape(size = 12.dp)
                )
                .background(
                    color = LiftTheme.colorScheme.no5,
                    shape = RoundedCornerShape(size = 12.dp)
                )
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    modifier = modifier
                        .padding(2.dp)
                        .width(13.dp)
                        .height(16.dp),
                    painter = painterResource(id = LiftIcon.Muscle),
                    contentDescription = "",
                    tint = Color.Unspecified
                )
                Spacer(modifier = modifier.padding(4.dp))
                Text(
                    text = "운동횟수",
                    color = LiftTheme.colorScheme.no11,
                    style = LiftTheme.typography.no6
                )
                Spacer(modifier = modifier.padding(2.dp))

            }

            Text(
                text = "${workCount}회",
                color = LiftTheme.colorScheme.no9,
                style = LiftTheme.typography.no3,
                textAlign = TextAlign.Center
            )
        }
        Column(
            modifier = modifier
                .weight(1f)
                .border(
                    width = 1.dp,
                    color = LiftTheme.colorScheme.no8,
                    shape = RoundedCornerShape(size = 12.dp)
                )
                .background(
                    color = LiftTheme.colorScheme.no5,
                    shape = RoundedCornerShape(size = 12.dp)
                )
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    modifier = modifier
                        .padding(2.dp)
                        .width(13.dp)
                        .height(16.dp),
                    painter = painterResource(id = LiftIcon.Bell),
                    contentDescription = "",
                    tint = Color.Unspecified
                )
                Spacer(modifier = modifier.padding(4.dp))
                Text(
                    text = "알림",
                    color = LiftTheme.colorScheme.no11,
                    style = LiftTheme.typography.no6
                )
                Spacer(modifier = modifier.padding(2.dp))

                Icon(
                    painter = painterResource(id = LiftIcon.ChevronRightSharp),
                    contentDescription = "",
                    tint = Color.Unspecified
                )
            }
            Text(
                text = "준비 중",
                color = LiftTheme.colorScheme.no9,
                style = LiftTheme.typography.no3,
                textAlign = TextAlign.Center
            )
        }
    }
}