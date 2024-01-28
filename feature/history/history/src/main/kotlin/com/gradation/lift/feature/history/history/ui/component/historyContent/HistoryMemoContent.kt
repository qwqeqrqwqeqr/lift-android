package com.gradation.lift.feature.history.history.ui.component.historyContent

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.component.button.LiftPrimaryButton
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.component.textField.LiftDefaultInputTextField
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.model.model.history.History

@Composable
fun HistoryMemoContent(
    modifier: Modifier = Modifier,
    selectedHistory: History,
    navigateHistoryToUpdateInfoInHistoryGraph: (String, Int) -> Unit,
) {
    Column(
        modifier = modifier.padding(horizontal = LiftTheme.space.space20),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space24)
    ) {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space28)
        ) {
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(
                    LiftTheme.space.space4,
                    Alignment.CenterHorizontally
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(5) { targetScore ->
                    AnimatedContent(
                        targetState = (targetScore + 1 <= (selectedHistory.score ?: 0)),
                        label = "scoreAnimation",
                    ) { it ->
                        Icon(
                            modifier = modifier
                                .size(LiftTheme.space.space36),
                            painter = painterResource(id = LiftIcon.StarFilled),
                            contentDescription = "star",
                            tint = if (it) LiftTheme.colorScheme.no26 else LiftTheme.colorScheme.no1
                        )
                    }
                }
                Row(
                    modifier = modifier,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    LiftText(
                        textStyle = LiftTextStyle.No3,
                        text = "${selectedHistory.score}",
                        color = LiftTheme.colorScheme.no6,
                        textAlign = TextAlign.Left
                    )
                    LiftText(
                        textStyle = LiftTextStyle.No3,
                        text = "/5",
                        color = LiftTheme.colorScheme.no6,
                        textAlign = TextAlign.Left
                    )
                }
            }
            Column(
                modifier = modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8),
            ) {
                LiftText(
                    textStyle = LiftTextStyle.No3,
                    text = "메모",
                    color = LiftTheme.colorScheme.no3,
                    textAlign = TextAlign.Left
                )
                LiftDefaultInputTextField(
                    modifier = modifier.height(LiftTheme.space.space92),
                    value = selectedHistory.comment
                        ?: "",
                    singleLine = false,
                    enabled = false,
                    onValueChange = {},
                    placeHolderValue = "오늘의 경험을 남겨주세요. (20자 내)",
                )
            }
        }
        LiftPrimaryButton(text = "메모하기") {
            navigateHistoryToUpdateInfoInHistoryGraph(
                selectedHistory.comment ?: "",
                selectedHistory.score ?: 0
            )
        }
    }
}