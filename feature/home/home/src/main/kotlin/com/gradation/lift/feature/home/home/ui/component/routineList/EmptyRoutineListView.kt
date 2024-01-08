package com.gradation.lift.feature.home.home.ui.component.routineList

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.R
import com.gradation.lift.designsystem.component.button.LiftSolidButton
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.home.home.ui.component.RoutineListHeaderView


fun LazyListScope.emptyRoutineListView(
    modifier: Modifier = Modifier,
    navigateHomeGraphToRoutineDetailGraph: () -> Unit,
    navigateMainGraphToCreateRoutineGraph: () -> Unit,
) {
    item {
        Column(
            modifier = modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space32)
        ) {
            RoutineListHeaderView(modifier, navigateHomeGraphToRoutineDetailGraph)
            Column(
                modifier = modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = modifier.size(LiftTheme.space.space72),
                    painter = painterResource(id = R.drawable.open_box),
                    contentDescription = "emptyBox",
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
                ) {
                    LiftText(
                        textStyle = LiftTextStyle.No2,
                        text = "루틴이 존재하지 않네요...",
                        color = LiftTheme.colorScheme.no2,
                        textAlign = TextAlign.Center
                    )
                    LiftText(
                        textStyle = LiftTextStyle.No7,
                        text = "루틴을 추가하면 더욱 빠르게\n 운동을 시작할 수 있어요",
                        color = LiftTheme.colorScheme.no2,
                        textAlign = TextAlign.Center
                    )
                }
            }
            LiftSolidButton(
                modifier = modifier.fillMaxWidth(),
                text = "루틴 추가하기",
                onClick = navigateMainGraphToCreateRoutineGraph,
            )
        }
    }
}