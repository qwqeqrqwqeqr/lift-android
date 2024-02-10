package com.gradation.lift.feature.home.home.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.gradation.lift.ui.modifier.noRippleClickable

@Composable
fun RoutineListHeaderView(
    modifier:Modifier=Modifier,
    navigateHomeGraphToRoutineDetailGraph: () -> Unit,
){
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
        ) {
            Icon(
                painter = painterResource(LiftIcon.RoutineList),
                contentDescription = "routine",
                tint = Color.Unspecified,
            )
            LiftText(
                textStyle = LiftTextStyle.No1,
                text = "자주 사용하는 루틴",
                color = LiftTheme.colorScheme.no3,
                textAlign = TextAlign.Start
            )
        }

        Row(
            modifier = modifier.noRippleClickable { navigateHomeGraphToRoutineDetailGraph() },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space2)
        ) {
            LiftText(
                textStyle = LiftTextStyle.No6,
                text = "전체보기",
                color = LiftTheme.colorScheme.no2,
                textAlign = TextAlign.Start
            )
            Icon(
                modifier = modifier
                    .size(LiftTheme.space.space8),
                painter = painterResource(LiftIcon.ChevronRight),
                contentDescription = "selectAllBadge",
                tint = LiftTheme.colorScheme.no2,
            )
        }
    }
}