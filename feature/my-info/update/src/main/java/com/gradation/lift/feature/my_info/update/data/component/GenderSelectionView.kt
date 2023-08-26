package com.gradation.lift.feature.my_info.update.data.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.component.LiftToggleTextBox
import com.gradation.lift.model.model.user.Gender

@Composable
fun GenderSelectionView(
    modifier: Modifier = Modifier,
    gender: Gender,
    updateFemale: () -> Unit,
    updateMale: () -> Unit,
) {
    Row {
        LiftToggleTextBox(
            text = "남자", checked = gender.getGenderValue() == Gender.MALE_VALUE,
            modifier = modifier
                .fillMaxWidth()
                .weight(1f),
            onCheckedChange = { updateMale() }
        )
        Spacer(modifier = modifier.padding(4.dp))
        LiftToggleTextBox(
            text = "여자",
            checked = gender.getGenderValue() == Gender.FEMALE_VALUE,
            modifier = modifier
                .fillMaxWidth()
                .weight(1f),
            onCheckedChange = { updateFemale() }
        )
    }
}