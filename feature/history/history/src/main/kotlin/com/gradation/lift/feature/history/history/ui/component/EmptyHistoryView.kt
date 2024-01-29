package com.gradation.lift.feature.history.history.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.component.button.LiftPrimaryButton
import com.gradation.lift.designsystem.component.text.LiftMultiStyleText
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.component.text.TextWithStyle
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.history.history.data.state.HistoryScreenState
import kotlinx.datetime.LocalDate

@Composable
fun EmptyHistoryView(
    modifier: Modifier = Modifier,
    selectedDate: LocalDate,
    historyScreenState: HistoryScreenState,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = LiftTheme.space.space20),
        verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space18)
    ) {
        Spacer(modifier = modifier)
        Column(
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space4),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LiftMultiStyleText(
                modifier = modifier,
                defaultColor = LiftTheme.colorScheme.no4,
                defaultTextStyle = LiftTextStyle.No2,
                textAlign = TextAlign.Center,
                textWithStyleList = listOf(
                    TextWithStyle(text = "${selectedDate.monthNumber}월 ${selectedDate.dayOfMonth}일 "),
                    TextWithStyle(
                        text = "운동기록이 없네요",
                        color = LiftTheme.colorScheme.no9
                    ),
                )
            )
            LiftText(
                textStyle = LiftTextStyle.No6,
                text = "지금 바로 운동하러 가볼까요?",
                color = LiftTheme.colorScheme.no3,
                textAlign = TextAlign.Center
            )
        }
        LiftPrimaryButton(text = "운동하기") {
            historyScreenState.updateWorkBottomSheetView(
                true
            )
        }
    }
}