package com.gradation.lift.feature.myInfo.updateInfo.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.component.selector.LiftPrimarySelector
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.model.model.user.Gender

@Composable
fun GenderSelectionView(
    modifier: Modifier = Modifier,
    gender: Gender,
    updateGender: (Gender) -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
    ) {
        LiftText(
            modifier = modifier.fillMaxWidth(),
            textStyle = LiftTextStyle.No3,
            text = "성별",
            color = LiftTheme.colorScheme.no3,
            textAlign = TextAlign.Start
        )
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
        ) {
            LiftPrimarySelector(
                modifier = modifier
                    .fillMaxWidth()
                    .weight(1f),
                text = "남자",
                isSelected = gender.getGenderValue() == Gender.MALE_VALUE,
                onClick = { updateGender(Gender.Male()) }
            )
            LiftPrimarySelector(
                modifier = modifier
                    .fillMaxWidth()
                    .weight(1f),
                text = "여자",
                isSelected = gender.getGenderValue() == Gender.FEMALE_VALUE,
                onClick = { updateGender(Gender.Female()) }
            )
        }
    }
}