package com.gradation.lift.feature.register_detail.gender.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gradation.lift.designsystem.component.selector.LiftPrimarySelector
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.model.model.user.Gender

@Composable
fun GenderSelectionView(
    modifier: Modifier = Modifier,
    gender: Gender,
    updateGender: (Gender) -> Unit,
) {
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