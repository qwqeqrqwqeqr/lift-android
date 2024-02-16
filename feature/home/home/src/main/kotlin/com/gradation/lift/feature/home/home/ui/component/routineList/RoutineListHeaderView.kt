package com.gradation.lift.feature.home.home.ui.component.routineList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.component.icon.IconBoxSize
import com.gradation.lift.designsystem.component.icon.IconType
import com.gradation.lift.designsystem.component.icon.LiftIconBox
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.ui.modifier.noRippleClickable

@Composable
fun RoutineListHeaderView(
    modifier: Modifier = Modifier,
    navigateHomeGraphToRoutineDetailGraph: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = LiftTheme.space.space20),
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
                textStyle = LiftTextStyle.No2,
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
                textStyle = LiftTextStyle.No7,
                text = "전체보기",
                color = LiftTheme.colorScheme.no2,
                textAlign = TextAlign.Start
            )
            LiftIconBox(
                icon = LiftIcon.ChevronRight,
                iconType = IconType.Vector,
                iconBoxSize = IconBoxSize.Size12,
                padding = LiftTheme.space.space2,
                tint = LiftTheme.colorScheme.no2
            )
        }
    }
}