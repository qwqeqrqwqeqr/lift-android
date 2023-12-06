package com.gradation.lift.feature.register_detail.profile_picture.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gradation.lift.designsystem.component.LiftButton
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
fun NavigationView(
    modifier:Modifier=Modifier,
    selectedProfilePicture: String,
    navigationCondition: Boolean,
    updateCreateUserDetailProfilePicture: (String) -> Unit,
    createUserDetail: () -> Unit,
){
    LiftButton(
        modifier = modifier.fillMaxWidth(),
        onClick = {
            updateCreateUserDetailProfilePicture(selectedProfilePicture)
            createUserDetail()
        },
        enabled = navigationCondition
    ) {
        Text(
            text = "완료",
            style = LiftTheme.typography.no3,
            color = LiftTheme.colorScheme.no5,
        )
    }
}