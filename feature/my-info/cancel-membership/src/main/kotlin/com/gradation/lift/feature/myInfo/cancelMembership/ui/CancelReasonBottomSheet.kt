package com.gradation.lift.feature.myInfo.cancelMembership.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.component.bottomSheet.LiftBottomSheet
import com.gradation.lift.designsystem.component.icon.IconBoxSize
import com.gradation.lift.designsystem.component.icon.IconType
import com.gradation.lift.designsystem.component.icon.LiftIconBox
import com.gradation.lift.designsystem.component.radio.LiftRadioButton
import com.gradation.lift.designsystem.component.text.LiftMultiStyleText
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.component.text.TextWithStyle
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.myInfo.cancelMembership.data.CancelMembershipViewModel.Companion.CANCEL_REASON_LIST
import com.gradation.lift.ui.modifier.noRippleClickable

/**
 * 회원 탈퇴 사유 바텀 시트
 * @since 2024-02-19 19:12:06
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CancelReasonBottomSheet(
    modifier: Modifier = Modifier,
    selectedCancelReasonValue: String,
    updateSelectedCancelReasonValue: (String) -> Unit,
    updateBottomSheetView: (Boolean) -> Unit,
) {
    LiftBottomSheet(
        modifier = modifier,
        onDismissRequest = { updateBottomSheetView(false) },
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
                            TextWithStyle("탈퇴하려는 이유", color = LiftTheme.colorScheme.no4),
                            TextWithStyle("가 궁금해요"),
                        )
                    )
                }
                LiftIconBox(
                    modifier = modifier.noRippleClickable {
                        updateBottomSheetView(false)
                    },
                    icon = LiftIcon.Close,
                    iconType = IconType.Vector,
                    iconBoxSize = IconBoxSize.Size12,
                    tint = LiftTheme.colorScheme.no9
                )
            }
            Column(
                modifier = modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                CANCEL_REASON_LIST.forEach { reason ->
                    Row(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(vertical = LiftTheme.space.space12),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        LiftText(
                            modifier = modifier.weight(1f),
                            textStyle = LiftTextStyle.No3,
                            text = reason,
                            color = LiftTheme.colorScheme.no2,
                            textAlign = TextAlign.Left
                        )
                        LiftRadioButton(
                            selected = reason == selectedCancelReasonValue,
                            onClick = {
                                updateSelectedCancelReasonValue(reason)
                                updateBottomSheetView(false)
                            }
                        )
                    }
                }

            }
        }
    }
}


