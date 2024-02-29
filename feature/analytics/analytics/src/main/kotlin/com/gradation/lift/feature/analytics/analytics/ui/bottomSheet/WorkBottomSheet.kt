package com.gradation.lift.feature.analytics.analytics.ui.bottomSheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.component.bottomSheet.LiftBottomSheet
import com.gradation.lift.designsystem.component.selector.LiftDefaultSelector
import com.gradation.lift.designsystem.component.text.LiftMultiStyleText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.component.text.TextWithStyle
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.analytics.analytics.data.state.AnalyticsScreenState
import com.gradation.lift.ui.modifier.noRippleClickable

/**
 * 운동 선택 바텀 시트
 * @since 2024-01-08 19:20:06
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun WorkBottomSheet(
    modifier: Modifier = Modifier,
    navigateAnalyticsGraphToWorkReadyReadyRouter: () -> Unit,
    navigateAnalyticsGraphToWorkReadyRoutineSelectionRouter: () -> Unit,
    analyticsScreenState: AnalyticsScreenState,
) {
    LiftBottomSheet(
        modifier = modifier,
        onDismissRequest = { analyticsScreenState.updateWorkBottomSheetView(false) },
        dragHandle = null,
    ) {
        Column(
            modifier = modifier
                .padding(
                    start = LiftTheme.space.space20,
                    end = LiftTheme.space.space20,
                    bottom = LiftTheme.space.space20
                )
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space28)
        ) {
            Row(
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space2)
                ) {
                    LiftMultiStyleText(
                        defaultColor = LiftTheme.colorScheme.no9,
                        defaultTextStyle = LiftTextStyle.No2,
                        textAlign = TextAlign.Start,
                        textWithStyleList = listOf(
                            TextWithStyle("어떻게 운동", color = LiftTheme.colorScheme.no4),
                            TextWithStyle("할까요?"),
                        )
                    )
                }
                Icon(
                    modifier = modifier
                        .size(LiftTheme.space.space10)
                        .noRippleClickable {
                            analyticsScreenState.updateWorkBottomSheetView(false)
                        },
                    painter = painterResource(LiftIcon.Close),
                    contentDescription = "",
                    tint = LiftTheme.colorScheme.no9,
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space12)
            ) {
                LiftDefaultSelector(
                    modifier.fillMaxWidth(),
                    "내 운동루틴",
                    false,
                    navigateAnalyticsGraphToWorkReadyRoutineSelectionRouter
                )

                LiftDefaultSelector(
                    modifier.fillMaxWidth(),
                    "자유운동",
                    false,
                    navigateAnalyticsGraphToWorkReadyReadyRouter
                )
            }
        }
    }
}


